package org.architecturemining.ismodeler.proving.model;

public class Implies extends Operator {

	private Clause premise;
	private Clause conclusion;
	
	public Implies(Clause premise, Clause conclusion) {
		this.premise = premise;
		this.conclusion = conclusion;
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

}
