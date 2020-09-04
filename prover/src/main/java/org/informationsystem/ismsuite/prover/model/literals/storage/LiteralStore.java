package org.informationsystem.ismsuite.prover.model.literals.storage;

import java.util.HashSet;
import java.util.Iterator;

import org.informationsystem.ismsuite.prover.model.literals.Literal;

public class LiteralStore<T extends Literal> implements Iterable<T> {
	
	private String name;
	private HashSet<T> elements = new HashSet<>();
	
	public LiteralStore(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void add(T l) {
		elements.add(l);
	}
	
	public void remove(T l) {
		elements.remove(l);
	}
	
	public boolean contains(T l) {
		return elements.contains(l);
	}
	
	public int size() {
		return elements.size();
	}
	
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return elements.iterator();
	}

}
