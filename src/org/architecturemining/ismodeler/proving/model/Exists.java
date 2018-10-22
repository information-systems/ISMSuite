package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.List;

public class Exists extends Operator {

	private Clause operand;
	private String mString;
	
	private Variable variable;
	
	public Exists(Variable v, Clause clause) {
		this.operand = clause;
		this.variable = v;
		
		calculateProperties();
	}
	
	public Variable getVariable() {
		return variable;
	}
	
	public Clause getOperand() {
		return operand;
	}
	
	private void calculateProperties() {
		StringBuilder sb = new StringBuilder();
		sb.append("EXISTS [");
		sb.append(variable.toString());
		sb.append("] (");
		sb.append(operand.toString());
		sb.append(")");
		
		mString = sb.toString();
	}
	
	@Override
	public boolean isValidIn(World world) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object clone() {
		return new Exists((Variable) variable.clone(), (Clause) operand.clone());
	}
	
	@Override
	public String toString() {
		return mString;
	}

	@Override
	public void instantiate(Variable x, Element a) {
		if (variable.equals(x)) {
			return;
		}
		operand.instantiate(x, a);
		
		calculateProperties();
	}
}
