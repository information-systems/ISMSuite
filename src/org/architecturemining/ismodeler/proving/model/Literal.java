package org.architecturemining.ismodeler.proving.model;

import java.util.Stack;

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
	
	public Stack<Clause> findExplanationFor(World world) {
		Stack<Clause> explain = new Stack<>();
		if (this.isAbstract()) {
			explain.add(this);
		}
		if (!world.contains(this)) {
			explain.add(new Not(this));
		}
		
		return explain;
	}
	 
	public abstract boolean isAbstract();
	
	public String getLabel() {
		return label;
	}

}
