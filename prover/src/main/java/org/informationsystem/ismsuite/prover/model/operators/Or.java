package org.informationsystem.ismsuite.prover.model.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.ClauseVisitor;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Variable;

/**
 * The Or Operator. Clause represents A | B
 * A and B are called the operands.
 * Like for the And Clause, we order the elements, as
 * A | B  = B | A.
 * That way, we ensure that both always get the same 
 * representation, and are treated as equals...
 * 
 * @author jmw
 */
public class Or extends Operator {

	private List<Clause> operands;
	
	public Or(Collection<Clause> operands) {
		this.operands = new ArrayList<Clause>();
		this.operands.addAll(operands);
		Collections.sort(this.operands);
		
		calculateProperties();
	}
	
	public Or(Clause... operands) {
		this.operands = new ArrayList<>();
		for(Clause c: operands) {
			this.operands.add(c);
		}
		Collections.sort(this.operands);
		
		calculateProperties();
	}
	
	public Iterator<Clause> operands() {
		return operands.iterator();
	}
	
	@Override
	protected void calculateProperties() {
		StringBuilder sb = new StringBuilder();
		sb.append("OR( ");
		for(Clause op: operands) {
			sb.append("[");
			sb.append(op.toString());
			sb.append("] ");
		}
		sb.append(")");
		mString = sb.toString();
	}
	
	/**
	 * The Or is valid as soon as one of the operands is true
	 * in the world. If none are true, we return false.
	 */
	@Override
	public boolean isValidIn(FirstOrderLogicWorld world) {
		for(Iterator<Clause> it = operands.iterator() ; it.hasNext() ; ) {
			if (it.next().isValidIn(world)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object clone() {
		ArrayList<Clause> params = new ArrayList<>();
		for(Clause c: operands) {
			params.add((Clause) c.clone());
		}
		return new Or(params);
	}
	
	@Override
	public void instantiate(Variable x, Element a) {
		for(Clause c: operands) {
			c.instantiate(x, a);
		}
		calculateProperties();
	}

	/**
	 * The only explanation possible for an OR is that
	 * all its operands are not true. Hence, using De Morgan:
	 * not(not(A | B)) == not(not(A) & not(B)). Hence, if
	 * we check this formula, and the Not is not valid, we get
	 * as explanation not(A) & not(B), and that is exactly what
	 * we would like to have!
	 */
	@Override
	public Stack<Clause> findExplanationFor(FirstOrderLogicWorld world) {
		List<Clause> notClauses = new ArrayList<>();
		for(Clause c: operands) {
			notClauses.add(new Not((Clause) c.clone()));
		}
		Not dm = new Not(new And(notClauses));
		
		Stack<Clause> result = dm.findExplanationFor(world);
		if (!result.isEmpty()) {
			result.add(new Not(this));
		}
		return result;
	}

	@Override
	public String toTFF(boolean typed) {
		StringBuilder sb = new StringBuilder();
		for(Clause c: operands) {
			sb.append(" | ");
			sb.append(c.toTFF(false));
		}
		return "(" + sb.substring(2) + " )";
	}
	
	@Override
	public <T> T accept(ClauseVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public Clause simplify() {
		// Only try to simplify the children
		// We could simplify by stating that
		// A || true == true
		// However, then we lose information...
		List<Clause> simpleOperands = new ArrayList<>();
		
		if (operands.size() == 2) {
			// Is it actually an implies?
			if (operands.get(0) instanceof Not) {
				return new Implies(
						((Not) operands.get(0)).getOperand().simplify(),
						operands.get(1).simplify()
						);
			} else if (operands.get(1) instanceof Not) {
				return new Implies(
						((Not) operands.get(1)).getOperand().simplify(),
						operands.get(0).simplify()
						);
			}
		}
				
		for(Clause c: operands) {
			if (c instanceof Or) {
				// Move them one level up...
				Iterator<Clause> iter =  ((Or) c).operands();
				while(iter.hasNext()) {
					simpleOperands.add(iter.next().simplify());
				}
			} else {
				simpleOperands.add(c.simplify());
			}
		}
		
		return new Or(simpleOperands);
	}
}
