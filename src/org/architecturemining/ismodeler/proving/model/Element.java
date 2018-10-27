package org.architecturemining.ismodeler.proving.model;

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
