package org.informationsystem.ismsuite.prover.model.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.ClauseVisitor;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Variable;

/**
 * Represents the Not operator.
 *   Not(A)
 *   
 * A is called the Operand
 * 
 * @author jmw
 *
 */
public class Not extends Operator {

	private Clause operand;
	
	public Not(Clause operand) {
		this.operand = operand;
		
		calculateProperties();
	}
	
	protected void calculateProperties() {
		mString = "NOT ( [" + operand.toString() + "] )";
	}
	
	/**
	 * The Not operator is valid in the world if its
	 * operand is not valid in the world.
	 */
	@Override
	public boolean isValidIn(FirstOrderLogicWorld world) {
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
	public void instantiate(Variable x, Element a) {
		operand.instantiate(x, a);
		calculateProperties();
	}

	/**
	 * If Not(A) is not true, its explanation is A. 
	 */
	@Override
	public Stack<Clause> findExplanationFor(FirstOrderLogicWorld world) {
		Stack<Clause> explanation = new Stack<>();
		if (this.operand.isValidIn(world)) {
			explanation.add(this.operand);
		}
		return explanation;
	}

	@Override
	public String toTFF(boolean typed) {
		return "~( " + operand.toTFF(false) + " )";
	}

	public Clause getOperand() {
		return operand;
	}
	
	@Override
	public <T> T accept(ClauseVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public Clause simplify() {
		// This is the most interesting part, simplification
		// through the De Morgan rules:
		
		if (operand instanceof And) {
			// Push the Not inside the And
			List<Clause> operands = new ArrayList<>();
			Iterator<Clause> it = ((And) operand).operands();
			while(it.hasNext()) {
				Clause c = (Clause) it.next().clone();
				operands.add((new Not(c)).simplify());
			}
			return new Or(operands).simplify();
		}
		if (operand instanceof Or) {
			// Push the Not inside the Or
			List<Clause> operands = new ArrayList<>();
			Iterator<Clause> it = ((Or) operand).operands();
			while(it.hasNext()) {
				Clause c = (Clause) it.next().clone();
				operands.add((new Not(c)).simplify());
			}
			return new And(operands).simplify();
		}
		
		if (operand instanceof Implies) {
			// ~(A => B) == ~(~A | B) == A & ~B
			Implies cast = (Implies) operand;
			return (new And(
					cast.getPremise().simplify(),
					(new Not((Clause) cast.getConclusion().clone())).simplify()
			)).simplify();
		}
		
		if (operand instanceof Exists) {
			Exists cast = (Exists) operand;
			Clause c = new Not((Clause) cast.getOperand().clone());
			return new All((Variable) cast.getVariable().simplify(), c.simplify());
		}
		
		if (operand instanceof All) {
			All cast = (All) operand;
			Clause c = new Not((Clause) cast.getOperand().clone());
			return new Exists((Variable) cast.getVariable().simplify(), c.simplify());
		}
		
		if (operand instanceof Not) {
			return ((Not) operand).getOperand().simplify();
		}
		
		return (Clause) this.clone();
		
	}
}
