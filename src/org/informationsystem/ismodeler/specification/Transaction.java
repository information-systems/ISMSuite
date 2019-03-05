package org.informationsystem.ismodeler.specification;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.ismodeler.proving.model.Element;
import org.informationsystem.ismodeler.proving.model.Variable;
import org.informationsystem.ismodeler.proving.model.World;

public class Transaction {

	private List<Operation> myoperations = new ArrayList<>();
	private List<Variable> myvariables = new ArrayList<>();
	
	public Iterator<Operation> operations() {
		return myoperations.iterator();
	}
	
	public Iterator<Variable> variables() {
		return myvariables.iterator();
	}
	
	public Transaction(List<Variable> arguments, List<Operation> operations) {
		this.myvariables.addAll(arguments);
		this.myoperations.addAll(operations);
	}
	
	public Transaction(Map<String, String> arguments, List<Operation> operations) {
		for(Entry<String, String> e: arguments.entrySet()) {
			this.myvariables.add(new Variable(e.getKey(), e.getValue()));
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
