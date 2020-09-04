package org.informationsystem.ismsuite.prover.model.literals;

import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.ClauseVisitor;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;

/**
 * An element is some object in the real word that can be used
 * in defining Relations and statements about the world.
 * Each element has a Type.
 * 
 * @author jmw
 */
public class Element extends Literal {

	private String type;
	
	public Element(String label, String type) {
		super(label);
		this.type = type;
	
		calculateProperties();
	}
	
	@Override
	protected void calculateProperties() {
		mString = "ELT: " + getLabel() + " (" + type + ")";
	}

	/**
	 * An element is as concrete as it can get...
	 */
	@Override
	public boolean isAbstract() {
		return false;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public Object clone() {
		return new Element(getLabel(), getType());
	}

	@Override
	public void instantiate(Variable x, Element a) {
		// Nothing to do :-)
	}

	@Override
	public String toTFF(boolean typed) {
		if (typed) {
			return getLabel() + ": " + getType();
		} else {
			return getLabel();
		}
	}
	
	/**
	 * Returns true if the Literal is part of the world.
	 */
	@Override
	public boolean isValidIn(FirstOrderLogicWorld world) {
		if (world.contains(this)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public <T> T accept(ClauseVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public Clause simplify() {
		return (Element) this.clone();
	}
	
}
