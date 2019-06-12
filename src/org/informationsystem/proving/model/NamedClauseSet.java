package org.informationsystem.proving.model;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NamedClauseSet<K, C extends Clause> implements Map<K, C> {

	private HashMap<K,Set<C>> items;
	private HashSet<C> values;
	
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
	public Set<Entry<K, C>> entrySet() {
		HashSet<Entry<K, C>> result = new HashSet<>();
		
		for(Entry<K, Set<C>> entry: items.entrySet()) {
			
			for(C clause : entry.getValue()) {
				result.add(new AbstractMap.SimpleEntry<K, C>(entry.getKey(), clause));
			}
			
		}
			
		return result;
	}

	/**
	 * Returns a random element of the list
	 * Not intended for use!
	 */
	@Override
	public C get(Object key) {
		if (items.containsKey(key)) {
			for(C c: items.get(key)) {
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
	public Set<K> keySet() {
		return items.keySet();
	}

	/**
	 * Adds a clause to the set of clauses registered
	 * for this key. 
	 * If the clause already exists, it is not added
	 * at all.
	 */
	@Override
	public C put(K key, C clause) {
		if(values.contains(clause)) {
			return null;
		}
		
		if(!items.containsKey(key)) {
			items.put(key, new HashSet<C>());
		}
		
		items.get(key).add(clause);
		values.add(clause);

		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends C> clauseMap) {
		for(Entry<? extends K, ? extends C> entry: clauseMap.entrySet()) {
			this.put(entry.getKey(), entry.getValue());
		}
	}
	
	public void putAll(K key, Set<C> clauses) {
		for(C clause: clauses) {
			this.put(key, clause);
		}
	}

	@Override
	public C remove(Object key) {
		if (items.containsKey(key)) {
			for(C c: items.get(key)) {
				values.remove(c);
			}
			items.remove(key);
		}
		return null;
	}
	
	public boolean remove(String key, C clause) {
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
	public Collection<C> values() {
		return values;
	}
	
	public Set<C> values(Object key) {
		return items.get(key);
	}

	/**
	 * @return the actual number of Clauses
	 */
	public int valueSize() {
		return values.size();
	}

}
