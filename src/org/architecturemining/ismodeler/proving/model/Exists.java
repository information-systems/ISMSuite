package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.List;

public class Exists extends Operator {

private Clause operand;
	
	private List<Variable> variables;
	
	public Exists(Clause clause, Variable... variables) {
		this.operand = clause;
		this.variables = new ArrayList<>();
		for(Variable v: variables) {
			this.variables.add(v);
		}
	}
	
	public Exists(Clause clause, List<Variable> variables) {
		this.operand = clause;
		this.variables = variables;
	}

	@Override
	public boolean isValidIn(World world) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object clone() {
		ArrayList<Variable> vars = new ArrayList<>();
		for(Variable v: variables) {
			vars.add((Variable) v.clone());
		}
		return new Exists((Clause) operand.clone(), vars);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("EXISTS [");
		for(Variable v: variables) {
			sb.append(v.toString());
			sb.append(" ");
		}
		sb.append("] (");
		sb.append(operand.toString());
		sb.append(")");
		
		return sb.toString();
	}
}
