package org.architecturemining.ismodeler.proving.model;

public class Element extends Literal {

	private String type;
	private String mString;
	
	public Element(String label, String type) {
		super(label);
		this.type = type;
		mString = "ELT: " + label + " (" + type + ")";
	}

	@Override
	public boolean isAbstract() {
		return false;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return mString;
	}
	
	@Override
	public Object clone() {
		return new Element(getLabel(), getType());
	}
}
