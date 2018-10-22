package org.architecturemining.ismodeler.tests.prover.model;

import static org.junit.jupiter.api.Assertions.*;

import org.architecturemining.ismodeler.proving.model.Element;
import org.architecturemining.ismodeler.proving.model.Not;
import org.architecturemining.ismodeler.proving.model.Or;
import org.architecturemining.ismodeler.proving.model.Relation;
import org.architecturemining.ismodeler.proving.model.World;
import org.junit.jupiter.api.Test;

class TestOrOperator extends WorldTester {

	@Test
	void test() {
		World world = createWorld();
		
		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		Element augustine = new Element("Augustine", "human");
		Element hume = new Element("Hume", "human");
		Element descartes = new Element("Descartes", "human");
		
		// TRUE: philosopher( Socrates ) or philosopher( Plato ) or philosopher ( Augustine )
		Or or1 = new Or(
				new Relation("philosopher", socrates),
				new Relation("philosopher", plato),
				new Relation("philosopher", augustine)
		);
		assertTrue(or1.isValidIn(world));
		
		// TRUE: philosopher( Hume ) or philosopher( Plato )
		Or or2 = new Or(
				new Relation("philosopher", hume),
				new Relation("philosopher", plato)
		);
		assertTrue(or2.isValidIn(world));
		
		// FALSE: philosopher ( Hume ) or philosopher ( Descartes )
		Or or3 = new Or(
				new Relation("philosopher", hume),
				new Relation("philosopher", descartes)
		);
		assertFalse(or3.isValidIn(world));
		
		// TRUE: not(philosopher( Hume ) ) or philosopher ( Descartes )
		Or or4 = new Or(
				new Not(new Relation("philosopher", hume)),
				new Relation("philosopher", descartes)
		);
		assertTrue(or4.isValidIn(world));
	}

}
