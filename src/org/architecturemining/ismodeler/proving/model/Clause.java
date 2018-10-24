package org.architecturemining.ismodeler.proving.model;

public abstract class Clause implements Cloneable {

	public abstract boolean isValidIn(World world);
	
	protected String mString;
	
	protected abstract void calculateProperties();
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Clause) {
			return o.toString().equals(this.toString());
		}
		return false;
	}
	
	@Override
	public abstract Object clone();
	
	public abstract void instantiate(Variable x, Element a);
	
	@Override
	public String toString() {
		return mString;
	}
}
