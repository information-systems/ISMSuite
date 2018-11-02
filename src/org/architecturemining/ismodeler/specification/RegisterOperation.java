package org.architecturemining.ismodeler.specification;

import org.architecturemining.ismodeler.proving.model.Variable;

public class RegisterOperation extends Operation {

	private Variable var;
	
	public RegisterOperation(Variable var) {
		this.var = var;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("register (");
		sb.append(var.toTFF(false));
		sb.append(");");
		
		return sb.toString();
	}

}
