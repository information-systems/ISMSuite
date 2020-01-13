package org.informationsystem.ismsuite.prover.model;

import static org.junit.Assert.*;

import java.util.Stack;

import org.informationsystem.ismsuite.prover.model.And;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Not;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.junit.Test;

public class TestAndOperator extends WorldTester {

	@Test
	public void test() {
		FirstOrderLogicWorld world = createWorld();
		
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
		Stack<Clause> ex = and1.findExplanationFor(world); 
		assertTrue(ex.isEmpty());
		
		// FALSE: philosopher( Socrates ) and philosopher( Hume )
		And and2 = new And(
				new Relation("philosopher", socrates),
				new Relation("philosopher", hume)
				);
		assertFalse(and2.isValidIn(world));
		ex = and2.findExplanationFor(world);
		//System.out.println(ex);
		assertEquals(3, ex.size());
		ex.contains(new Not(hume));
		ex.contains(new Not(new Relation("philosopher", hume)));
		ex.contains(new Not(and2));
		
		// TRUE: philosopher( Socrates ) and NOT(philosopher( Hume ))
		And and3 = new And(
				new Relation("philosopher", socrates),
				new Not(new Relation("philosopher", hume))
				);
		assertTrue(and3.isValidIn(world));
		assertTrue(and3.findExplanationFor(world).isEmpty());
		
		And and4 = new And(
				new Relation("philosopher", hume),
				new Relation("philosopher", descartes)
				);
		assertFalse(and4.isValidIn(world));
		ex = and4.findExplanationFor(world);
		assertEquals(3, ex.size());
		assertTrue(ex.contains(new Not(descartes)));
		assertTrue(ex.contains(new Not(new Relation("philosopher", descartes))));
		assertTrue(ex.contains(new Not(and4)));
	}
	

}
