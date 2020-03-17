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
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser.OperatorContext;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser.TransactionContext;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser.VariableContext;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser.Variable_declarationContext;

public class SpecificationBuilder extends SpecificationBaseVisitor<Object> {

	/**
	 * Contextual list of variables of the current transaction.
	 * Is cleared for every run.
	 */
	private Map<String, Variable> currentVariables = new HashMap<>();
		
	public Specification getSpecification(SpecificationParser.SpecificationContext ctx) {
		Object o = visitSpecification(ctx);
		if (o instanceof Specification) {
			return (Specification) o;
		} else {
			return new Specification();
		}
	}
	
	public Transaction getTransaction(TransactionContext ctx) {
		Object o = visitTransaction(ctx);
		if (o instanceof Transaction) {
			return (Transaction) o;
		} else {
			return null;
		}
	}
	
	@Override
	public Object visitSpecification(SpecificationParser.SpecificationContext ctx) {
		Specification spec = new Specification();
		
		for(TransactionContext child: ctx.transaction()) {

			Transaction t = getTransaction(child);
			if (t != null) {
				spec.put(t.getLabel(), t);
			}
		}
		
		return spec;
	}
	
	@Override
	public Object visitTransaction(SpecificationParser.TransactionContext ctx) {
		String label = (String) visitTransaction_name(ctx.transaction_name());
		
		currentVariables.clear();
		
		@SuppressWarnings("unchecked")
		List<Variable> arguments = (List<Variable>) visitArgument_list(ctx.argument_list());
		
		// We add all variables to the current context
		
		List<Operation> operations = new ArrayList<>();
		for(OperatorContext child: ctx.operator()) {
			Object o = visitOperator(child);
			if (o instanceof Operation) {
				operations.add((Operation) o);
			}
		}
				
		Transaction t = new Transaction(label, arguments, operations);
		return t;
	}
	
	@Override
	public Object visitTransaction_name(SpecificationParser.Transaction_nameContext ctx) {
		if (ctx == null || ctx.name() == null || ctx.name().size() == 0) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(ctx.name(0).getText());
		
		for(int i = 1 ; i < ctx.name().size(); i++) {
			sb.append(".");
			sb.append(ctx.name(i).getText());
		}
		
		return sb.toString();
	}
	
	@Override
	public Object visitArgument_list(SpecificationParser.Argument_listContext ctx) {
		List<Variable> arguments = new ArrayList<>();
		
		if (ctx == null) {
			return arguments;
		}
				
		for(Variable_declarationContext child : ctx.variable_declaration()) {
			String label = (String) visitVariable(child.variable());
			String type = (String) visitType(child.type());
			if (!label.isEmpty()) {
				Variable v = new Variable(label, type);
				arguments.add(v);
				currentVariables.put(label, v);
			}
		}
		
		return arguments;
	}
	
	@Override
	public Object visitVariable(SpecificationParser.VariableContext ctx) {
		if (ctx == null) {
			return "";
		}
		
		if (ctx.Lower_word() != null) {
			return ctx.Lower_word().getText();
		}
		
		if (ctx.Upper_word() != null) {
			return ctx.Upper_word().getText();
		}
		
		return "";
	}
	
	@Override
	public Object visitType(SpecificationParser.TypeContext ctx) {
		if (ctx == null) {
			return "";
		}
		
		if (ctx.Lower_word() != null) {
			return ctx.Lower_word().getText();
		}
		return "";
	}
	
	@Override
	public Object visitOperator(SpecificationParser.OperatorContext ctx) {
		if (ctx.register_operator() != null) {
			return visitRegister_operator(ctx.register_operator());
		}
		if (ctx.deregister_operator() != null) {
			return visitDeregister_operator(ctx.deregister_operator());
		}
		if (ctx.insert_operator() != null) {
			return visitInsert_operator(ctx.insert_operator());
		}
		if (ctx.remove_operator() != null) {
			return visitRemove_operator(ctx.remove_operator());
		}
		
		return null;
	}
	
	@Override
	public Object visitRegister_operator(SpecificationParser.Register_operatorContext ctx) {
		String label = (String) visitVariable(ctx.variable());
		if (currentVariables.containsKey(label)) {
			return new RegisterOperation(currentVariables.get(label));
		} else {
			return null;
		}
	}
	
	@Override
	public Object visitDeregister_operator(SpecificationParser.Deregister_operatorContext ctx) {
		String label = (String) visitVariable(ctx.variable());
		if (currentVariables.containsKey(label)) {
			return new DeregisterOperation(currentVariables.get(label));
		} else {
			return null;
		}
	}
		
	@Override
	public Object visitInsert_operator(SpecificationParser.Insert_operatorContext ctx) {
		@SuppressWarnings("unchecked")
		List<Literal> list = (List<Literal>) visitVariable_list(ctx.variable_list());
		String label = (String) visitRelation(ctx.relation());
				
		Relation relation = new Relation(label, list);
		
		return new InsertOperation(relation);
	}
	
	@Override
	public Object visitRemove_operator(SpecificationParser.Remove_operatorContext ctx) {
		@SuppressWarnings("unchecked")
		List<Literal> list = (List<Literal>) visitVariable_list(ctx.variable_list());
		String label = (String) visitRelation(ctx.relation());
				
		Relation relation = new Relation(label, list);
		
		return new RemoveOperation(relation);
	}
	
	@Override
	public Object visitVariable_list(SpecificationParser.Variable_listContext ctx) {
		List<Literal> list = new ArrayList<>();
		
		for(VariableContext child : ctx.variable()) {
			String var = (String) visitVariable(child);
			if (!var.isEmpty() && currentVariables.containsKey(var)) {
				list.add(currentVariables.get(var));
			}
		}
		
		return list;
	}
	
	@Override
	public Object visitRelation(SpecificationParser.RelationContext ctx) {
		if (ctx == null) {
			return "";
		}
		
		if (ctx.Lower_word() != null) {
			return ctx.Lower_word().getText();
		}
		
		return "";
	}
}