package org.architecturemining.ismodeler.proving.model;

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
}
