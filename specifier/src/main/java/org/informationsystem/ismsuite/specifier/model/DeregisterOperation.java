package org.informationsystem.ismsuite.specifier.model;

import java.util.Map;

import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.specifier.model.Operation;

public class DeregisterOperation extends Operation {

	private Variable var;
	
	public DeregisterOperation(Variable var) {
		this.var = var;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("deregister ");
		sb.append(var.toTFF(false));
		sb.append(";");
		
		return sb.toString();
	}
	
	@Override
	public void apply(Map<Variable, Element> binding, World world) {
		if (binding.containsKey(var)) {
			if (var.getType().equals(binding.get(var).getType())) {
				world.removeElement(binding.get(var));
			}
		}		
	}

}
