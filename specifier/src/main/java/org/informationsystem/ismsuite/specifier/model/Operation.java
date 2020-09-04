package org.informationsystem.ismsuite.specifier.model;

import java.util.Map;

import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Variable;

public abstract class Operation {

	public abstract boolean apply(Map<Variable, Element> binding, World world) throws OperationException;

}
