package org.informationsystem.ismsuite.specifier.model;

import org.informationsystem.ismsuite.prover.model.Clause;

public class IsAbstractException extends OperationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2642468023723410110L;

	public IsAbstractException(Operation op, Clause c) {
		super(op, c);
	}

}
