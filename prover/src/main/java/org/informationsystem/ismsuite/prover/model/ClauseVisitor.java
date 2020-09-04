package org.informationsystem.ismsuite.prover.model;

import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.informationsystem.ismsuite.prover.model.operators.All;
import org.informationsystem.ismsuite.prover.model.operators.And;
import org.informationsystem.ismsuite.prover.model.operators.Equality;
import org.informationsystem.ismsuite.prover.model.operators.Exists;
import org.informationsystem.ismsuite.prover.model.operators.Implies;
import org.informationsystem.ismsuite.prover.model.operators.Not;
import org.informationsystem.ismsuite.prover.model.operators.Or;

public interface ClauseVisitor<T> {
	
	public T visit(Element e);
	
	public T visit(Relation r);
	
	public T visit(Variable v);
	
	public T visit(All a);
	
	public T visit(And a);
	
	public T visit(Equality e);
	
	public T visit(Exists e);
	
	public T visit(False f);
	
	public T visit(Implies i);
	
	public T visit(Not n);
	
	public T visit(Or o);
	
	public T visit(True t);
	
}
