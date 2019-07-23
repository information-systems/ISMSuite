package org.informationsystem.ismsuite.processengine.process;

public class Token extends SimpleVector {

	public Token() {
		super(0);
	}
	
	public Token(int size) {
		super(size);
	}
	
	public Token(long[] vector) {
		super(vector);
	}
	
	public boolean isBlack() {
		return (size() == 0);
	}
}
