package org.architecturemining.ismodeler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Population {

	private HashMap<String, String> inhabitants;
	private HashMap<String, List<String>> inhabitantsPerType;
	private HashSet<Predicate> predicates;
	
	public Population() {
		this.inhabitants = new HashMap<String, String>();
		this.inhabitantsPerType = new HashMap<String, List<String>>();
		this.predicates = new HashSet<Predicate>();
	}
	
	public Population(Map<String, String> inhabitants, Set<Predicate> initialPredicates) {
		this.inhabitants = new HashMap<String, String>();
		this.predicates = new HashSet<Predicate>(initialPredicates);
		this.inhabitantsPerType = new HashMap<String, List<String>>();
		
		Iterator<Map.Entry<String, String>> it = inhabitants.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, String> pair = it.next();
			this.addInhabitant(pair.getKey(), pair.getValue());
		}
	}
	
	public Population(Population initialPopulation) {
		this(initialPopulation.getInhabitantsWithType(), initialPopulation.getPredicates());
	}
	
	public Map<String, String> getInhabitantsWithType() {
		return this.inhabitants;
	}
	
	public Set<String> getInhabitants() {
		return this.inhabitants.keySet();
	}
	
	public int size() {
		return this.inhabitants.keySet().size();
	}
	
	public boolean isEmpty() {
		return this.inhabitants.keySet().isEmpty();
	}
	
	public Set<Predicate> getPredicates() {
		return this.predicates;
	}
	
	public boolean containsPredicate(Predicate p) {
		return predicates.contains(p);
	}
	
	public void removePredicate(Predicate p) {
		predicates.remove(p);
	}
	
	public void addPredicate(Predicate p) {
		predicates.add(p);
	}
	
	public void addInhabitant(String id, String type) {
		this.inhabitants.put(id, type);
		if (this.inhabitantsPerType.containsKey(type)) {
			this.inhabitantsPerType.get(type).add(id);
		} else {
			ArrayList<String> l = new ArrayList<String>();
			l.add(id);
			this.inhabitantsPerType.put(type, l);
		} 
	}
	
	public boolean containsInhabitant(String id) {
		return this.inhabitants.containsKey(id);
	}
	
	public String getTypeFor(String id) {
		return this.inhabitants.get(id);
	}
	
	public String getTFFUniquenessConstraints() {
		StringBuilder sb = new StringBuilder();
		
		for(List<String> list : inhabitantsPerType.values()) {
		
			for(String p1: list) {
				for (String p2: list) {
					if (p1 != p2) {
						sb.append("tff( ");
						sb.append(p1);
						sb.append("_");
						sb.append(p2);
						sb.append("_unique, axiom, ");
						sb.append(p1);
						sb.append(" != ");
						sb.append(p2);
						sb.append(").\n");
					}
				}
			}
		}
		return sb.toString();
	}
	
	public String toTFF() {
		StringBuilder sb = new StringBuilder();
		
		for(String inh: inhabitants.keySet()) {
			sb.append("tff( ");
			sb.append(inh);
			sb.append("_type, type, ");
			sb.append(inh);
			sb.append(": ");
			sb.append(inhabitants.get(inh));
			sb.append(").\n");
		}

		sb.append("\n\n");
		
		// add uniqueness constraints
		sb.append(this.getTFFUniquenessConstraints());
		
		for(Predicate p: predicates) {
			sb.append(p.toTFF());
			sb.append("\n");
		}
		
		return sb.toString();
	}

	public void removeInhabitant(String string) {
		inhabitants.remove(string);
	}
}
