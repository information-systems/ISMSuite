package org.architecturemining.ismodeler.proving.model;

public class Literal extends Clause implements Cloneable {

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
	 
	public boolean isAbstract() {
		return false;
	}
	
	public boolean isComplex() {
		return false;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Literal setLabel(String label) {
		this.label = label;
		return this;
	}
	
	
	@Override
	public Object clone() {
		Literal clone = new Literal(this.getLabel());
		
		return clone;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Literal) {
			return ((Literal) o).getLabel().equals(getLabel());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public String toString() {
		return getLabel();
	}

}
