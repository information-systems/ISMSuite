package org.informationsystem.ismsuite.modeler.process.validator;

import org.pnml.tools.epnk.pnmlcoremodel.Object;

public class ArcInscriptionTypeError extends SyntaxError {

	public ArcInscriptionTypeError(Object pnObject) {
		super(pnObject, "Typing of variables for transition #NAME (#ID) is incorrect");
	}
}
