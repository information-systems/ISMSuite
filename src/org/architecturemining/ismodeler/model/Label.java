package org.architecturemining.ismodeler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Label {
	
	private String predicate;
	private ArrayList<String> arguments;
	private HashMap<String, String> types;

	public Label(String predicate) {
		this.predicate = predicate;
		this.arguments = new ArrayList<String>();
		this.types = new HashMap<String, String>();
	}
	
	public Label(String predicate, List<String> arguments, List<String> types) {
		this.predicate = predicate;
		this.arguments = new ArrayList<String>(arguments);
		this.types = new HashMap<String, String>();
		if (arguments.size() == types.size() ) {
			for(int i = 0 ; i < arguments.size() ; i++) {
				this.types.put(arguments.get(i), types.get(i));
			}
		}
	}
	
	public Label(String predicate, List<String> arguments, Map<String,String> types) {
		this.predicate = predicate;
		this.arguments = new ArrayList<String>(arguments);
		this.types = new HashMap<String, String>(types);
	}
	
	public String getPredicateName() {
		return this.predicate;
	}
	
	public List<String> getArguments() {
		return this.arguments;
	}
	
	public String getTypeFor(String argument) {
		return this.types.get(argument);
	}
	
	public void addArgument(String name, String type) {
		this.arguments.add(name);
		this.types.put(name, type);
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(this.predicate);
		b.append(" [");
		StringBuilder arg = new StringBuilder();
		for(String s: arguments) {
			arg.append(", ");
			arg.append(s);
		}
		b.append(arg.substring(1));
		b.append(" ] {");
		
		arg = new StringBuilder();
		for(String s: arguments) {
			arg.append(", ");
			arg.append(types.get(s));
		}
		b.append(arg.substring(1));
		b.append(" }");
		
		return b.toString();
	}
	
	public String toTFF() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("tff( ");
		sb.append(this.getPredicateName());
		sb.append("_type, type, ");
		sb.append(this.getPredicateName());
		sb.append(": ");
		StringBuilder arg = new StringBuilder();
		for(String a: this.getArguments()) {
			arg.append("* ");
			arg.append(this.getTypeFor(a));
		}
		arg.replace(0, 1, "(");
		sb.append(arg);
		sb.append(") > $o).");
		
		return sb.toString();
	}
}
