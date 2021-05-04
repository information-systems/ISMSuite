package org.informationsystem.ismsuite.itsatrueworld.util;

import java.util.HashSet;
import java.util.Set;

import org.informationsystem.ismsuite.prover.model.BaseClauseVisitor;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.literals.Variable;


public class EmptyVariableChecker extends BaseClauseVisitor<Object> {
	
	public static Set<Variable> giveEmptyVariables(Clause c, FirstOrderLogicWorld world) {
		EmptyVariableChecker checker = new EmptyVariableChecker(world);
		checker.visit(c);
		
		return checker.getErroneousVariables();
	}	
	
	private FirstOrderLogicWorld world;
	
	private Set<Variable> errorSets = new HashSet<>();
	
	public Set<Variable> getErroneousVariables() {
		return errorSets;
	}
	
	private EmptyVariableChecker(FirstOrderLogicWorld world) {
		setWorld(world);
	}
	
	public void setWorld(FirstOrderLogicWorld world) {
		this.world = world;
	}
	
	@Override
	public Object visit(Variable v) {
		if (v.getType().isEmpty()) {
			// It is a universal variable, so no problem!
			return null;
		}
		if (world.elementSize(v.getType()) == 0) {
			errorSets.add(v);
		}
			
		return null;
	}
}