package org.informationsystem.ismsuite.modeler.process.simulator.exceptions;

import org.pnml.tools.epnk.pnmlcoremodel.Arc;

public class InvalidArc extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3190049517527475188L;

	public InvalidArc(Arc a) {
		super("Arc '" + a.getId() + "' is invalid!");
	}
}
