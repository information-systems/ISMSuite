package org.architecturemining.ismodeler.tests.prover.model;

import static org.junit.jupiter.api.Assertions.*;

import org.architecturemining.ismodeler.proving.model.Element;
import org.architecturemining.ismodeler.proving.model.Implies;
import org.architecturemining.ismodeler.proving.model.Relation;
import org.architecturemining.ismodeler.proving.model.World;
import org.junit.jupiter.api.Test;


public class TestImpliesOperator extends WorldTester {

	@Test
	public void testImplies() {
		World world = createWorld();

		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		Element augustine = new Element("Augustine", "human");
		Element hume = new Element("Hume", "human");
		Element descartes = new Element("Descartes", "human");
		
		// TRUE: philosopher ( Augustine ) => likes ( Augustine, Augustine )
		Implies imp1 = new Implies(
				new Relation("philosopher", augustine), 
				new Relation("likes", augustine, augustine));
		assertTrue((new Relation("philosopher", augustine)).isValidIn(world));
		assertTrue((new Relation("likes", augustine, augustine)).isValidIn(world));
		assertTrue(imp1.isValidIn(world));
		
		// FALSE: philosopher ( Socrates ) => likes ( Socrates, Plato )
		Implies imp2 = new Implies(
				new Relation("philosopher", socrates), 
				new Relation("likes", socrates, plato));
		assertTrue((new Relation("philosopher", socrates)).isValidIn(world));
		assertFalse((new Relation("likes", socrates, plato)).isValidIn(world));
		assertFalse(imp2.isValidIn(world));
				
		// TRUE: philosopher(Hume) => likes( Augustine, Plato )
		Implies imp3 = new Implies(
				new Relation("philosopher", hume), 
				new Relation("likes", augustine, plato));
		assertFalse((new Relation("philosopher", hume)).isValidIn(world));
		assertTrue((new Relation("likes", augustine, plato)).isValidIn(world));
		assertTrue(imp3.isValidIn(world));
		
		// TRUE: philosopher(Hume) => likes( Hume, Plato )
		Implies imp4 = new Implies(
				new Relation("philosopher", hume), 
				new Relation("likes", hume, plato));
		assertFalse((new Relation("philosopher", hume)).isValidIn(world));
		assertFalse((new Relation("likes", hume, plato)).isValidIn(world));
		assertTrue(imp4.isValidIn(world));
	}
	
}
