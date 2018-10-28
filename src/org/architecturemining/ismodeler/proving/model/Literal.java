package org.architecturemining.ismodeler.proving.model;

import java.util.Stack;

/**
 * Abstract element to build up the world. Each Literal is 
 * either an element, a relation, or a variable representing
 * some element.
 * 
 * @author jmw
 *
 */
public abstract class Literal extends Clause {

	private String label;
	
	public Literal(String label) {
		this.label = label;
	}
	
	/**
	 * Returns true if the Literal is part of the world.
	 */
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
	
	/**
	 * The only way a Literal cannot be true, is if it does
	 * not exist in the world. Hence, to explain why it is false,
	 * we only need to state that the literal is not in the world.
	 */
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
	
	/**
	 * @return True if the literal contains variables.
	 */
	public abstract boolean isAbstract();
	
	public String getLabel() {
		return label;
	}

}
