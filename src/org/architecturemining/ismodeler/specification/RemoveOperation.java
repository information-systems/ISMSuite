package org.architecturemining.ismodeler.specification;

import org.architecturemining.ismodeler.proving.model.Relation;

public class RemoveOperation extends Operation {

	private Relation relation;
	
	public RemoveOperation(Relation relation) {
		this.relation = relation;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("remove (");
		sb.append(relation.getParameterString());		
		sb.append(") from ");
		sb.append(relation.getLabel());
		sb.append(";");
		return sb.toString();
	}
}
