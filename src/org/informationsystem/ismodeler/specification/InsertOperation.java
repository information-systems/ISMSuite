package org.informationsystem.ismodeler.specification;

import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.ismodeler.proving.model.Element;
import org.informationsystem.ismodeler.proving.model.Relation;
import org.informationsystem.ismodeler.proving.model.Variable;
import org.informationsystem.ismodeler.proving.model.World;

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

	@Override
	public void apply(Map<Variable, Element> binding, World world) {
		Relation newRelation = (Relation) relation.clone();
		
		for(Entry<Variable, Element> e: binding.entrySet()) {
			newRelation.instantiate(e.getKey(), e.getValue());
		}
		// Notice that this fails in case that Relation remains abstract
		// (i.e., it is not added if not all variables are instantiated
		world.addRelation(newRelation);
		
	}

}
