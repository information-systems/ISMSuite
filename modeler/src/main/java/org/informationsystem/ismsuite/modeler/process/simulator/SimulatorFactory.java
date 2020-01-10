package org.informationsystem.ismsuite.modeler.process.simulator;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID;
import org.pnml.tools.epnk.applications.Application;
import org.pnml.tools.epnk.applications.ApplicationFactory;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class SimulatorFactory extends ApplicationFactory {

	@Override
	public String getName() {
		return "PNID Simulator";
	}

	@Override
	public String getDescription() {
		return "PNID Simulator";
	}

	@Override
	public boolean isApplicable(PetriNet net) {
		return (net.getType() != null) && (net.getType() instanceof PNID);
	}

	@Override
	public Application startApplication(PetriNet net) {
		return new Simulator(net);
	}
}
