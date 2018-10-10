package org.architecturemining.ismodeller.model;

import java.util.List;
import java.util.ArrayList;

public class PredicateDefinition {

	private String name;
	
	private ArrayList<String> arguments;
	
	public PredicateDefinition(String name, List<String> arrayList) {
		this.name = name;
		
		this.arguments = new ArrayList<String>();
		this.arguments.addAll(arrayList);
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<String> getArguments() {
		return this.arguments;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(this.name);
		b.append("(");
		StringBuilder arg = new StringBuilder();
		for(String a: getArguments()) {
			arg.append(", ");
			arg.append(a);
		}
		b.append(arg.substring(1));
		b.append(" )");
		
		return b.toString();
	}
	
}
 