package org.architecturemining.ismodeler.proving.model;

public class NotOperator extends Operator {

	private Clause operand;
	
	public NotOperator(Clause operand) {
		this.operand = operand;
	}
	
	@Override
	public boolean isValidIn(World world) {
		if (operand.isValidIn(world)) {
			return false;
		}
		return true;
	}

}
