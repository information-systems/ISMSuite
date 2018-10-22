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
		if (world.contains(this)) {
			return true;
		} else {
			return false;
		}
	}
	 
	public abstract boolean isAbstract();
	
	public String getLabel() {
		return label;
	}

}
