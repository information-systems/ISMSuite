package org.informationsystem.ismsuite.modeler.process.simulator.exceptions;

import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class UnknownNetType extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3957511554252174377L;

	public UnknownNetType(PetriNet net) {
		super("PNTD: '" + net.getType().getURI() + "' is not supported");
	}
	
}
