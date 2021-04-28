package org.informationsystem.ismsuite.prover.model.operators;

import java.util.Stack;

import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.ClauseVisitor;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Literal;
import org.informationsystem.ismsuite.prover.model.literals.Variable;

public class ElementOf extends Operator {

	private Literal element;
	private String type;
	
	public ElementOf(Literal element, String type) {
		this.element = element;
		this.type = type;
		
		calculateProperties();
	}
	
	public Literal getElement() {
		return element;
	}
	public String getType() {
		return type;
	}

	@Override
	public boolean isValidIn(FirstOrderLogicWorld world) {
		if (element.isAbstract()) {
			return false;
		}
		// Find the type of the element, and check whether the returned type
		// Equals the expected type.
		return world.findTypeFor(element.getLabel()).equals(type);
	}

	/**
	 * If the element is not part of the type, then we return that it is not an element...
	 */
	@Override
	public Stack<Clause> findExplanationFor(FirstOrderLogicWorld world) {
		Stack<Clause> explanation = new Stack<>();
		
		if (!isValidIn(world)) {
			explanation.add(new Not(new Element(element.getLabel(), type)));
		}
		
		return explanation;
	}

	@Override
	protected void calculateProperties() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(element.getLabel());
		sb.append(" IS IN ");
		sb.append(type);
		sb.append(")");
		
		mString = sb.toString();
	}

	@Override
	public Object clone() {
		if (element instanceof Element) {
			return new ElementOf((Element) element.clone(), type);
		}
		if (element instanceof Variable) {
			return new ElementOf((Variable) element.clone(), type);
		}
		
		return null;
	}

	@Override
	public void instantiate(Variable x, Element a) {
		if (element instanceof Variable && element.getLabel().equals(x.getLabel())) {
			element = a;
		}
	}

	@Override
	public String toTFF(boolean typed) {
		StringBuilder sb = new StringBuilder();
		sb.append("( ");
		sb.append(element.getLabel());
		sb.append(" IS IN ");
		sb.append(type);
		sb.append(" )");
		
		return sb.toString();
	}

	@Override
	public <T> T accept(ClauseVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public Clause simplify() {
		return (ElementOf) this.clone();
	}

}
