package org.architecturemining.ismodeler.proving.model;

public class True extends Clause {

	@Override
	public boolean isValidIn(World world) {
		return true;
	}

	@Override
	public Object clone() {
		return new True();
	}

	@Override
	public void instantiate(Variable x, Element a) {}

}
