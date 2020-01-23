package org.informationsystem.ismsuite.modeler.simulator;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.pnml.tools.epnk.applications.Application;
import org.pnml.tools.epnk.applications.ApplicationFactory;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class Factory  extends ApplicationFactory {

	@Override
	public String getName() {
		return "ISMSuite Simulator";
	}

	@Override
	public String getDescription() {
		return "Simulator on an Information model and a Process Model";
	}

	@Override
	public boolean isApplicable(PetriNet net) {
		return (net.getType() != null && net.getType() instanceof PNID);
	}
	
	private Specification specification;
	
	public Specification getSpecifiaction() {
		return specification;
	}
	
	public void setSpecification(Specification specification) {
		this.specification = specification;
	}
	
	private World world;
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	@Override
	public Application startApplication(PetriNet net) {
		if (!isApplicable(net) || specification == null || world == null) {
			return null;
		}
		return new Simulator(net, specification, world);
	}

}
