package org.architecturemining.ismodeler.specification;

import org.architecturemining.ismodeler.proving.model.Relation;

public class InsertOperation extends Operation {

	private Relation relation;
	
	public InsertOperation(Relation relation) {
		this.relation = relation;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("insert (");
		sb.append(relation.getParameterString());		
		sb.append(") into ");
		sb.append(relation.getLabel());
		sb.append(";");
		return sb.toString();
	}

}
