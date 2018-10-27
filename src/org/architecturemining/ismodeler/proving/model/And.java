/**
 * 
 */
package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

/**
 * Returns true if all operands are valid. 
 * 
 * @author jmw
 *
 */
public class And extends Operator {

	private Collection<Clause> operands;
	
	public And(Collection<Clause> clauses) {
		this.operands = clauses;
		
		calculateProperties();
	}
	
	public And(Clause...clauses) {
		operands = new ArrayList<>();
		for(Clause c: clauses) {
			operands.add(c);
		}
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
	
	@Override
	public boolean isValidIn(World world) {
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
	 * How do we know a clause is false?
	 * TODO use (findExplanationFor(world).isEmpty()) instead of isValidIn(world);
	 */
	@Override
	public Stack<Clause> findExplanationFor(World world) {
		boolean value = true;
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
		return "(" + sb.substring(1) + " )";
	}

}
