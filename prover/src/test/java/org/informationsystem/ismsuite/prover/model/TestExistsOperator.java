package org.informationsystem.ismsuite.prover.model;

import static org.junit.Assert.*;

import java.util.Stack;

import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.informationsystem.ismsuite.prover.model.operators.All;
import org.informationsystem.ismsuite.prover.model.operators.And;
import org.informationsystem.ismsuite.prover.model.operators.Exists;
import org.informationsystem.ismsuite.prover.model.operators.Implies;
import org.informationsystem.ismsuite.prover.model.operators.Not;
import org.junit.Test;

public class TestExistsOperator extends WorldTester {

	@Test
	public void test() {
		World w = createWorld();
		
		Exists exists = new Exists(
				new Variable("X","human"),
				new Exists(
						new Variable("Y", "human"),
						new Exists(
								new Variable("Z", "human"),
								new Not(
									new Implies(
											new And(
													new Relation("likes", new Variable("X", "human"), new Variable("Y","human")),
													new Relation("likes", new Variable("Y", "human"), new Variable("Z", "human"))
											),
											new Relation("likes", new Variable("X", "human"), new Variable("Z", "human"))
									)
								)
						)
				)
		);
		// This should be false, as A > P, P > S, but not A > S.
		assertTrue(exists.isValidIn(w));
		Stack<Clause> ce1 = exists.findExplanationFor(w);
		assertTrue(ce1.isEmpty());
		
		// Add relation A > S
		w.addRelation(
				new Relation("likes", 
						new Element("Augustine", "human"), 
						new Element("Socrates", "human")
				)
		);

		// Now it should be true...
		assertFalse(exists.isValidIn(w));
		ce1 = exists.findExplanationFor(w);
		assertEquals(1, ce1.size());
		
		All all = new All(
				new Variable("X", "human"),
				new Not(
						new Exists(
								new Variable("Y", "human"),
								new Exists(
										new Variable("Z", "human"),
										new Not(new Implies (
												new And(
														new Relation("likes", new Variable("X", "human"), new Variable("Y", "human")),
														new Relation("likes", new Variable("Y", "human"), new Variable("Z", "human"))
												),
												new Relation("likes", new Variable("X", "human"), new Variable("Z", "human"))
												)
										)
								)
						)
				)
		);
		assertTrue(ce1.contains(all));
	}

	@Test
	public void testEmptyDomain() {
		FirstOrderLogicWorld w = createWorld();
		Exists exists = new Exists(
				new Variable("X", "dog"),
				new Relation("likes", new Variable("X", "dog"), new Variable("X", "dog"))
		);
		
		assertFalse(exists.isValidIn(w));
	}
	
}
