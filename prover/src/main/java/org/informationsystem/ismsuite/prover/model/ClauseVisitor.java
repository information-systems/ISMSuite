package org.informationsystem.ismsuite.prover.model;

public interface ClauseVisitor<T> {

	public T visit(Clause c);
	
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
