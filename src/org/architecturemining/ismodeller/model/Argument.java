package org.architecturemining.ismodeller.model;

public class Argument {

	private String name;
	private String type;
	
	public Argument(String name) {
		this(name, "");
	}
	
	public Argument(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getValueType() {
		return this.type;
	}
}
