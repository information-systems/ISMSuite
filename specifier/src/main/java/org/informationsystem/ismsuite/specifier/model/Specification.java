package org.informationsystem.ismsuite.specifier.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.ismsuite.specifier.model.Transaction;

import java.util.Set;

public class Specification {
	
	private Map<String, Transaction> transitions = new HashMap<>();
	private Map<String, Transaction> places = new HashMap<>();
	
	public Collection<Transaction> places() {
		return places.values();
	}
		
	public void addPlace(String name, Transaction transaction) {
		places.put(name, transaction);
	}
	
	public void addTransition(String name, Transaction transaction) {
		if (!name.contains(".")) {
			
		}
		transitions.put(name, transaction);
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(Entry<String, Transaction> e: places.entrySet()) {
			sb.append("place ");
			sb.append(e.getValue().toString());
			sb.append("\n");
		}
		
		for(Entry<String, Transaction> e: transitions.entrySet()) {
			sb.append("transaction ");
			sb.append(e.getValue().toString());
			sb.append("\n");
		}
			
		return sb.toString();
	}

	public boolean containsTransition(String transition) {
		return transitions.containsKey(transition);
	}
	
	public Transaction getTransactionFor(String transition) {
		return transitions.get(transition);
	}
	
	public Set<String> transitionLabels() {
		return transitions.keySet();
	}

	public Collection<Transaction> transitions() {
		return transitions.values();
	}
	
	public Transaction getPlace(String place) {
		return places.get(place);
	}

	public boolean removeTransaction(String transition) {
		transitions.remove(transition);
		return true;
	}
	
}
