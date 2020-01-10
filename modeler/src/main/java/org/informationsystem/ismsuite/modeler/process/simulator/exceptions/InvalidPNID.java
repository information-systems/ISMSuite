package org.informationsystem.ismsuite.modeler.process.simulator.exceptions;

import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class InvalidPNID extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3732505582653356682L;
	
	public InvalidPNID(PetriNet net) {
		super("Net is not a valid PNID net");
	}

}
