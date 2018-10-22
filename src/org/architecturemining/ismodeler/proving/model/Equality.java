package org.architecturemining.ismodeler.proving.model;

public class Equality extends Operator {

	private Literal left;
	private Literal right;
	
	private String mString;
	
	public Equality(Literal left, Literal right) {
		this.left = left;
		this.right = right;
		
		calculateProperties();
	}
	
	public Literal getLeft() {
		return left;
	}
	
	public Literal getRight() {
		return right;
	}
	
	private void calculateProperties() {
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
		}
		if (right.equals(x)) {
			right = (Element) a.clone();
		}
		if (left instanceof Relation) {
			left.instantiate(x, a);
		}
		if (right instanceof Relation) {
			right.instantiate(x, a);
		}
	}

}
