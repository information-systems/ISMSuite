package org.informationsystem.ismsuite.modeler.process.validator;

import org.informationsystem.ismsuite.modeler.process.commands.ValidatorCommand;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID;
import org.pnml.tools.epnk.actions.framework.jobs.AbstractEPNKAction;
import org.pnml.tools.epnk.actions.framework.jobs.AbstractEPNKJob;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNetType;

public class PNIDSyntaxCheckerAction extends AbstractEPNKAction {

	@Override
	public boolean isEnabled(PetriNet petrinet) {
		if (petrinet != null) {
			PetriNetType type = petrinet.getType();
			return type != null && type instanceof PNID;
		}
		return false;
	}
	
	
	@Override
	protected AbstractEPNKJob createJob(PetriNet petrinet, String defaultInput) {
		ValidatorCommand.validate(petrinet, null);
		return null;
	}

	

}
