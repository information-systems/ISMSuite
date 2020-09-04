package org.informationsystem.ismsuite.prover.model.literals;

import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.ClauseVisitor;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;

/**
 * Represents a variable that can be used in Relations and
 * operators. Can only be substituted for elements.
 *  
 * @author jmw
 *
 */
public class Variable extends Literal {

	private String type;
	
	public Variable(String label, String type) {
		super(label);
		this.type = type;
		
		calculateProperties();
	}
	
	@Override
	protected void calculateProperties() {
		this.mString = "VAR: " + getLabel() + " (" + type + ")";
	}
	
	/**
	 * Is always abstract.
	 */
	@Override
	public boolean isAbstract() {
		return true;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public Object clone() {
		return new Variable(getLabel(), getType());
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
		return false;
	}
	
	@Override
	public <T> T accept(ClauseVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public Clause simplify() {
		return (Variable) this.clone();
	}
}
