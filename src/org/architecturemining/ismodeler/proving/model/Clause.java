package org.architecturemining.ismodeler.proving.model;

public abstract class Clause implements Cloneable {

	public abstract boolean isValidIn(World world);
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public abstract Object clone();
}
