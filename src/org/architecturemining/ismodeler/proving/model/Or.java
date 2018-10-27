package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Returns true if at least one of the operands is true.
 * 
 * @author jmw
 */
public class Or extends Operator {

	private Collection<Clause> operands;
	
	public Or(Collection<Clause> operands) {
		this.operands = operands;
		
		calculateProperties();
	}
	
	public Or(Clause... operands) {
		this.operands = new ArrayList<>();
		for(Clause c: operands) {
			this.operands.add(c);
		}
		
		calculateProperties();
	}
	
	protected void calculateProperties() {
		StringBuilder sb = new StringBuilder();
		sb.append("OR( ");
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
			if (it.next().isValidIn(world)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object clone() {
		ArrayList<Clause> params = new ArrayList<>();
		for(Clause c: operands) {
			params.add((Clause) c.clone());
		}
		return new Or(params);
	}
	
	@Override
	public void instantiate(Variable x, Element a) {
		for(Clause c: operands) {
			c.instantiate(x, a);
		}
		calculateProperties();
	}

	/**
	 * The only explanation possible for an OR is that
	 * all its operands are not true.
	 */
	@Override
	public Stack<Clause> findExplanationFor(World world) {
		boolean value = false;
		Stack<Clause> result = new Stack<>();
		for(Clause c: operands) {
			Stack<Clause> exOp = c.findExplanationFor(world);
			if (exOp.isEmpty()) {
				value = true;
			} else {
				result.addAll(exOp);
			}
		}
		if (value) {
			return new Stack<>();
		} else {
			result.add(new Not(this));
			return result;
		}
	}

	@Override
	public String toTFF(boolean typed) {
		StringBuilder sb = new StringBuilder();
		for(Clause c: operands) {
			sb.append(" | ");
			sb.append(c.toTFF(false));
		}
		return "(" + sb.substring(1) + " )";
	}
}
