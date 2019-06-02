package test.org.informationsystem.proving.model;

import static org.junit.jupiter.api.Assertions.*;

import org.informationsystem.proving.model.All;
import org.informationsystem.proving.model.And;
import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Exists;
import org.informationsystem.proving.model.Not;
import org.informationsystem.proving.model.Or;
import org.informationsystem.proving.model.Relation;
import org.informationsystem.proving.model.Variable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestInstantiation {

	@Test
	void testElement() {
		Element e = new Element("Socrates", "human");
		Element a = new Element("Augustine", "human");
		Variable x = new Variable("X", "human");
		
		e.instantiate(x, a);
		Element expected = new Element("Socrates", "human");
		
		assertTrue(e.equals(expected));
	}
	
	@Test
	void testVariable() {
		Variable e = new Variable("X", "human");
		
		Element a = new Element("Augustine", "human");
		Variable x = new Variable("X", "human");
		
		e.instantiate(x, a);
		Variable expected = new Variable("X", "human");
		
		assertTrue(e.equals(expected));
	}
	
	@Test
	void testSimpleRelation() {
		Element soc = new Element("Socrates", "human");
		Variable var1 = new Variable("X", "human");
		Variable var2 = new Variable("X", "dog");
		Variable var3 = new Variable("Y", "human");
		
		Relation r = new Relation("likes", soc, var1, var2, var3);
		
		Element a = new Element("Augustine", "human");
		Variable x = new Variable("X", "human");
		
		r.instantiate(x, a);
		
		Relation expected = new Relation("likes", soc, a, var2, var3);
		
		assertTrue(r.equals(expected));
	}
	
	@Test
	void testComplexRelation() {
		Element e = new Element("Socrates", "human");
		
		Variable var1 = new Variable("X", "human");
		Variable var2 = new Variable("X", "dog");
		Variable var3 = new Variable("Y", "human");
		
		Relation r1 = new Relation("reads", var1,e);
		Relation r2 = new Relation("likes", var1, var3);
		Relation r3 = new Relation("pets", r2, var2);
		Relation r = new Relation("does", r1,r3);
		
		Element a = new Element("Augustine", "human");
		Variable x = new Variable("X", "human");
		
		r.instantiate(x, a);
		
		Relation expected = new Relation("does", 
				new Relation("reads", a, e), 
				new Relation("pets", 
						new Relation("likes", a, var3), var2)
		);
		
		assertTrue(r.equals(expected));
	}
	
	@Test
	public void testNot() {
Element e = new Element("Socrates", "human");
		
		Variable var1 = new Variable("X", "human");
		Variable var2 = new Variable("X", "dog");
		Variable var3 = new Variable("Y", "human");
		
		Relation r1 = new Relation("reads", var1,e);
		Relation r2 = new Relation("likes", var1, var3);
		Relation r3 = new Relation("pets", r2, var2);
		Relation r = new Relation("does", r1,r3);
		
		Element a = new Element("Augustine", "human");
		Variable x = new Variable("X", "human");
		
		Not not = new Not(r);
		not.instantiate(x, a);
		
		Relation expectedRel = new Relation("does", 
				new Relation("reads", a, e), 
				new Relation("pets", 
						new Relation("likes", a, var3), var2)
		);
		
		Not expected = new Not(expectedRel);
		
		assertTrue(not.equals(expected));
	}
	
	@Test
	public void testAnd() {
		Element e = new Element("Socrates", "human");
		
		Variable var1 = new Variable("X", "human");
		Variable var2 = new Variable("X", "dog");
		Variable var3 = new Variable("Y", "human");
		
		Relation r1 = new Relation("reads", var1,e);
		Relation r2 = new Relation("likes", var1, var3);
		Relation r3 = new Relation("pets", r2, var2);
		Relation r = new Relation("does", r1,r3);
		
		Element a = new Element("Augustine", "human");
		Variable x = new Variable("X", "human");
		
		And and = new And(r, r1);
		
		and.instantiate(x, a);
		
		Relation expectedRel = new Relation("does", 
				new Relation("reads", a, e), 
				new Relation("pets", 
						new Relation("likes", a, var3), var2)
		);
		
		And expected = new And(
				expectedRel,
				new Relation("reads", a, e)
		);
		
		assertTrue(and.equals(expected));
	}

	@Test
	public void testOr() {
		Element e = new Element("Socrates", "human");
		
		Variable var1 = new Variable("X", "human");
		Variable var2 = new Variable("X", "dog");
		Variable var3 = new Variable("Y", "human");
		
		Relation r1 = new Relation("reads", var1,e);
		Relation r2 = new Relation("likes", var1, var3);
		Relation r3 = new Relation("pets", r2, var2);
		Relation r = new Relation("does", r1,r3);
		
		Element a = new Element("Augustine", "human");
		Variable x = new Variable("X", "human");
		
		Or or = new Or(r, r1);
		
		or.instantiate(x, a);
		
		Relation expectedRel = new Relation("does", 
				new Relation("reads", a, e), 
				new Relation("pets", 
						new Relation("likes", a, var3), var2)
		);
		
		Or expected = new Or(
				expectedRel,
				new Relation("reads", a, e)
		);
		
		assertTrue(or.equals(expected));
	}
	
	@Test
	public void testAllTryInstantiateBoundedVar() {
		Element e = new Element("Socrates", "human");
		
		Variable var1 = new Variable("X", "human");
		Variable var2 = new Variable("X", "dog");
		Variable var3 = new Variable("Y", "human");
		
		Relation r1 = new Relation("reads", var1,e);
		Relation r2 = new Relation("likes", var1, var3);
		Relation r3 = new Relation("pets", r2, var2);
		Relation r = new Relation("does", r1,r3);
		
		Element a = new Element("Augustine", "human");
		Variable x = new Variable("X", "human");
		
		And and = new And(r, r1);
		
		All a1 = new All(var1, and);
		a1.instantiate(x, a);
		
		All expected = new All(var1, and);
		
		assertTrue(a1.equals(expected));
	}
	
	@Test
	public void testAllTryInstantiateFreeVar() {
		Element e = new Element("Socrates", "human");
		
		Variable var1 = new Variable("X", "human");
		Variable var2 = new Variable("X", "dog");
		Variable var3 = new Variable("Y", "human");
		
		Relation r1 = new Relation("reads", var1,e);
		Relation r2 = new Relation("likes", var1, var3);
		Relation r3 = new Relation("pets", r2, var2);
		Relation r = new Relation("does", r1,r3);
		
		Element a = new Element("Augustine", "human");
		Variable x = new Variable("X", "human");
		
		And and = new And(r, r1);
		
		All a1 = new All(var3, and);
		a1.instantiate(x, a);
		
		Relation expectedRel = new Relation("does", 
				new Relation("reads", a, e), 
				new Relation("pets", 
						new Relation("likes", a, var3), var2)
		);
		
		And expectedAnd = new And(
				expectedRel,
				new Relation("reads", a, e)
		);
		
		All expected = new All(var3, expectedAnd);
		
		assertTrue(a1.equals(expected));
	}

	@Test
	public void testExistsTryInstantiateBoundedVar() {
		Element e = new Element("Socrates", "human");
		
		Variable var1 = new Variable("X", "human");
		Variable var2 = new Variable("X", "dog");
		Variable var3 = new Variable("Y", "human");
		
		Relation r1 = new Relation("reads", var1,e);
		Relation r2 = new Relation("likes", var1, var3);
		Relation r3 = new Relation("pets", r2, var2);
		Relation r = new Relation("does", r1,r3);
		
		Element a = new Element("Augustine", "human");
		Variable x = new Variable("X", "human");
		
		And and = new And(r, r1);
		
		Exists a1 = new Exists(var1, and);
		a1.instantiate(x, a);
		
		Exists expected = new Exists(var1, and);
		
		assertTrue(a1.equals(expected));
	}
	
	@Test
	public void testExistsTryInstantiateFreeVar() {
		Element e = new Element("Socrates", "human");
		
		Variable var1 = new Variable("X", "human");
		Variable var2 = new Variable("X", "dog");
		Variable var3 = new Variable("Y", "human");
		
		Relation r1 = new Relation("reads", var1,e);
		Relation r2 = new Relation("likes", var1, var3);
		Relation r3 = new Relation("pets", r2, var2);
		Relation r = new Relation("does", r1,r3);
		
		Element a = new Element("Augustine", "human");
		Variable x = new Variable("X", "human");
		
		And and = new And(r, r1);
		
		Exists a1 = new Exists(var3, and);
		a1.instantiate(x, a);
		
		Relation expectedRel = new Relation("does", 
				new Relation("reads", a, e), 
				new Relation("pets", 
						new Relation("likes", a, var3), var2)
		);
		
		And expectedAnd = new And(
				expectedRel,
				new Relation("reads", a, e)
		);
		
		Exists expected = new Exists(var3, expectedAnd);
		
		assertTrue(a1.equals(expected));
	}


}
