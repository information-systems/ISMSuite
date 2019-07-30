/**
 * 
 */
package org.informationsystem.ismsuite.prover.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Returns true if all operands are valid. 
 * 
 * @author jmw
 *
 */
public class And extends Operator {

	/**
	 * To keep the sort equals for A & B and B & A, we sort
	 * the items once created.
	 */
	private List<Clause> operands;
	
	public And(Collection<Clause> clauses) { 
		this.operands = new ArrayList<>();
		this.operands.addAll(clauses);
		Collections.sort(operands);
		calculateProperties();
	}
	
	public And(Clause...clauses) {
		operands = new ArrayList<>();
		for(Clause c: clauses) {
			operands.add(c);
		}
		Collections.sort(this.operands);
		
		calculateProperties();
	}
	
	protected void calculateProperties() {
		StringBuilder sb = new StringBuilder();
		sb.append("AND( ");
		for(Clause op: operands) {
			sb.append("[");
			sb.append(op.toString());
			sb.append("] ");
		}
		sb.append(")");
		mString = sb.toString();
	}
	
	
	/**
	 * The AND function is valid in a world, if all its clauses
	 * are valid in the world. As soon as one is invalid,
	 * we know that the whole formula is false. Hence, we may
	 * directly stop.
	 */
	@Override
	public boolean isValidIn(FirstOrderLogicWorld world) {
		for(Iterator<Clause> it = operands.iterator() ; it.hasNext() ; ) {
			if (!it.next().isValidIn(world)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Object clone() {
		ArrayList<Clause> params = new ArrayList<>();
		for(Clause c: operands) {
			params.add((Clause) c.clone());
		}
		return new And(params);
	}
	
	@Override
	public void instantiate(Variable x, Element a) {
		for(Clause c: operands) {
			c.instantiate(x, a);
		}
		
		calculateProperties();
	}

	/**
	 * IF A & B is not true, its explanation is either 
	 * not(A) or not(B), depending on which of them is false;
	 * An operand is invalid if the stack its explanation generates
	 * is not empty; since there is some explanation.
	 * Again, since the operand is invalid, we pass on the reason
	 * why one of its operands is invalid, and conclude that this
	 * Clause itself is also invalid.
	 */
	@Override
	public Stack<Clause> findExplanationFor(FirstOrderLogicWorld world) {

		for(Clause op: operands) {
			Stack<Clause> exOp = op.findExplanationFor(world);
			// if exOp is empty, the clause holds
			if (!exOp.isEmpty()) {
				exOp.add(new Not(this));
				return exOp;
			}
		}
		return new Stack<>();
	}

	@Override
	public String toTFF(boolean typed) {
		StringBuilder sb = new StringBuilder();
		for(Clause c: operands) {
			sb.append(" & ");
			sb.append(c.toTFF(false));
		}
		return "(" + sb.substring(2) + " )";
	}
}
