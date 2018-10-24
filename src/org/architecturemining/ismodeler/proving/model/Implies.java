package org.architecturemining.ismodeler.proving.model;

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

}
