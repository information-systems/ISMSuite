package org.informationsystem.ismsuite.specifier.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.informationsystem.ismsuite.specifier.model.Operation;

public class Transaction {

	private String label;
	
	private List<Operation> myoperations = new ArrayList<>();
	private List<Variable> myvariables = new ArrayList<>();
	private Map<String, Variable> variablelist = new HashMap<>();
	
	public Iterator<Operation> operations() {
		return myoperations.iterator();
	}
	
	public Iterator<Variable> variables() {
		return myvariables.iterator();
	}
	
	public Variable getVariable(int i) {
		return myvariables.get(i);
	}
	
	public int variableSize() {
		return myvariables.size();
	}
	
	public Variable getVariable(String name) {
		return variablelist.get(name);
	}
	
	public Transaction(String label, List<Variable> arguments, List<Operation> operations) {
		this.label = label;
		for(Variable v: arguments) {
			variablelist.put(v.getLabel(), v);
		}
		this.myvariables.addAll(arguments);
		this.myoperations.addAll(operations);
	}
	
	@Override
	public String toString() {
		return toString("");
	}
	
	public String toString(String append) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(append);
		sb.append("transaction ");
		sb.append(getLabel());
		sb.append(" ");
		
		StringBuilder vb = new StringBuilder();
		for(Variable v: myvariables ) {
			vb.append(", ");
			vb.append(v.toTFF(true));
		}
		vb.append(" )");
		vb.setCharAt(0, '(');
		
		sb.append(vb);
		sb.append(" {");
		
		for(Operation o: myoperations) {
			sb.append("\n");
			sb.append(append);
			sb.append("\t");
			sb.append(o.toString());
		}
		sb.append("\n");
		sb.append(append);
		sb.append("}\n");
		return sb.toString();
	}
	
	public String getLabel() {
		return label;
	}
	
	public boolean apply(Map<Variable, Element> binding, World world) throws OperationException {
		for(Operation o: myoperations) {
			if(!o.apply(binding, world)) {
				return false;
			}
		}
		
		return true;
	}
}
