package org.informationsystem.ismsuite.specifier.model;

import org.informationsystem.ismsuite.prover.model.literals.Literal;

public class ElementNotExistingInWorldException extends OperationException {

	public ElementNotExistingInWorldException(Operation op, Literal l) {
		super("Error in operaton: '" + op.toString() +"': Literal " + l.toTFF(true) + " does not exist!");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 48583494403244239L;

}
