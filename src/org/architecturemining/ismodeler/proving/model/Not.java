package org.architecturemining.ismodeler.proving.model;

public class Not extends Operator {

	private Clause operand;
	
	public Not(Clause operand) {
		this.operand = operand;
	}
	
	@Override
	public boolean isValidIn(World world) {
		if (operand.isValidIn(world)) {
			return false;
		}
		return true;
	}
	@Override
	public Object clone() {
		return new Not((Clause) operand.clone());
	}
	
	
	@Override
	public String toString() {
		return "NOT ( [" + operand.toString() + "] );";
	}
	

}
