package org.informationsystem.ismsuite.modeler.process.simulator;

import org.informationsystem.ismsuite.modeler.process.simulator.handlers.FireTransitionHandler;
import org.pnml.tools.epnk.applications.ui.ApplicationUIManager;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class PNIDSimulator extends BasicPNIDSimulator {

	public PNIDSimulator(PetriNet petrinet) {
		super(petrinet);
	}
	
	
	@Override
	public void initializeContents() {
		initializeSimulator();
	}

	
}
