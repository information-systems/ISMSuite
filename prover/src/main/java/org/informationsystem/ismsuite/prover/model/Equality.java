package org.informationsystem.ismsuite.prover.model;

import java.util.Stack;

/**
 * An equality is a compare-operator between two literals, e.g.
 * X = a, b = c, or R(a,b,c) = R(X,b,Y).
 * 
 * @author jmw
 */
public class Equality extends Operator {

	private Literal left;
	private Literal right;
	
	public Equality(Literal left, Literal right) {
		this.left = left;
		this.right = right;
		
		calculateProperties();
	}
	
	public Literal getLeft() {
		return left;
	}
	
	public Literal getRight() {
		return right;
	}
	
	@Override
	protected void calculateProperties() {
		StringBuilder sb = new StringBuilder();
		sb.append("EQ ( [");
		sb.append(left.toString());
		sb.append(" ] [");
		sb.append(right.toString());
		sb.append("] )");
		
		mString = sb.toString();
	}
	
	/**
	 * An Equality is valid in any world, as long as the right
	 * and left side are identical. 
	 */
	@Override
	public boolean isValidIn(FirstOrderLogicWorld world) {
		return left.equals(right);
	}

	@Override
	public Object clone() {
		return new Equality((Literal) left.clone(), (Literal) right.clone());
	}

	/**
	 * As the equality can have literals, it may be that one of 
	 * the constituents needs to be changed from the Variable
	 * to the instance itself. Otherwise, we just push the
	 * instantiation further.
	 */
	@Override
	public void instantiate(Variable x, Element a) {
		if (left.equals(x)) {
			left = (Element) a.clone();
		} else {
			left.instantiate(x, a);
		}
		if (right.equals(x)) {
			right = (Element) a.clone();
		} else {
			right.instantiate(x, a);
		}
	}

	/**
	 * Explanation of X=Y is false: not(X=Y) :-)
	 */
	@Override
	public Stack<Clause> findExplanationFor(FirstOrderLogicWorld world) {
		Stack<Clause> s = new Stack<>();
		if (!this.isValidIn(world)) {
			s.add(new Not(this));
		}
		return s;
	}

	@Override
	public String toTFF(boolean typed) {
		return "( " + left.toTFF(false) + " = " + right.toTFF(false) + " )";
	}

}
