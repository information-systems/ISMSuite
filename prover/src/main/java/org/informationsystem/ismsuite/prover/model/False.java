package org.informationsystem.ismsuite.prover.model;

import java.util.Stack;

/**
 * The Clause False, which is just always false...
 *  
 * @author jmw
 *
 */
public class False extends Clause {

	public False() {
		mString = "FALSE";
	}
	
	@Override
	public boolean isValidIn(World world) {
		return false;
	}
	
	@Override
	protected void calculateProperties() {
	}

	@Override
	public Object clone() {
		return new False();
	}

	@Override
	public void instantiate(Variable x, Element a) {}

	@Override
	public Stack<Clause> findExplanationFor(World world) {
		Stack<Clause> s = new Stack<>();
		s.add(this);
		return s;
	}

	@Override
	public String toTFF(boolean typed) {
		return "$false";
	}
}
