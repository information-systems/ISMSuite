package org.architecturemining.ismodeler.tests.prover.model;

import static org.junit.jupiter.api.Assertions.*;

import org.architecturemining.ismodeler.proving.model.All;
import org.architecturemining.ismodeler.proving.model.And;
import org.architecturemining.ismodeler.proving.model.Element;
import org.architecturemining.ismodeler.proving.model.Implies;
import org.architecturemining.ismodeler.proving.model.Relation;
import org.architecturemining.ismodeler.proving.model.Variable;
import org.architecturemining.ismodeler.proving.model.World;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestForAllClass extends WorldTester {

	@Test
	void test() {
		World w = createWorld();
		
		All all = new All(
				new Variable("X","human"),
				new All(
						new Variable("Y", "human"),
						new All(
								new Variable("Z", "human"),
								new Implies(
										new And(
												new Relation("likes", new Variable("X", "human"), new Variable("Y","human")),
												new Relation("likes", new Variable("Y", "human"), new Variable("Z", "human"))
										),
										new Relation("likes", new Variable("X", "human"), new Variable("Z", "human"))
								)
						)
				)
		);	
		
		// This should be false, as A > P, P > S, but not A > S.
		assertFalse(all.isValidIn(w));
		// Add a relation A > S
		w.addRelation(
				new Relation("likes", 
						new Element("Augustine", "human"), 
						new Element("Socrates", "human")
				)
		);
		
		// Now it should be true...
		assertTrue(all.isValidIn(w));
	}

}
