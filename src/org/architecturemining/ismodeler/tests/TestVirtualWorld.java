package org.architecturemining.ismodeler.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

import org.architecturemining.ismodeler.VirtualWorld;
import org.architecturemining.ismodeler.proving.SyntaxException;
import org.architecturemining.ismodeler.model.Constraint;
import org.architecturemining.ismodeler.model.Label;
import org.architecturemining.ismodeler.model.Population;
import org.architecturemining.ismodeler.model.Predicate;
import org.architecturemining.ismodeler.model.Specification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestVirtualWorld {

	@BeforeEach
	void setUp() throws Exception {
		Specification spec = new Specification();
		spec.addValueType("p", "bla");
		
		Label human = new Label("human");
		human.addArgument("P", "p");
		Label philospher = new Label("philospher");
		philospher.addArgument("P", "p");
		
		spec.addLabel(human);
		spec.addLabel(philospher);
		
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		
		Constraint allPhilosophersAreHuman = new Constraint(
				"allPhilosophersAreHuman", 
				"All Philosophers are Human",
				"(! [ P: p ] : philosopher(P) => human(P) )");
		constraints.add(allPhilosophersAreHuman);
		
		world = new VirtualWorld(spec, constraints);
	}
	
	private VirtualWorld world;
	
	/**
	 * Outcome of this test is weird: assumption does not hold,
	 * even if there are entities at all...
	 * TODO Ask the developer of e-prover???
	 *
	@Test
	void testEmptyWorld() {
		try {
			List<String> invalid = world.validate(new Population());
			assertTrue(invalid.isEmpty());
		} catch (SyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	@Test
	void testWorldWithInhabitants() {
		Population pop = new Population();
		pop.addInhabitant("socrates", "p");
		Predicate p = new Predicate("philosopher", new String[] {"socrates"});
		pop.addPredicate(p);
		
		try {
			// This should be false, because there is no clause
			// that states that Socrates is human.
			List<String> invalid1 = world.validate(pop);
			assertFalse(invalid1.isEmpty(), "World with Philosopher Socrates who is no human");
			assertEquals(1, invalid1.size(), "World with philospher Socrates, not human, size not 1");
			assertEquals("allPhilosophersAreHuman", invalid1.get(0), "World with philospher Socrates, not human, wrong constraint ID");
			
			Predicate p2 = new Predicate("human", new String[] { "socrates"});
			pop.addPredicate(p2);
			
			// This should be true now, as there is a new 
			// predicate that states that Socrates is human.
			List<String> invalid2 = world.validate(pop);
			assertTrue(invalid2.isEmpty(), "World with Philosopher Socrates, who is human as well");

		} catch(SyntaxException e) {
			fail(e.getProofText() + "\n\n" + e.getMessage());
		}
	}

}
