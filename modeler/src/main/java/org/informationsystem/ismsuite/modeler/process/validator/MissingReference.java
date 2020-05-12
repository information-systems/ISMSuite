package org.informationsystem.ismsuite.modeler.process.validator;

import org.pnml.tools.epnk.pnmlcoremodel.Object;


public class MissingReference  extends SyntaxError {

	public MissingReference(Object pnObject)  {
		super(pnObject, "Object #NAME (#ID) is connected to an unreferenced place");
	}

}
