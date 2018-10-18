package org.architecturemining.ismodeler.proving.model;

public class Variable extends Literal {

	private String type;
	
	public Variable(String label, String type) {
		super(label);
		this.type = type;
	}
	
	@Override
	public boolean isAbstract() {
		return true;
	}
	
	public String getType() {
		return type;
	}
	
	public Variable setType(String type) {
		this.type = type;
		return this;
	}
}
