package org.informationsystem.ismodeler.specification;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.informationsystem.ismodeler.specification.parsing.SpecificationBaseVisitor;
import org.informationsystem.ismodeler.specification.parsing.SpecificationLexer;
import org.informationsystem.ismodeler.specification.parsing.SpecificationParser;
import org.informationsystem.ismodeler.specification.parsing.SpecificationParser.Specication_fileContext;
import org.informationsystem.ismodeler.specification.parsing.SpecificationParser.Variable_declarationContext;
import org.informationsystem.proving.model.Literal;
import org.informationsystem.proving.model.Relation;
import org.informationsystem.proving.model.Variable;

public class SpecificationReader { 

	
	public static Specification fromFileName(String filename) throws IOException {
		return fromCharStream(CharStreams.fromFileName(filename));
	}
	
	public static Specification fromString(String s) {
		return fromCharStream(CharStreams.fromString(s));
	}
	
	public static Specification fromStream(InputStream in) throws IOException {
		return fromCharStream(CharStreams.fromStream(in));
	}
	
	public static Specification fromCharStream(CharStream stream) {
		SpecificationLexer lexer = new SpecificationLexer(stream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SpecificationParser parser = new SpecificationParser(tokenStream);
        Specication_fileContext spec = parser.specication_file();
        
        SpecificationBuilder builder = new SpecificationBuilder();
        
        builder.visit(spec);
        
        return builder.getSpecification();
	}
	
	

	private static class SpecificationBuilder extends SpecificationBaseVisitor<Specification> {

		private Specification spec;
		private String currentProcess = "";
		
		public Specification getSpecification() {
			return spec;
		}
		
		@Override
		public Specification visitSpecication_file(SpecificationParser.Specication_fileContext ctx) {
			spec = new Specification();
			
			visitChildren(ctx);
			
			return spec;
		}
		
		@Override
		public Specification visitProcess(SpecificationParser.ProcessContext ctx) {
			//if (ctx.name() != null) {
			currentProcess = ctx.name().getText();
			//}
			
			return visitChildren(ctx); 
		}
		
		@Override
		public Specification visitPlace(SpecificationParser.PlaceContext ctx) { 
			String name = ctx.name().getText();
			Map<String,String> arguments = buildVariableList(ctx.argument_list());
			List<Operation> operations = buildOperations(ctx.transaction(), arguments);
			
			spec.addPlace(currentProcess + "." + name, new Transaction(arguments, operations));
			
			return spec;
		}
		
		@Override
		public Specification visitTransition(SpecificationParser.TransitionContext ctx) { 
			String name = ctx.name().getText();
			Map<String,String> arguments = buildVariableList(ctx.argument_list());
			List<Operation> operations = buildOperations(ctx.transaction(), arguments);
			
			spec.addTransition(currentProcess + "." + name, new Transaction(arguments, operations));
			
			return spec;
		}
	
	
		private List<Operation> buildOperations(List<SpecificationParser.TransactionContext> transactions, Map<String, String> vars) {
			ArrayList<Operation> result = new ArrayList<>();
			for(int i = 0; i < transactions.size() ; i++) {
				Operation op = null;
				if (transactions.get(i).register_operator() != null) {
					op = buildRegisterOperation(transactions.get(i).register_operator(), vars);
				}
				if (transactions.get(i).deregister_operator() != null) {
					op = buildDeregisterOperation(transactions.get(i).deregister_operator(), vars);
				}
				if (transactions.get(i).insert_operator() != null) {
					op = buildInsertOperation(transactions.get(i).insert_operator(), vars);
				}
				if (transactions.get(i).remove_operator() != null) {
					op = buildRemoveOperation(transactions.get(i).remove_operator(), vars);
				}
				if (op != null) {
					result.add(op);
				}
			}
			return result;
		}
		
		private RegisterOperation buildRegisterOperation(SpecificationParser.Register_operatorContext ctx, Map<String,String> vars) {
			String varname = ctx.variable().getText();
			String type = "";
			if (vars.containsKey(varname)) {
				type = vars.get(varname);
			}
			return new RegisterOperation(new Variable(varname, type));
		}
		
		private DeregisterOperation buildDeregisterOperation(SpecificationParser.Deregister_operatorContext ctx, Map<String,String> vars) {
			String varname = ctx.variable().getText();
			String type = "";
			if (vars.containsKey(varname)) {
				type = vars.get(varname);
			}
			return new DeregisterOperation(new Variable(varname, type));
		}
		
		private InsertOperation buildInsertOperation(SpecificationParser.Insert_operatorContext ctx, Map<String, String> vars) {
					
			return new InsertOperation(buildRelation(ctx.Lower_word().getText(), ctx.variable_list(), vars));
		}
		
		private RemoveOperation buildRemoveOperation(SpecificationParser.Remove_operatorContext ctx, Map<String, String> vars) {
			
			return new RemoveOperation(buildRelation(ctx.Lower_word().getText(), ctx.variable_list(), vars));
		}
		
		private Relation buildRelation(String relationName, SpecificationParser.Variable_listContext ctx, Map<String, String> vars) {
	
			ArrayList<Literal> arguments = new ArrayList<>();
			for(int i = 0 ; i < ctx.variable().size() ; i++) {
				String var = "X";
				if (ctx.variable(i).Upper_word() != null) {
					var = ctx.variable(i).Upper_word().getText();
				}
				if (ctx.variable(i).Lower_word() != null) {
					var = ctx.variable(i).Lower_word().getText();
				}
				String type = "";
				if (vars.containsKey(var)) {
					type = vars.get(var);
				}
				arguments.add(new Variable(var, type));
			}
			
			return new Relation(relationName, arguments);
			
		}
		
		private Map<String, String> buildVariableList(SpecificationParser.Argument_listContext ctx) {
			Map<String, String> list = new HashMap<>();
			
			if (ctx == null) {
				return list;
			}
					
			for(int i = 0; i < ctx.variable_declaration().size(); i++) {
				Variable_declarationContext item = ctx.variable_declaration(i);
				String type = item.type().getText();
				String name = "X";
				if (item.variable().Upper_word() != null) {
					name = item.variable().Upper_word().getText();
				}
				if (item.variable().Lower_word() != null) {
					name = item.variable().Lower_word().getText();
				}
				list.put(name, type);
			}
			
			return list;
		}
	}
}