package org.informationsystem.ismodeler.specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Variable;
import org.informationsystem.proving.model.World;

public class Transaction {

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
	
	public Transaction(List<Variable> arguments, List<Operation> operations) {
		for(Variable v: arguments) {
			variablelist.put(v.getLabel(), v);
		}
		this.myvariables.addAll(arguments);
		this.myoperations.addAll(operations);
	}
	
	public Transaction(Map<String, String> arguments, List<Operation> operations) {
		for(Entry<String, String> e: arguments.entrySet()) {
			Variable v = new Variable(e.getKey(), e.getValue());
			this.variablelist.put(v.getLabel(), v);
			this.myvariables.add(v);
		}
		this.myoperations.addAll(operations);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(Variable v: myvariables ) {
			sb.append(", ");
			sb.append(v.toTFF(true));
		}
		sb.append(") {");
		sb.setCharAt(0, '(');
		
		for(Operation o: myoperations) {
			sb.append("\n\t");
			sb.append(o.toString());
		}
		
		sb.append("\n}\n");
		return sb.toString();
	}
	
	public void apply(Map<Variable, Element> binding, World world) {
		for(Operation o: myoperations) {
			o.apply(binding, world);
		}
	}
}
