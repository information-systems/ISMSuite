package org.informationsystem.ismsuite.modeler.process.validator;

import java.util.Collection;

import org.pnml.tools.epnk.pnmlcoremodel.ID;

public class IDNotUnique extends SyntaxError {

	public IDNotUnique(Collection<ID> objects) {
		super(null, buildErrorString(objects));
	}
	
	public static String buildErrorString(Collection<ID> objects) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Objects have the same identifier: ");
		for(ID o: objects) {
			sb.append(", ");
			sb.append(o.getId());
		}
		
		return sb.toString();
	}
}
