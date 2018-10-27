package org.architecturemining.ismodeler.proving.model;

import java.util.Stack;

public abstract class Clause implements Cloneable {

	public abstract boolean isValidIn(World world);
	
	/**
	 * In case the formula is invalid, this function explains
	 * which clauses made the formula invalid. 
	 * @param world
	 * @return
	 */
	public abstract Stack<Clause> findExplanationFor(World world);
		
	protected String mString;
	
	protected abstract void calculateProperties();
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Clause) {
			return o.toString().equals(this.toString());
		}
		return false;
	}
	
	@Override
	public abstract Object clone();
	
	public abstract void instantiate(Variable x, Element a);
	
	@Override
	public String toString() {
		return mString;
	}
	
	public abstract String toTFF(boolean typed);
	
	public static String printStack(Stack<Clause> stack) {
		Stack<Clause> sc = (Stack<Clause>) stack.clone();
		StringBuilder sb = new StringBuilder();
		
		while(!sc.isEmpty()) {
			sb.append(sc.pop().toTFF(false));
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
