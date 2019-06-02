package test.org.informationsystem.proving.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.informationsystem.proving.model.All;
import org.informationsystem.proving.model.And;
import org.informationsystem.proving.model.Clause;
import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Exists;
import org.informationsystem.proving.model.Implies;
import org.informationsystem.proving.model.Not;
import org.informationsystem.proving.model.Relation;
import org.informationsystem.proving.model.Variable;
import org.informationsystem.proving.model.World;
import org.junit.jupiter.api.Test;

class TestExistsOperator extends WorldTester {

	@Test
	void test() {
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
		World w = createWorld();
		Exists exists = new Exists(
				new Variable("X", "dog"),
				new Relation("likes", new Variable("X", "dog"), new Variable("X", "dog"))
		);
		
		assertFalse(exists.isValidIn(w));
	}
	
}
