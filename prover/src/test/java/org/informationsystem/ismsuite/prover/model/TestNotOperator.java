package org.informationsystem.ismsuite.prover.model;

import static org.junit.Assert.*;

import java.util.Stack;

import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Not;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.informationsystem.ismsuite.prover.model.World;
import org.junit.Test;

public class TestNotOperator extends WorldTester {

	@Test
	public void testNotOperator() {
		World w = createWorld();
		
		Relation relation = new Relation("likes", new Element("Socrates", "human"), new Element("Plato", "human"));
		
		assertFalse(relation.isValidIn(w));
		
		Not not = new Not(relation);
		assertTrue(not.isValidIn(w));
		Stack<Clause> ex = not.findExplanationFor(w);
		assertTrue(ex.isEmpty());
		
		Element soc = new Element("Socrates", "human");
		
		not = new Not(soc);
		assertFalse(not.isValidIn(w));
		ex = not.findExplanationFor(w);
		assertEquals(1, ex.size());
		assertTrue(ex.contains(soc));
		
	}
	
}
