package org.informationsystem.ismodeler.tests.prover.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

import org.informationsystem.ismodeler.proving.model.Clause;
import org.informationsystem.ismodeler.proving.model.Element;
import org.informationsystem.ismodeler.proving.model.Literal;
import org.informationsystem.ismodeler.proving.model.Not;
import org.informationsystem.ismodeler.proving.model.Relation;
import org.informationsystem.ismodeler.proving.model.World;
import org.junit.jupiter.api.Test;

class TestNotOperator extends WorldTester {

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
