package org.informationsystem.ismsuite.ismsuite.model;

public class Pair<S extends Object, T extends Object> {

	private S first;
	private T second;
	
	public Pair(S first, T second) {
		this.first = first;
		this.second = second;
	}

	public S first() {
		return first;
	}
	
	public T second() {
		return second;
	}
	
}
