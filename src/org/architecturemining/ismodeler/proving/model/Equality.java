package org.architecturemining.ismodeler.proving.model;

public class Equality extends Operator {

	private Clause left;
	private Clause right;
	
	public Equality(Clause left, Clause right) {
		this.left = left;
		this.right = right;
		
		calculateProperties();
	}
	
	public Clause getLeft() {
		return left;
	}
	
	public Clause getRight() {
		return right;
	}
	
	@Override
	protected void calculateProperties() {
		StringBuilder sb = new StringBuilder();
		sb.append("EQ ( [");
		sb.append(left.toString());
		sb.append(" ] [");
		sb.append(right.toString());
		sb.append("] )");
		
		mString = sb.toString();
	}
	
	@Override
	public boolean isValidIn(World world) {
		return left.equals(right);
	}

	@Override
	public Object clone() {
		return new Equality((Literal) left.clone(), (Literal) right.clone());
	}

	@Override
	public void instantiate(Variable x, Element a) {
		if (left.equals(x)) {
			left = (Element) a.clone();
		} else {
			left.instantiate(x, a);
		}
		if (right.equals(x)) {
			right = (Element) a.clone();
		} else {
			right.instantiate(x, a);
		}
	}
}
