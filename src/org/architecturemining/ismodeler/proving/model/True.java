package org.architecturemining.ismodeler.proving.model;

import java.util.Stack;

/**
 * Basic representation of True. Is always true...
 * 
 * @author jmw
 *
 */
public class True extends Clause {

	public True() {
		mString = "TRUE";
	}
	
	@Override
	public boolean isValidIn(World world) {	
		return true;
	}
	
	@Override
	protected void calculateProperties() {
	}

	@Override
	public Object clone() {
		return new True();
	}

	@Override
	public void instantiate(Variable x, Element a) {}

	@Override
	public Stack<Clause> findExplanationFor(World world) {
		return new Stack<>();
	}

	@Override
	public String toTFF(boolean typed) {
		return "$true";
	}

	

}
