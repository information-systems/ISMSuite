package org.informationsystem.ismsuite.prover.model;

import static org.junit.Assert.*;

import java.util.Stack;

import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.operators.And;
import org.informationsystem.ismsuite.prover.model.operators.Implies;
import org.informationsystem.ismsuite.prover.model.operators.Not;
import org.junit.Test;


public class TestImpliesOperator extends WorldTester {

	@Test
	public void testImplies() {
		FirstOrderLogicWorld world = createWorld();

		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		Element augustine = new Element("Augustine", "human");
		Element hume = new Element("Hume", "human");
		
		// TRUE: philosopher ( Augustine ) => likes ( Augustine, Augustine )
		Implies imp1 = new Implies(
				new Relation("philosopher", augustine), 
				new Relation("likes", augustine, augustine));
		assertTrue((new Relation("philosopher", augustine)).isValidIn(world));
		assertTrue((new Relation("likes", augustine, augustine)).isValidIn(world));
		assertTrue(imp1.isValidIn(world));
		Stack<Clause> ex = imp1.findExplanationFor(world);
		assertTrue(ex.isEmpty());
		
		Relation socP = new Relation("philosopher", socrates);
		Relation likesSP = new Relation("likes", socrates, plato);
		// FALSE: philosopher ( Socrates ) => likes ( Socrates, Plato )
		Implies imp2 = new Implies(	socP, likesSP );
		assertTrue(socP.isValidIn(world));
		assertFalse(likesSP.isValidIn(world));
		assertFalse(imp2.isValidIn(world));
		
		ex = imp2.findExplanationFor(world);
		System.out.println(ex);
		assertEquals(2, ex.size());
		assertTrue(ex.contains(new And(socP, new Not(likesSP))));
		assertTrue(ex.contains(new Not(imp2)));
		
		// TRUE: philosopher(Hume) => likes( Augustine, Plato )
		Implies imp3 = new Implies(
				new Relation("philosopher", hume), 
				new Relation("likes", augustine, plato));
		assertFalse((new Relation("philosopher", hume)).isValidIn(world));
		assertTrue((new Relation("likes", augustine, plato)).isValidIn(world));
		assertTrue(imp3.isValidIn(world));
		assertTrue(imp3.findExplanationFor(world).isEmpty());
		
		// TRUE: philosopher(Hume) => likes( Hume, Plato )
		Implies imp4 = new Implies(
				new Relation("philosopher", hume), 
				new Relation("likes", hume, plato));
		assertFalse((new Relation("philosopher", hume)).isValidIn(world));
		assertFalse((new Relation("likes", hume, plato)).isValidIn(world));
		assertTrue(imp4.isValidIn(world));
		assertTrue(imp4.findExplanationFor(world).isEmpty());
	}
	
}
