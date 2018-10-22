package org.architecturemining.ismodeler.tests.prover.model;

import static org.junit.jupiter.api.Assertions.*;

import org.architecturemining.ismodeler.proving.model.And;
import org.architecturemining.ismodeler.proving.model.Element;
import org.architecturemining.ismodeler.proving.model.Not;
import org.architecturemining.ismodeler.proving.model.Relation;
import org.architecturemining.ismodeler.proving.model.World;
import org.junit.jupiter.api.Test;

class TestAndOperator extends WorldTester {

	@Test
	void test() {
		World world = createWorld();
		
		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		Element augustine = new Element("Augustine", "human");
		Element hume = new Element("Hume", "human");
		Element descartes = new Element("Descartes", "human");
		
		// TRUE: philosopher( Socrates ) and philosopher( Plato ) and philosopher( Augustine )
		And and1 = new And(
				new Relation("philosopher", socrates),
				new Relation("philosopher", plato),
				new Relation("philosopher", augustine)
				);
		assertTrue(and1.isValidIn(world));
		
		// FALSE: philosopher( Socrates ) and philosopher( Hume )
		And and2 = new And(
				new Relation("philosopher", socrates),
				new Relation("philosopher", hume)
				);
		assertFalse(and2.isValidIn(world));
		
		// TRUE: philosopher( Socrates ) and NOT(philosopher( Hume ))
		And and3 = new And(
				new Relation("philosopher", socrates),
				new Not(new Relation("philosopher", hume))
				);
		assertTrue(and3.isValidIn(world));
	}

}
