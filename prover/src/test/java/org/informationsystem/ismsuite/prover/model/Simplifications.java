package org.informationsystem.ismsuite.prover.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.informationsystem.ismsuite.prover.model.operators.All;
import org.informationsystem.ismsuite.prover.model.operators.And;
import org.informationsystem.ismsuite.prover.model.operators.Exists;
import org.informationsystem.ismsuite.prover.model.operators.Implies;
import org.informationsystem.ismsuite.prover.model.operators.Not;
import org.informationsystem.ismsuite.prover.model.operators.Or;
import org.junit.Test;

public class Simplifications {

	@Test
	public void testSimplifyLiterals() {
		Element e = new Element("a", "universe");
		e.simplify();
		assertEquals(new Element("a", "universe"), e);
		
		Relation r = new Relation("some", e, e);
		r.simplify();
		assertEquals(new Relation("some", e, e), r);
		
	}
	
	@Test
	public void testSimplifyAnd() {
		String label = "some";
		Element a = new Element("a", "universe");
		Element b = new Element("b", "universe");
		Relation r = new Relation(label, a, b);
		
		And and = new And(r, r);
		And test = (And) and.clone();
		test.simplify();
		assertEquals(and, test);

		// ~(A && ~(B || C) )
		// === 
		// ~A || ~~(B || C) )
		// ===
		// ~A || B || C
		Not complex = new Not(
				new And(
						new Element("A",""),
						new Not(
								new Or(
										new Element("B",""),
										new Element("C","")
										)
								)
						)
				);
		
		Clause expected = new Or(
				new Not(new Element("A","")),
				new Element("B",""),
				new Element("C","")
				);
		
		Clause simple = complex.simplify();
				
		assertEquals(expected, simple);
	}
	
	@Test
	public void testSimplifyOr() {
		String label = "some";
		Element a = new Element("a", "universe");
		Element b = new Element("b", "universe");
		Relation r = new Relation(label, a, b);
		
		Or or = new Or(r, r);
		Clause test = or.simplify();
				
		assertEquals(or, test);
		
		
		// ~(A | ~(B && C) )
		// === 
		// ~A && ~~(B && C) )
		// ===
		// ~A && B && C
		Not complex = new Not(
				new Or(
						new Element("A",""),
						new Not(
								new And(
										new Element("B",""),
										new Element("C","")
										)
								)
						)
				);
		
		Clause expected = new And(
				new Not(new Element("A","")),
				new Element("B",""),
				new Element("C","")
				);
		
		Clause simple = complex.simplify();
		
		assertEquals(expected, simple);
		
	}
	
	@Test
	public void testAll() {
		// ~ ( ! [X: universe] : (~(p(X)) => q(X) ) )
		// ===
		// ? [X: universe]: ~(~(p(X)) => q(X))
		// ===
		// ? [X: universe]: (p(X) | q(X) )
		Variable x = new Variable("X","universe");
		Not complex = new Not(
				new All(
						x, 
						new Implies(
									new Not(new Relation("p", x)),
									new Relation("q", x)
								)
						)
				);
		
		Clause simple = complex.simplify();
		
		Clause expected = new Exists(x, new Or(new Relation("p",x), new Relation("q",x)));
		
		assertEquals(expected, simple);		
	}
	
	@Test
	public void testExists() {
		// ~ ( ? [X: universe] : (~(p(X)) => q(X) ) )
		// ===
		// ! [X: universe]: ~(~(p(X)) => q(X))
		// ===
		// ! [X: universe]: (p(X) | q(X) )
		Variable x = new Variable("X","universe");
		Not complex = new Not(
				new Exists(
						x, 
						new Implies(
									new Not(new Relation("p", x)),
									new Relation("q", x)
								)
						)
				);
		
		Clause simple = complex.simplify();
		
		All expected = new All(x, new Or(new Relation("p",x), new Relation("q",x)));
				
		assertEquals(expected, simple);		
	}
	
}
