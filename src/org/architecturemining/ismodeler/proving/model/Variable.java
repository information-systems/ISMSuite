package org.architecturemining.ismodeler.proving.model;

public class Variable extends Literal {

	private String type;
	private String mString;
	
	public Variable(String label, String type) {
		super(label);
		this.type = type;
		this.mString = "VAR: " + label + " (" + type + ")";
	}
	
	@Override
	public boolean isAbstract() {
		return true;
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
		return new Variable(getLabel(), getType());
	}

	@Override
	public void instantiate(Variable x, Element a) {
		// Nothing to do :-)
	}
}
