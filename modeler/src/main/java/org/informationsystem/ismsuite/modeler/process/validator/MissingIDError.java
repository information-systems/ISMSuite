package org.informationsystem.ismsuite.modeler.process.validator;

import org.pnml.tools.epnk.pnmlcoremodel.Object;

public class MissingIDError extends SyntaxError {

	public MissingIDError(Object pnObject) {
		super(pnObject, "Object #NAME (#ID) has no ID");
	}
	
}
