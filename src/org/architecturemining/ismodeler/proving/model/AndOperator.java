/**
 * 
 */
package org.architecturemining.ismodeler.proving.model;

import java.util.Collection;
import java.util.Iterator;

/**
 * Returns true if all operands are valid. 
 * 
 * @author jmw
 *
 */
public class AndOperator extends Operator {

	private Collection<Clause> operands;
	
	public AndOperator(Collection<Clause> operands) {
		this.operands = operands; 
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

}
