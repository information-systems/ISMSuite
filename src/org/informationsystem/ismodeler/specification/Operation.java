package org.informationsystem.ismodeler.specification;

import java.util.Map;

import org.informationsystem.ismodeler.proving.model.Element;
import org.informationsystem.ismodeler.proving.model.Variable;
import org.informationsystem.ismodeler.proving.model.World;

public abstract class Operation {

	public abstract void apply(Map<Variable, Element> binding, World world);

}
