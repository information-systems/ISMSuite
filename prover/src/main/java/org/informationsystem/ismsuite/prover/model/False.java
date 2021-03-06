package org.informationsystem.ismsuite.prover.model;

import java.util.Stack;

import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Variable;

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
	public boolean isValidIn(FirstOrderLogicWorld world) {
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
	public Stack<Clause> findExplanationFor(FirstOrderLogicWorld world) {
		Stack<Clause> s = new Stack<>();
		s.add(this);
		return s;
	}

	@Override
	public String toTFF(boolean typed) {
		return "$false";
	}
	
	@Override
	public <T> T accept(ClauseVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public Clause simplify() {
		return new False();
	}
}
