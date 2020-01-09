package org.informationsystem.ismsuite.specifier.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.informationsystem.ismsuite.prover.model.Literal;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.specifier.model.DeregisterOperation;
import org.informationsystem.ismsuite.specifier.model.InsertOperation;
import org.informationsystem.ismsuite.specifier.model.Operation;
import org.informationsystem.ismsuite.specifier.model.RegisterOperation;
import org.informationsystem.ismsuite.specifier.model.RemoveOperation;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.model.Transaction;
import org.informationsystem.ismsuite.specifier.parser.SpecificationBaseVisitor;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser.ProcessbasednameContext;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser.Variable_declarationContext;

public class SpecificationBuilder extends SpecificationBaseVisitor<Object> {

	private Specification specification;
	private String currentProcess = "";
	
	public Specification getSpecification() {
		if (specification == null) {
			specification = new Specification();
		}
		return specification;
	}
	
	private Transaction lastTransaction = null;
	
	public Transaction lastTransaction() {
		return lastTransaction;
	}
		
	@Override
	public Object visitProcess(SpecificationParser.ProcessContext ctx) {
		//if (ctx.name() != null) {
		currentProcess = ctx.name().getText();
		//}
		
		return visitChildren(ctx); 
	}
	
	@Override
	public Object visitPlace(SpecificationParser.PlaceContext ctx) { 
		String name = buildName(ctx.processbasedname());
		Map<String,String> arguments = buildVariableList(ctx.argument_list());
		List<Operation> operations = buildOperations(ctx.transaction(), arguments);
		String label = currentProcess + "." + name;
		Transaction transaction = new Transaction(label, arguments, operations);
		getSpecification().addPlace(label, transaction);
		lastTransaction = transaction;
		
		return transaction;
	}
	
	private String buildName(ProcessbasednameContext ctx) {
		if (ctx == null) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		if (ctx.name().size() > 0) {
			sb.append(ctx.name(0).getText());
		}
		for(int i = 1 ; i < ctx.name().size(); i++) {
			sb.append(".");
			sb.append(ctx.name(i).getText());
		}
		
		return sb.toString();
	}

	@Override
	public Object visitTransition(SpecificationParser.TransitionContext ctx) { 
		String name = buildName(ctx.processbasedname());
		Map<String,String> arguments = buildVariableList(ctx.argument_list());
		List<Operation> operations = buildOperations(ctx.transaction(), arguments);
		// String label;
		if (!currentProcess.isEmpty()) {
			name = currentProcess + "." + name; 
		}
		Transaction transaction = new Transaction(name, arguments, operations);
		getSpecification().addTransition(name, transaction);
		lastTransaction = transaction;
		
		return transaction;
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