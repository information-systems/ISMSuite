package org.architecturemining.ismodeler.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

import org.architecturemining.ismodeler.VirtualWorld;

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
		
	}

}
