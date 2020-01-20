package org.informationsystem.ismsuite.specifier.model;

import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.specifier.model.Operation;

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
	public boolean apply(Map<Variable, Element> binding, World world) throws OperationException {
		Relation newRelation = (Relation) relation.clone();
		
		for(Entry<Variable, Element> e: binding.entrySet()) {
			newRelation.instantiate(e.getKey(), e.getValue());
		}
		
		if (newRelation.isAbstract()) {
			throw new IsAbstractException(this, newRelation);
		}
		
		// Notice that this fails in case that Relation remains abstract
		// (i.e., it is not added if not all variables are instantiated
		return world.addRelation(newRelation);
	}

}
