package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class All extends Operator {

	private Clause operand;
	private String mString;
	
	private Variable variable;
	
	public All(Variable v, Clause clause) {
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
		sb.append("ALL [");
		sb.append(variable.toString());
		sb.append("] (");
		sb.append(operand.toString());
		sb.append(")");
		
		mString = sb.toString();
	}
	
	@Override
	public boolean isValidIn(World world) {
		Iterator<Element> it = world.elementsIn(variable.getType());
		while(it.hasNext()) {
			Element element = it.next();
			Clause clause = (Clause) operand.clone();
			clause.instantiate(variable, element);
			
			if (!clause.isValidIn(world)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public Object clone() {
		return new All((Variable) variable.clone(), (Clause) operand.clone());
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
