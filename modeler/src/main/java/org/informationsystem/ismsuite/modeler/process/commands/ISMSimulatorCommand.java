package org.informationsystem.ismsuite.modeler.process.commands;

import org.informationsystem.ismsuite.modeler.process.simulator.SimulatorFactory;

public class ISMSimulatorCommand extends ApplicationStartCommand {

	public ISMSimulatorCommand() {
		super(new SimulatorFactory());
	}
	
}
