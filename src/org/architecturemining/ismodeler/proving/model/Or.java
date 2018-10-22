package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Returns true if at least one of the operands is true.
 * 
 * @author jmw
 */
public class Or extends Operator {

	private Collection<Clause> operands;
	private String mString;
	
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
	
	private void calculateProperties() {
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
	public String toString() {
		return mString;
	}

	@Override
	public void instantiate(Variable x, Element a) {
		for(Clause c: operands) {
			c.instantiate(x, a);
		}
		calculateProperties();
	}
}
