package org.informationsystem.ismodeler.model;

import java.util.Map;

import org.informationsystem.ismodeler.process.BoundTransition;
import org.informationsystem.proving.model.World;

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
