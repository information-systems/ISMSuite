/**
 * 
 */
package org.architecturemining.ismodeler.tests.specification;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.architecturemining.ismodeler.InformationSystemModel;
import org.architecturemining.ismodeler.proving.model.ClauseReader;
import org.architecturemining.ismodeler.proving.model.Element;
import org.architecturemining.ismodeler.proving.model.Relation;
import org.architecturemining.ismodeler.proving.model.Variable;
import org.architecturemining.ismodeler.specification.SpecificationReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author jmw
 *
 */
class TestInformationSystem {

	private InformationSystemModel model;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		String specification = "process Philosophers { place Philosopher(p: person) {register p;insert (p) into human;insert (p) into philosopher;}transition newHuman(nu1: person) {register nu1;insert (nu1) into human;}transition newPerson(nu1: person, p: person) {register nu1;insert (nu1) into human;insert (nu1, p) into likes;}transition newPhilosopher(nu1: person) {register nu1;insert (nu1) into human;insert (nu1) into philosopher;}transition Reads(r: person, p: person) {insert (r, p) into likes;}transition Discuss(r: person, p: person) {remove (r, p) from likes;}}";
		String datamodel = "tff( all_philosophers_are_human, conjecture, 	! [X: person]: (  philosopher(X) => human(X) )).tff( like_domain_human, conjecture, 	! [X: person, Y: person] : (likes(X,Y) => ( human(X) & human(Y) ) )).tff( all_humans_not_a_philo_like_a_philo, conjecture,	! [X: person] : ( ~( philosopher(X) )  => ( ? [Y: person] : ( likes(X,Y) & philosopher(Y) ) ) ) ).";
		
		model = new InformationSystemModel(
				SpecificationReader.fromString(specification),
				ClauseReader.buildWorldFrom(datamodel)
				);
		
	}

	@Test
	void testInitialWorld() {
		// World should initially be empty;
		assertTrue(model.isEmpty());
		// Initially all constraints should be valid
		assertEquals(3, model.conjectures().size());
		
	}
	
	@Test
	void createPhilosopher() {
		Map<Variable, Element> binding = new HashMap<>();
		binding.put(new Variable("nu1", "person"), new Element("augustine", "person"));
		String transition = "Philosophers.newPhilosopher";
		assertTrue(model.constructNextWorldFor(transition, binding));
		
		assertEquals(1, model.possibleNextWorlds().size());
		assertTrue(model.possibleNextWorlds().contains(transition));
		
		assertTrue(model.setNextWorld(transition));
		
		Element a = new Element("augustine", "person");
		assertTrue(model.contains(a));
		assertTrue(model.contains(new Relation("human", a)));
		assertTrue(model.contains(new Relation("philosopher", a)));
	}
	
	@Test
	void newHuman() {
		Map<Variable, Element> binding = new HashMap<>();
		binding.put(new Variable("nu1", "person"), new Element("augustine", "person"));
		String transition = "Philosophers.newHuman";
		
		assertFalse(model.constructNextWorldFor(transition, binding));
		
		assertEquals(0, model.possibleNextWorlds().size());

		assertFalse(model.setNextWorld(transition));
		
	}
	
	@Test
	void newPerson() {
		Map<Variable, Element> binding = new HashMap<>();
		binding.put(new Variable("nu1", "person"), new Element("augustine", "person"));
		String transition = "Philosophers.newPhilosopher";
		assertTrue(model.constructNextWorldFor(transition, binding));
		assertTrue(model.setNextWorld(transition));
		
		binding.put(new Variable("nu1", "person"), new Element("janMartijn", "person"));
		binding.put(new Variable("p", "person"), new Element("augustine", "person"));
		transition = "Philosophers.newPerson";

		assertTrue(model.constructNextWorldFor(transition, binding));		
		assertTrue(model.setNextWorld(transition));
		
		Element a = new Element("augustine", "person");
		Element jm = new Element("janMartijn", "person");
		
		assertTrue(model.contains(jm));
		assertTrue(model.contains(new Relation("human", jm)));
		assertTrue(model.contains(new Relation("likes", jm, a)));		
		
	}


}
