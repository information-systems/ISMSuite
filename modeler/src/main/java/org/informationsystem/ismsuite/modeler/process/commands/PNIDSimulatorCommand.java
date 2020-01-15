package org.informationsystem.ismsuite.modeler.process.commands;

import org.informationsystem.ismsuite.modeler.process.simulator.SimulatorFactory;

public class PNIDSimulatorCommand extends ApplicationStartCommand {

	public PNIDSimulatorCommand() {
		super(new SimulatorFactory());
	}
	
}
