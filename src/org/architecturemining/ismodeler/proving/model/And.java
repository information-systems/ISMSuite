/**
 * 
 */
package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
	}
	
	public And(Clause...clauses) {
		operands = new ArrayList<>();
		for(Clause c: clauses) {
			operands.add(c);
		}
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

}
