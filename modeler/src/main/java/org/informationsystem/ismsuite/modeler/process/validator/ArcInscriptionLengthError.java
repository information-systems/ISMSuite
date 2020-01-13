package org.informationsystem.ismsuite.modeler.process.validator;

import org.pnml.tools.epnk.pnmlcoremodel.Object;

public class ArcInscriptionLengthError extends SyntaxError {

	public ArcInscriptionLengthError(Object pnObject, int expectedLength, int actualLength) {
		super(pnObject, "Invalid inscription vector size for arc #ID. Expected size: " + expectedLength + ", I got: " + actualLength);
	}
}
