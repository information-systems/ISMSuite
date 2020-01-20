package org.informationsystem.ismsuite.specifier.model;

import java.util.Map;

import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.prover.model.World;

public abstract class Operation {

	public abstract boolean apply(Map<Variable, Element> binding, World world) throws OperationException;

}
