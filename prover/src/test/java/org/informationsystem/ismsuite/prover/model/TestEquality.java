package org.informationsystem.ismsuite.prover.model;

import static org.junit.Assert.*;

import java.util.Stack;

import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.informationsystem.ismsuite.prover.model.operators.Equality;
import org.informationsystem.ismsuite.prover.model.operators.Not;
import org.junit.Before;
import org.junit.Test;

public class TestEquality {

	private Element s;
	private Element p;
	private Element a;
	
	private Variable x;
	
	private Relation r1;
	private Relation r2;
	private Relation r3;
	
	private Relation r4;
	private Relation r5;
	private Relation r6;
	
	private FirstOrderLogicWorld world;
	
	@Before
	public void setUp() throws Exception {
		world = new World();
		
		s = new Element("Socrates", "human");
		p = new Element("Plato", "human");
		a = new Element("Augustine", "human");
		
		x = new Variable("X", "human");
		
		r1 = new Relation("likes", s, p);
		r2 = new Relation("likes", p, s);
		r3 = new Relation("likes", new Element("Socrates", "human"), new Element("Plato", "human"));
		
		r4 = new Relation("likes", a, x);
		r5 = new Relation("likes", a, a);
		r6 = new Relation("likes", x, a);
	}

	@Test
	public void testIsValidIn() {
		Equality eq1 = new Equality(s, p);
		assertFalse(eq1.isValidIn(world));
		Stack<Clause> ce1 = eq1.findExplanationFor(world);
		assertFalse(ce1.isEmpty());
		assertEquals(1, ce1.size());
		assertTrue(ce1.contains(new Not(eq1)));
		
		Equality eq2 = new Equality(s, s);
		assertTrue(eq2.isValidIn(world));
		assertTrue(eq2.findExplanationFor(world).isEmpty());
		
		Equality eq3 = new Equality(s, new Element("Socrates", "human"));
		assertTrue(eq3.isValidIn(world));
		assertTrue(eq3.findExplanationFor(world).isEmpty());
		
		Equality eq4 = new Equality(s, new Element("Plato", "human"));
		assertFalse(eq4.isValidIn(world));
		Stack<Clause> ce4 = eq4.findExplanationFor(world);
		assertEquals(1, ce4.size());
		assertTrue(ce4.contains(new Not(eq4)));
		
		
		Equality eq5 = new Equality(r1, r2);
		assertFalse(eq5.isValidIn(world));
		Stack<Clause> ce5 = eq5.findExplanationFor(world);
		assertEquals(1, ce5.size());
		assertTrue(ce5.contains(new Not(eq5)));
		
		Equality eq6 = new Equality(r1, r3);
		assertTrue(eq6.isValidIn(world));
		assertTrue(eq6.findExplanationFor(world).isEmpty());
	}
	
	@Test
	public void testInstantiateVariables() {
		Equality eq1 = new Equality(x, x);
		assertTrue(eq1.isValidIn(world));
		eq1.instantiate(x, a);
		assertTrue(eq1.isValidIn(world));
	}
	
	@Test
	public void testInstantiateVariables2() {
		Equality eq2 = new Equality(a, x);
		assertFalse(eq2.isValidIn(world));
		eq2.instantiate(x, a);
		assertTrue(eq2.isValidIn(world));
	}
	
	@Test
	public void testInstantiateVariables3() {
		Equality eq3 = new Equality(x, a);
		assertFalse(eq3.isValidIn(world));
		Stack<Clause> ex = eq3.findExplanationFor(world);
		assertEquals(1, ex.size());
		assertTrue(ex.contains(new Not(eq3)));
		
		eq3.instantiate(x, a);
		assertTrue(eq3.isValidIn(world));
		assertTrue(eq3.findExplanationFor(world).isEmpty());
	}
	
	@Test
	public void testInstantiateVariables4() {
		Equality eq4 = new Equality(r4, r5);
		assertFalse(eq4.isValidIn(world));
		Stack<Clause> ex = eq4.findExplanationFor(world);
		assertEquals(1, ex.size());
		assertTrue(ex.contains(new Not(eq4)));
		
		eq4.instantiate(x, a);
		assertTrue(eq4.isValidIn(world));
		assertTrue(eq4.findExplanationFor(world).isEmpty());
	}
	
	@Test
	public void testInstantiateVariables5() {
		Equality eq5 = new Equality(r5, r6);
		assertFalse(eq5.isValidIn(world));
		Stack<Clause> ex = eq5.findExplanationFor(world);
		assertEquals(1, ex.size());
		assertTrue(ex.contains(new Not(eq5)));
		
		
		eq5.instantiate(x, a);
		assertTrue(eq5.isValidIn(world));
		assertTrue(eq5.findExplanationFor(world).isEmpty());
	}
	
	@Test
	public void testInstantiateVariables6() {
		Equality eq6 = new Equality(r4, r5);
		assertFalse(eq6.isValidIn(world));
		Stack<Clause> ex = eq6.findExplanationFor(world);
		assertEquals(1, ex.size());
		assertTrue(ex.contains(new Not(eq6)));
		
		eq6.instantiate(x, a);
		assertTrue(eq6.isValidIn(world));
		assertTrue(eq6.findExplanationFor(world).isEmpty());
	}
}
