package org.informationsystem.ismsuite.specifier.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.ismsuite.specifier.model.Transaction;

import java.util.Set;

public class Specification implements Map<String, Transaction> {

	private Map<String, Transaction> transactions = new HashMap<>();
	
	@Override
	public int size() {
		return transactions.size();
	}

	@Override
	public boolean isEmpty() {
		return transactions.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return transactions.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return transactions.containsValue(value);
	}

	@Override
	public Transaction get(Object key) {
		return transactions.get(key);
	}

	@Override
	public Transaction put(String key, Transaction value) {
		return transactions.put(key, value);
	}

	@Override
	public Transaction remove(Object key) {
		return transactions.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Transaction> m) {
		transactions.putAll(m);
	}

	@Override
	public void clear() {
		transactions.clear();
	}

	@Override
	public Set<String> keySet() {
		return transactions.keySet();
	}

	@Override
	public Collection<Transaction> values() {
		return transactions.values();
	}

	@Override
	public Set<Entry<String, Transaction>> entrySet() {
		return transactions.entrySet();
	}
	
}
