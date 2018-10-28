package org.architecturemining.ismodeler.proving.model;

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
}
