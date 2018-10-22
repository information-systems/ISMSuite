package org.architecturemining.ismodeler.proving.model;

public class Not extends Operator {

	private Clause operand;
	private String mString;
	
	public Not(Clause operand) {
		this.operand = operand;
		
		calculateProperties();
	}
	
	private void calculateProperties() {
		mString = "NOT ( [" + operand.toString() + "] );";
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
		return mString;
	}

	@Override
	public void instantiate(Variable x, Element a) {
		operand.instantiate(x, a);
		calculateProperties();
	}
}
