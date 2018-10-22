package org.architecturemining.ismodeler.proving.model;

public abstract class Literal extends Clause {

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

}
