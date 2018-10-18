package org.architecturemining.ismodeler.proving.model;

import java.util.Collection;
import java.util.Iterator;

/**
 * Returns true if at least one of the operands is true.
 * 
 * @author jmw
 */
public class OrOperator extends Operator {

	private Collection<Clause> operands;
	
	public OrOperator(Collection<Clause> operands) {
		this.operands = operands; 
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

}
