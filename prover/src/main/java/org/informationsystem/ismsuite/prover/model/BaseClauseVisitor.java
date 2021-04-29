package org.informationsystem.ismsuite.prover.model;

import java.util.Iterator;

import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Literal;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.informationsystem.ismsuite.prover.model.operators.All;
import org.informationsystem.ismsuite.prover.model.operators.And;
import org.informationsystem.ismsuite.prover.model.operators.ElementOf;
import org.informationsystem.ismsuite.prover.model.operators.Equality;
import org.informationsystem.ismsuite.prover.model.operators.Exists;
import org.informationsystem.ismsuite.prover.model.operators.Implies;
import org.informationsystem.ismsuite.prover.model.operators.Not;
import org.informationsystem.ismsuite.prover.model.operators.Operator;
import org.informationsystem.ismsuite.prover.model.operators.Or;

public class BaseClauseVisitor<T> implements ClauseVisitor<T> {
	
	@Override
	public T visit(Element e) {
		return null;
	}
	
	public void preVisit(Relation r) {}
	
	public void postVisit(Relation r) {}
	
	public T visit(Clause c) {
		if (c instanceof Literal) {
			return visit((Literal) c);
		} else if (c instanceof Operator) {
			return visit((Operator) c);
		}
		
		return null;
	}
	
	public T visit(Literal lit) {
		if (lit instanceof Element) {
			return visit((Element) lit);
		} else if (lit instanceof Variable) {
			return visit((Variable) lit);
		} else if (lit instanceof Relation) {
			return visit((Relation) lit);
		}
		
		return null;
	}
	
	public T visit(Operator op) {
		if (op instanceof All) {
			return visit((All) op);
		} else if (op instanceof And) {
			return visit((And) op);
		} else if (op instanceof ElementOf) {
			return visit((ElementOf) op);
		} else if (op instanceof Equality) {
			return visit((Equality) op);
		} else if (op instanceof Exists) {
			return visit((Exists) op);
		} else if (op instanceof Implies) {
			return visit((Implies) op);
		} else if (op instanceof Not) {
			return visit((Not) op);
		} else if (op instanceof Or) {
			return visit((Or) op);
		}
		
		return null;
	}

	@Override
	public T visit(Relation r) {
		preVisit(r);
		
		Iterator<Literal> it = r.iterator();
		while(it.hasNext()) {
			visit(it.next());
		}
		
		postVisit(r);
		
		return null;
	}

	@Override
	public T visit(Variable v) {
		return null;
	}
	
	public void preVisit(All all) {}
		
	public void postVisit(All all) {}

	@Override
	public T visit(All a) {
		preVisit(a);
		
		visit(a.getVariable());
		visit(a.getOperand());
		
		postVisit(a);
		return null;
	}
	
	public void preVisit(And a) {}
	
	public void postVisit(And a) {}

	@Override
	public T visit(And a) {
		preVisit(a);
		
		Iterator<Clause> it = a.operands();
		while(it.hasNext()) {
			visit(it.next());
		}
		
		postVisit(a);
		
		return null;
	}
	
	public void preVisit(Equality e) {}
	
	public void postVisit(Equality e) {}


	@Override
	public T visit(Equality e) {
		preVisit(e);
		visit(e.getLeft());
		visit(e.getRight());
		postVisit(e);
		return null;
	}

	public void preVisit(Exists e) {}
	
	public void postVisit(Exists e) {}

	
	@Override
	public T visit(Exists e) {
		preVisit(e);
		visit(e.getVariable());
		visit(e.getOperand());
		postVisit(e);
		return null;
	}

	@Override
	public T visit(False f) {
		return null;
	}
	
	public void preVisit(Implies i) {}
	
	public void postVisit(Implies i) {}


	@Override
	public T visit(Implies i) {
		preVisit(i);
		visit(i.getPremise());
		visit(i.getConclusion());
		postVisit(i);
		
		return null;
	}
	
	public void preVisit(Not n) {}
	
	public void postVisit(Not n) {}

	@Override
	public T visit(Not n) {
		preVisit(n);
		visit(n.getOperand());
		postVisit(n);
		return null;
	}
	
	public void preVisit(Or o) {}
	
	public void postVisit(Or o) {}

	@Override
	public T visit(Or o) {
		preVisit(o);
		
		Iterator<Clause> it = o.operands();
		while(it.hasNext()) {
			visit(it.next());
		}
		
		postVisit(o);
		return null;
	}

	@Override
	public T visit(True t) {
		return null;
	}
	
	public void preVisit(ElementOf elem) {}
	
	public void postVisit(ElementOf elem) {}

	@Override
	public T visit(ElementOf elem) {
		preVisit(elem);
		
		visit (elem.getElement());
		
		postVisit(elem);
		
		return null;
	}

}
