package org.architecturemining.ismodeler.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.architecturemining.ismodeler.proving.model.Variable;

public class Transaction {

	private List<Operation> operations = new ArrayList<>();
	private List<Variable> variables = new ArrayList<>();
	
	public Transaction(List<Variable> arguments, List<Operation> operations) {
		this.variables.addAll(arguments);
		this.operations.addAll(operations);
	}
	
	public Transaction(Map<String, String> arguments, List<Operation> operations) {
		for(Entry<String, String> e: arguments.entrySet()) {
			this.variables.add(new Variable(e.getKey(), e.getValue()));
		}
		this.operations.addAll(operations);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Variable v: variables ) {
			sb.append(", ");
			sb.append(v.toTFF(true));
		}
		sb.append(") {");
		sb.setCharAt(0, '(');
		
		for(Operation o: operations) {
			sb.append("\n\t");
			sb.append(o.toString());
		}
		
		sb.append("\n}\n");
		return sb.toString();
	}
}
