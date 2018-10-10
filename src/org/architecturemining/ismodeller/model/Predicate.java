package org.architecturemining.ismodeller.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Predicate {

	private String name;
	private ArrayList<String> arguments;
	
	public Predicate(String name) {
		this.name = name;
		this.arguments = new ArrayList<String>();
	}
	
	public Predicate(String name, String[] arguments) {
		this.name = name;
		this.arguments = new ArrayList<String>();
		for(String a: arguments) {
			this.arguments.add(a);
		}
	}
	
	public Predicate(String name, List<String> arguments) {
		this.name = name;
		this.arguments = new ArrayList<String>(arguments);
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		for(String a: arguments) {
			b.append(", ");
			b.append(a);
		}
		b.append(")");
		b.replace(1, 1, "(");
		return this.name + b.substring(1);
	}
	
	public String toTFF() {
		StringBuilder sb = new StringBuilder();
		sb.append("tff( ");
		for(String a: arguments) {
			sb.append(a);
			sb.append("_");
		}
		sb.append(this.name);
		sb.append(", axiom, ");
		sb.append(this.toString());
		sb.append(" ).");
		
		return sb.toString();
	}
	
}
