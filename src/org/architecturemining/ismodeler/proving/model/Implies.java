package org.architecturemining.ismodeler.proving.model;

import java.util.Stack;

public class Implies extends Operator {

	private Clause premise;
	private Clause conclusion;
	
	public Implies(Clause premise, Clause conclusion) {
		this.premise = premise;
		this.conclusion = conclusion;
		
		calculateProperties();
	}
	
	protected void calculateProperties() {
		StringBuilder sb = new StringBuilder();
		sb.append("( ");
		sb.append(premise.toString());
		sb.append(") => (");
		sb.append(conclusion.toString());
		sb.append(")");
		
		mString = sb.toString();
	}
	
	@Override
	public boolean isValidIn(World world) {
		// A => B === not(A) or B
		Or or = new Or(new Not(premise), conclusion);
		return or.isValidIn(world);
	}
	
	@Override
	public Object clone() {
		return new Implies((Clause) premise.clone(), (Clause) conclusion.clone());
	}
	
	@Override
	public void instantiate(Variable x, Element a) {
		premise.instantiate(x, a);
		conclusion.instantiate(x, a);
		
		calculateProperties();
	}

	/**
	 * A => B is only invalid if A & not(B).
	 * A => B = not(A) | B = not( A & not(B) )
	 */
	@Override
	public Stack<Clause> findExplanationFor(World world) {
		
		Not form = new Not(
				new And(
						this.premise,
						new Not(this.conclusion)
						)
				);
		
		// Or form = new Or(new Not(this.premise), this.conclusion);
		Stack<Clause> s = form.findExplanationFor(world);
		if (!s.isEmpty()) {
			s.add(new Not(this));
		}
		return s;
	}

	@Override
	public String toTFF(boolean typed) {
		return "( " + premise.toTFF(false) + " => " + conclusion.toTFF(false) + " )";
	}

}
