package org.informationsystem.ismsuite.specifier.model;

import org.informationsystem.ismsuite.prover.model.Clause;

public class OperationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 969972771840738385L;

	public OperationException(Operation op, Clause c) {
		super("Operation '" + op.toString() + "' gave an error on clause: " + c.toTFF(true));
	}
	
	public OperationException(Operation op) {
		super("Operation: '" + op.toString() + "' gave an exception");
	}
	
}
