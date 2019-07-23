package org.informationsystem.ismsuite.processengine.process;

import java.util.Arrays;

public class SimpleVector {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(vector);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleVector other = (SimpleVector) obj;
		if (!Arrays.equals(vector, other.vector))
			return false;
		return true;
	}

	private long[] vector;
	
	public SimpleVector(int size) {
		this.vector = new long[size];
	}
	
	public SimpleVector(long[] vector) {
		this.vector = new long[vector.length];
		for(int i = 0; i < vector.length ; i++) {
			set(i, vector[i]);
		}
	}
	
	public int size() {
		return vector.length;
	}
	
	public void set(int i, long value) throws ArrayIndexOutOfBoundsException {
		if (i < 0 || i >= vector.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
		vector[i] = value;
	}
	
	public long get(int i) throws ArrayIndexOutOfBoundsException {
		if (i < 0 || i >= vector.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return vector[i];
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < size() ; i++ ) {
			b.append(", ");
			b.append(vector[i]);
		}
		
		return "{" + b.substring(1) + " }";
	}
}
