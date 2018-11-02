package org.architecturemining.ismodeler.specification;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Specification {

	private Map<String, Transaction> transitions = new HashMap<>();
	private Map<String, Transaction> places = new HashMap<>();
	
	
	public void addPlace(String name, Transaction transaction) {
		places.put(name, transaction);
	}
	
	public void addTransition(String name, Transaction transaction) {
		transitions.put(name, transaction);
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(Entry<String, Transaction> e: places.entrySet()) {
			sb.append("place ");
			sb.append(e.getKey());
			sb.append(e.getValue().toString());
			sb.append("\n");
		}
		
		for(Entry<String, Transaction> e: transitions.entrySet()) {
			sb.append("transition ");
			sb.append(e.getKey());
			sb.append(e.getValue().toString());
			sb.append("\n");
		}
			
		return sb.toString();
	}

}
