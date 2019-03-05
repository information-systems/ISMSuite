package org.informationsystem.ismodeler.specification;

import java.util.Map;

import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Variable;
import org.informationsystem.proving.model.World;

public class RegisterOperation extends Operation {

	private Variable var;
	
	public RegisterOperation(Variable var) {
		this.var = var;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("register ");
		sb.append(var.toTFF(false));
		sb.append(";");
		
		return sb.toString();
	}

	/**
	 * The Register operation adds the instantiated variable
	 * to the world as an element 
	 */
	@Override
	public void apply(Map<Variable, Element> binding, World world) {
		if (binding.containsKey(var)) {
			if (var.getType().equals(binding.get(var).getType())) {
				world.addElement(binding.get(var));
			}
		}
	}

}
