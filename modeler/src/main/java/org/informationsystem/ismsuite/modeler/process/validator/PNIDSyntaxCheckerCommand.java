package org.informationsystem.ismsuite.modeler.process.validator;

import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class PNIDSyntaxCheckerCommand extends CompoundCommand {

	public PNIDSyntaxCheckerCommand(PetriNet net, List<SyntaxError> results) {
		super(0);
		
		List<SyntaxError> errors = PNIDSyntaxChecker.giveErrorsFor(net);
		
		results.addAll(errors);
	}
	
	@Override
	public String getDescription() {
		return "Validates whether the model is a correct PNID";
	}

	@Override
	public String getLabel() {

		return "Validate PNID";
	}

}
