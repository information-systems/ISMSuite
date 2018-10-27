package org.architecturemining.ismodeler.proving.model;

import java.util.Stack;

public class Not extends Operator {

	private Clause operand;
	
	public Not(Clause operand) {
		this.operand = operand;
		
		calculateProperties();
	}
	
	protected void calculateProperties() {
		mString = "NOT ( [" + operand.toString() + "] );";
	}
	
	@Override
	public boolean isValidIn(World world) {
		if (operand.isValidIn(world)) {
			return false;
		}
		return true;
	}
	
	@Override
	public Object clone() {
		return new Not((Clause) operand.clone());
	}
	
	@Override
	public void instantiate(Variable x, Element a) {
		operand.instantiate(x, a);
		calculateProperties();
	}

	/**
	 * If Not(A) is not true, its explanation is A. 
	 */
	@Override
	public Stack<Clause> findExplanationFor(World world) {
		Stack<Clause> explanation = new Stack<>();
		if (this.operand.isValidIn(world)) {
			explanation.add(this.operand);
		}
		return explanation;
	}

	@Override
	public String toTFF(boolean typed) {
		return "~( " + operand.toTFF(false) + " )";
	}
}
