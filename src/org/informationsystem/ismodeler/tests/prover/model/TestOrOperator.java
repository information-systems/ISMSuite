package org.informationsystem.ismodeler.tests.prover.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.informationsystem.ismodeler.proving.model.And;
import org.informationsystem.ismodeler.proving.model.Clause;
import org.informationsystem.ismodeler.proving.model.Element;
import org.informationsystem.ismodeler.proving.model.Not;
import org.informationsystem.ismodeler.proving.model.Or;
import org.informationsystem.ismodeler.proving.model.Relation;
import org.informationsystem.ismodeler.proving.model.World;
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
		Stack<Clause> ex = or1.findExplanationFor(world);
		System.out.println(Clause.printStack(ex));
		assertTrue(ex.isEmpty());
		
 		// TRUE: philosopher( Hume ) or philosopher( Plato )
		Or or2 = new Or(
				new Relation("philosopher", hume),
				new Relation("philosopher", plato)
		);
		assertTrue(or2.isValidIn(world));
		ex = or2.findExplanationFor(world);
		assertTrue(ex.isEmpty());
		
		// FALSE: philosopher ( Hume ) or philosopher ( Descartes )
		Or or3 = new Or(
				new Relation("philosopher", hume),
				new Relation("philosopher", descartes)
		);
		assertFalse(or3.isValidIn(world));
		ex = or3.findExplanationFor(world);
		System.out.println(Clause.printStack(ex));
		assertEquals(2, ex.size());
		assertTrue(ex.contains(new And(
				new Not(new Relation("philosopher", hume)),
				new Not(new Relation("philosopher", descartes))
		)));
		assertTrue(ex.contains(new Not(or3)));
		
		// TRUE: not(philosopher( Hume ) ) or philosopher ( Descartes )
		Or or4 = new Or(
				new Not(new Relation("philosopher", hume)),
				new Relation("philosopher", descartes)
		);
		assertTrue(or4.isValidIn(world));
		assertTrue(or4.findExplanationFor(world).isEmpty());
	}

}
