package org.architecturemining.ismodeler.tests.prover.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

import org.architecturemining.ismodeler.proving.model.Element;
import org.architecturemining.ismodeler.proving.model.Literal;
import org.architecturemining.ismodeler.proving.model.Not;
import org.architecturemining.ismodeler.proving.model.Relation;
import org.architecturemining.ismodeler.proving.model.World;
import org.junit.jupiter.api.Test;

class TestNotOperator extends WorldTester {

	@Test
	public void testNotOperator() {
		World w = createWorld();
		
		Relation relation = new Relation("likes", new Element("Socrates", "human"), new Element("Plato", "human"));
		
		assertFalse(relation.isValidIn(w));
		
		Not not = new Not(relation);
		assertTrue(not.isValidIn(w));
	}
	
}
