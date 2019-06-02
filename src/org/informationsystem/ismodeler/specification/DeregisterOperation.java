package org.informationsystem.ismodeler.specification;

import java.util.Map;

import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Variable;
import org.informationsystem.proving.model.World;

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
