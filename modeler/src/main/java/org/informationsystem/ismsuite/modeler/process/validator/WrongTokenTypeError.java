package org.informationsystem.ismsuite.modeler.process.validator;

import org.pnml.tools.epnk.pnmlcoremodel.Object;

public class WrongTokenTypeError extends SyntaxError {

	
	public WrongTokenTypeError(Object pnObject) {
		super(pnObject, "Place '#NAME' (#ID) has one or more wrongly typed tokens!");
	}

}
