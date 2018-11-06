package org.architecturemining.ismodeler.specification;

import java.util.Map;

import org.architecturemining.ismodeler.proving.model.Element;
import org.architecturemining.ismodeler.proving.model.Variable;
import org.architecturemining.ismodeler.proving.model.World;

public abstract class Operation {

	public abstract void apply(Map<Variable, Element> binding, World world);

}
