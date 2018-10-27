package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Exists extends Operator {

	private Clause operand;
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
	
	@Override
	protected void calculateProperties() {
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
		Iterator<Element> it = world.elementsIn(variable.getType());
		while(it.hasNext()) {
			Element element = it.next();
			Clause clause = (Clause) operand.clone();
			clause.instantiate(variable, element);
			
			if (clause.isValidIn(world)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Object clone() {
		return new Exists((Variable) variable.clone(), (Clause) operand.clone());
	}
	
	@Override
	public void instantiate(Variable x, Element a) {
		if (variable.equals(x)) {
			return;
		}
		operand.instantiate(x, a);
		
		calculateProperties();
	}

	/**
	 * Exists is not true, if no items can be found that 
	 */
	@Override
	public Stack<Clause> findExplanationFor(World world) {
		Not dm = new Not(
				new All(
						this.getVariable(), 
						new Not(this.getOperand())
				)
			);
		return dm.findExplanationFor(world);
	}

	@Override
	public String toTFF(boolean typed) {
		return "( ? [ " + getVariable().toTFF(true) + " ] : ( " + getOperand().toTFF(false) + " ) )";
	}
}
