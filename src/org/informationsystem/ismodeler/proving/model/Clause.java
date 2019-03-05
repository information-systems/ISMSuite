package org.informationsystem.ismodeler.proving.model;

import java.util.Iterator;
import java.util.Stack;

/**
 * The basic element of Proving: everything is a Clause.
 * 
 * @author jmw
 *
 */
public abstract class Clause implements Cloneable, Comparable<Clause> {

	public abstract boolean isValidIn(World world);
	
	/**
	 * In case the formula is invalid, this function explains
	 * which clauses made the formula invalid. 
	 * @param world
	 * @return
	 */
	public abstract Stack<Clause> findExplanationFor(World world);
	
	/**
	 * The string representation of the Clause. We keep it here for
	 * performance.
	 */
	protected String mString;
	
	/**
	 * Should be called by all Constructors, to set the string
	 * representation of the Clause.
	 *  
	 * Notice that we cannot guarantee this by creating a 
	 * Constructor in this class, as super() is always the first
	 * argument to be called, whereas this function needs to be
	 * called last.
	 */
	protected abstract void calculateProperties();
	
	/**
	 * As the String representation is unique, we use this
	 * for generating the hashCode;
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	/**
	 * To Clauses are equal if their string representation
	 * is equal.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Clause) {
			return o.toString().equals(this.toString());
		}
		return false;
	}
	
	@Override
	public abstract Object clone();
	
	/**
	 * Instantiates in the clause variable x with element a. 
	 * Notice that we only allow variables to be substituted by
	 * elements, and not any Literal. 
	 * 
	 * Important: it is substituted in the Clause itself. If you
	 * want to keep the original Clause as well, make sure you
	 * first clone the Clause before instantiating.
	 * 
	 * @param x
	 * @param a
	 */
	public abstract void instantiate(Variable x, Element a);
	
	/**
	 * This function is used for an internal representation that
	 * is unique for each Clause. If two clauses have the same
	 * string representation, they are identical.
	 */
	@Override
	public String toString() {
		return mString;
	}
	
	/**
	 * Abstract method each concrete Clause needs to implement
	 * to return its representation in TFF. As sometimes, clauses
	 * may be typed, the boolean type variable is added to tell
	 * the Clause to add type information in the TFF representation.
	 *   
	 * @param typed
	 * @return the Clause in TFF format
	 */
	public abstract String toTFF(boolean typed);
	
	/**
	 * Prints a stack of Clauses in TFF format. It follows
	 * the Stack structure. 
	 * @param stack
	 * @return
	 */
	public static String printStack(Stack<Clause> stack) {
		StringBuilder sb = new StringBuilder();
		sb.append("Size: ");
		sb.append(stack.size());
		sb.append("\n");
		for( int i = stack.size() -1 ; i >= 0 ; i-- ) {
			Clause c = stack.get(i);
			sb.append(c.toTFF(false));
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Sorting etc, is all based on the String representation
	 * To make life as easy as possible.
	 */
	@Override
	public int compareTo(Clause o) {
		return this.toString().compareTo(o.toString());
	}
	
}
