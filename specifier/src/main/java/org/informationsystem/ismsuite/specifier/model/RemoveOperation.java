package org.informationsystem.ismsuite.specifier.model;

import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.informationsystem.ismsuite.specifier.model.Operation;

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

	@Override
	public boolean apply(Map<Variable, Element> binding, World world) throws OperationException {
		Relation newRelation = (Relation) relation.clone();
		
		for(Entry<Variable, Element> e: binding.entrySet()) {
			newRelation.instantiate(e.getKey(), e.getValue());
		}
		
		if (newRelation.isAbstract()) {
			throw new IsAbstractException(this, newRelation);
		}
		
		world.removeRelation(newRelation);
		
		return true;
	}
}
