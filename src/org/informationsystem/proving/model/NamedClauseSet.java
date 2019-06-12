package org.informationsystem.proving.model;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NamedClauseSet implements Map<String, Clause> {

	private HashMap<String,Set<Clause>> items;
	private HashSet<Clause> values;
	
	public NamedClauseSet() {
		items = new HashMap<>();
		values = new HashSet<>();
	}
	
	
	@Override
	public void clear() {
		values.clear();
		items.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return items.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return values.contains(value);
	}

	/**
	 * Expensive method!!!
	 */
	@Override
	public Set<Entry<String, Clause>> entrySet() {
		HashSet<Entry<String, Clause>> result = new HashSet<>();
		
		for(Entry<String, Set<Clause>> entry: items.entrySet()) {
			
			for(Clause clause : entry.getValue()) {
				result.add(new AbstractMap.SimpleEntry<String, Clause>(entry.getKey(), clause));
			}
			
		}
			
		return result;
	}

	/**
	 * Returns a random element of the list
	 * Not intended for use!
	 */
	@Override
	public Clause get(Object key) {
		if (items.containsKey(key)) {
			for(Clause c: items.get(key)) {
				return c;
			}
		}
		return null;
	}
	
	@Override
	public boolean isEmpty() {
		return values.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return items.keySet();
	}

	/**
	 * Adds a clause to the set of clauses registered
	 * for this key. 
	 * If the clause already exists, it is not added
	 * at all.
	 */
	@Override
	public Clause put(String key, Clause clause) {
		if(values.contains(clause)) {
			return null;
		}
		
		if(!items.containsKey(key)) {
			items.put(key, new HashSet<Clause>());
		}
		
		items.get(key).add(clause);
		values.add(clause);

		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Clause> clauseMap) {
		for(Entry<? extends String, ? extends Clause> entry: clauseMap.entrySet()) {
			this.put(entry.getKey(), entry.getValue());
		}
	}
	
	public void putAll(String key, Set<Clause> clauses) {
		for(Clause clause: clauses) {
			this.put(key, clause);
		}
	}

	@Override
	public Clause remove(Object key) {
		if (items.containsKey(key)) {
			for(Clause c: items.get(key)) {
				values.remove(c);
			}
			items.remove(key);
		}
		return null;
	}
	
	public boolean remove(String key, Clause clause) {
		if (values.contains(clause)) {
			items.get(key).remove(clause);
			values.remove(clause);
			return true;
		}
		
		return false;
	}

	@Override
	public int size() {
		return items.size();
	}

	@Override
	public Collection<Clause> values() {
		return values;
	}
	
	public Set<Clause> values(Object key) {
		return items.get(key);
	}

	/**
	 * @return the actual number of Clauses
	 */
	public int valueSize() {
		return values.size();
	}

}
