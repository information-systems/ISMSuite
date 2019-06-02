package org.informationsystem.proving.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * A relation represents a property of one or more Literals
 * 
 * @author jmw
 */
public class Relation extends Literal {
	
	private List<Literal> parameters;
	private boolean mIsAbstract;
	
	public Relation(String label, List<Literal> params) {
		super(label);
		parameters = params;
		
		calculateProperties();
	}
	
	public Relation(String label, Literal ... params) {
		super(label);
		parameters = new ArrayList<>();
		for(Literal p : params) {
			parameters.add(p);
		}
		
		calculateProperties();
	}

	/**
	 * Calculates the String representation, and whether
	 * it is an abstract Literal or not.
	 */
	@Override
	protected void calculateProperties() {
		StringBuilder sb = new StringBuilder();
		mIsAbstract = false;
		sb.append("REL: ");
		sb.append(getLabel());
		sb.append("(");
		for(Literal p : parameters) {
			sb.append("[");
			sb.append(p.toString());
			sb.append("] ");
			if (p.isAbstract()) {
				mIsAbstract = true;
			}
		}
		sb.append(" )");
		mString = sb.toString();
	}
	
	/**
	 * @return true if one of the literals is abstract.
	 */
	@Override
	public boolean isAbstract() {
		return mIsAbstract;
	}
	
	public Iterator<Literal> iterator() {
		return parameters.iterator();
	}
	
	public int size() {
		return parameters.size();
	}
	
	public Literal get(int index) throws IndexOutOfBoundsException {
		return parameters.get(index);
	}
	
	@Override
	public Object clone() {
		ArrayList<Literal> params = new ArrayList<>();
		for(Literal p: parameters) {
			params.add((Literal) p.clone());
		}
		return new Relation(getLabel(), params);
	}
	
	/**
	 * If one of the Literals equals the variable,
	 * we substitute the variable for the element.
	 */
	@Override
	public void instantiate(Variable x, Element a) {
		
		for(int i = 0 ; i < size() ; i++) {
			if (parameters.get(i).equals(x)) {
				parameters.set(i, a);
			} else {
				parameters.get(i).instantiate(x, a);
			}
		}
		calculateProperties();
	}
	
	@Override
	public String toTFF(boolean typed) {
		return getLabel() + "(" + getParameterString() + " )";
	}

	public String getParameterString() {
		
		StringBuilder sb = new StringBuilder();
		for(Literal item: parameters) {
			sb.append(", ");
			sb.append(item.toTFF(false));
		}
		if (sb.length() > 0) {
			return sb.substring(1);
		} else {
			return "";
		}
	}
	
	/**
	 * Returns true if the Literal is part of the world.
	 */
	@Override
	public boolean isValidIn(World world) {
		if (this.isAbstract()) {
			return false;
		}
		
		for(Literal item: parameters) {
			if (!item.isValidIn(world)) {
				return false;
			}
		}
		
		return world.contains(this);
	}
	
	/**
	 * The only way a Literal cannot be true, is if it does
	 * not exist in the world. Hence, to explain why it is false,
	 * we only need to state that the literal is not in the world.
	 */
	@Override
	public Stack<Clause> findExplanationFor(World world) {
		Stack<Clause> explain = new Stack<>();
		
		if (this.isAbstract()) {
			explain.add(this);
			return explain;
		}
		
		for(Literal item: parameters) {
			if (!item.isValidIn(world)) {
				explain.add(new Not(item));
			}
		}
		
		if (!world.contains(this)) {
			explain.add(new Not(this));
		}
		
		return explain;
	}
	
}
