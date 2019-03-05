package org.informationsystem.ismodeler.specification;

import java.util.Map;

import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Variable;
import org.informationsystem.proving.model.World;

public abstract class Operation {

	public abstract void apply(Map<Variable, Element> binding, World world);

}
