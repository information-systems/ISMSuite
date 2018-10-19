package org.architecturemining.ismodeler.proving.model;

public abstract class Literal extends Clause implements Cloneable {

	private String label;
	
	public Literal(String label) {
		this.label = label;
	}
	
	@Override
	public boolean isValidIn(World world) {
		if (this.isAbstract()) {
			return false;
		}
		return world.contains(this);
	}
	 
	public abstract boolean isAbstract();
	
	public String getLabel() {
		return label;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Literal) {
			return o.toString().equals(toString());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public abstract Object clone();
}
