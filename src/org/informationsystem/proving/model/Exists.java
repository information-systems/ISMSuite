package org.informationsystem.proving.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Implements the existential quantifier:
 *   
 *   ? [X : type ] : P(X)
 *   
 * X is called the variable, P(X) the operand.
 * 
 * @author jmw
 *
 */
public class Exists extends Operator {

	private Clause operand;
	private Variable variable;
	
	public Exists(Variable v, Clause clause) {
		this.operand = clause;
		this.variable = v;
		
		calculateProperties();
	}
	
	public Variable getVariable() {
		return variable;
	}
	
	public Clause getOperand() {
		return operand;
	}
	
	@Override
	protected void calculateProperties() {
		StringBuilder sb = new StringBuilder();
		sb.append("EXISTS [");
		sb.append(variable.toString());
		sb.append("] (");
		sb.append(operand.toString());
		sb.append(")");
		
		mString = sb.toString();
	}
	
	/**
	 * The existential quantifier only needs to check whether
	 * there is some element with the correct type in the 
	 * universe for which the operand is valid. 
	 * As soon as this one is found, we can return true.
	 * 
	 * Worst case, all elements in the universe are visited.
	 */
	@Override
	public boolean isValidIn(World world) {
		Iterator<Element> it = world.elementsIn(variable.getType());
		while(it.hasNext()) {
			Element element = it.next();
			Clause clause = (Clause) operand.clone();
			clause.instantiate(variable, element);
			
			if (clause.isValidIn(world)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Object clone() {
		return new Exists((Variable) variable.clone(), (Clause) operand.clone());
	}
	
	/**
	 * Only Free variables can be instantiated.
	 */
	@Override
	public void instantiate(Variable x, Element a) {
		if (variable.equals(x)) {
			return;
		}
		operand.instantiate(x, a);
		
		calculateProperties();
	}

	/**
	 * Exists is not true, if no items can be found that
	 * satisfies. We use De Morgan for the explanation:
	 * 
	 * ? [X: item] : P(X) === not( ! [X: item] : not(P(X)) ).
	 * 
	 * Hence, checking the existential quantifier is identical
	 * to checking the negation of the operand as an 
	 * for all clause, and check that clause. That way, either
	 * an empty Stack is returned if the Not holds, or the whole
	 * formula ! [X: item] : not(P(X)) is returned as explanation
	 * for the Not. And that is exactly the explanation we would
	 * like to have for our existential quantifier.
	 *  
	 */
	@Override
	public Stack<Clause> findExplanationFor(World world) {
		Not dm = new Not(
				new All(
						this.getVariable(), 
						new Not(this.getOperand())
				)
			);
		return dm.findExplanationFor(world);
	}

	@Override
	public String toTFF(boolean typed) {
		return "( ? [ " + getVariable().toTFF(true) + " ] : ( " + getOperand().toTFF(false) + " ) )";
	}
}
