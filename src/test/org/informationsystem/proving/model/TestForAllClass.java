package test.org.informationsystem.proving.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.informationsystem.proving.model.All;
import org.informationsystem.proving.model.And;
import org.informationsystem.proving.model.Clause;
import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Implies;
import org.informationsystem.proving.model.Not;
import org.informationsystem.proving.model.Relation;
import org.informationsystem.proving.model.Variable;
import org.informationsystem.proving.model.World;
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
		
		Element a = new Element("Augustine", "human");
		Element s = new Element("Socrates", "human");
		Element p = new Element("Plato", "human");
		
		Relation lAP = new Relation("likes", a, p);
		Relation lPS = new Relation("likes", p, s);
		Relation lAS = new Relation("likes", a, s);
		
		Variable x = new Variable("X", "human");
		Variable y = new Variable("Y", "human");
		Variable z = new Variable("Z", "human");
		
		Relation lAZ = new Relation("likes", a, z);
		Relation lPZ = new Relation("likes", p, z);
		Relation lYZ = new Relation("likes", y, z);
		Relation lAY = new Relation("likes", a, y);
		Relation lXY = new Relation("likes", x, y);
		Relation lXZ = new Relation("likes", x, z);
		
		// This should be false, as A > P, P > S, but not A > S.
		assertFalse(all.isValidIn(w));
		Stack<Clause> ce1 = all.findExplanationFor(w);
		
		System.out.println(Clause.printStack(ce1));

		
		assertEquals(5, ce1.size());
		assertTrue(ce1.contains(new And(new And(lAP, lPS), new Not(lAS) )));
		assertTrue(ce1.contains(new Not(new Implies(new And(lAP, lPS), lAS))));
		assertTrue(ce1.contains(new Not(new All(z, new Implies(new And(lAP, lPZ), lAZ)))));
		assertTrue(ce1.contains(new Not(new All(y, new All(z, new Implies(new And(lAY, lYZ), lAZ))))));
		assertTrue(ce1.contains(new Not(all)));
		
		// Add a relation A > S
		w.addRelation(
				new Relation("likes", 
						new Element("Augustine", "human"), 
						new Element("Socrates", "human")
				)
		);
		
		// Now it should be true...
		assertTrue(all.isValidIn(w));
		assertTrue(all.findExplanationFor(w).isEmpty());
	}
	
	@Test
	public void testEmptyDomain() {
		World w = createWorld();
		All all = new All(
				new Variable("X", "dog"),
				new Relation("likes", new Variable("X", "dog"), new Variable("X", "dog"))
		);
		
		assertTrue(all.isValidIn(w));
	}

}
