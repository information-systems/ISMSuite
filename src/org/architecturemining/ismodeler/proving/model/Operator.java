package org.architecturemining.ismodeler.proving.model;

public abstract class Operator extends Clause {

	@Override
	public abstract boolean isValidIn(World world);

}
