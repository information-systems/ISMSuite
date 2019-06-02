/**
 * 
 */
package test.org.informationsystem.ismodeler.specification;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.informationsystem.ismodeler.model.Controller;
import org.informationsystem.ismodeler.model.Model;
import org.informationsystem.ismodeler.specification.SpecificationReader;
import org.informationsystem.proving.model.ClauseReader;
import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Relation;
import org.informationsystem.proving.model.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author jmw
 *
 */
class TestInformationSystemWithoutProcess {

	private Controller controller;
	private Model model;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		String specification = "process Philosophers { place Philosopher(p: person) {register p;insert (p) into human;insert (p) into philosopher;}transition newHuman(nu1: person) {register nu1;insert (nu1) into human;}transition newPerson(nu1: person, p: person) {register nu1;insert (nu1) into human;insert (nu1, p) into likes;}transition newPhilosopher(nu1: person) {register nu1;insert (nu1) into human;insert (nu1) into philosopher;}transition Reads(r: person, p: person) {insert (r, p) into likes;}transition Discuss(r: person, p: person) {remove (r, p) from likes;} transition removePhilosopher(p: person) { remove (p) from philosopher; remove (p) from human; deregister p; } }";
		String datamodel = "tff( all_philosophers_are_human, conjecture, 	! [X: person]: (  philosopher(X) => human(X) )).tff( like_domain_human, conjecture, 	! [X: person, Y: person] : (likes(X,Y) => ( human(X) & human(Y) ) )).tff( all_humans_not_a_philo_like_a_philo, conjecture,	! [X: person] : ( ~( philosopher(X) )  => ( ? [Y: person] : ( likes(X,Y) & philosopher(Y) ) ) ) ).";
		
		controller = new Controller(
				null,
				SpecificationReader.fromString(specification),
				ClauseReader.buildWorldFrom(datamodel)
				);
		
		model = controller.getModel();
		
	}

	@Test
	void testInitialWorld() {
		// World should initially be empty;
		assertTrue(model.getWorld().isEmpty());
		// Initially all constraints should be valid
		assertEquals(3, controller.conjectures().size());
		assertTrue(controller.isValid());
		
	}
	
	@Test
	void removePhilosopher() {
		Map<Variable, Element> binding = new HashMap<>();
		binding.put(new Variable("nu1", "person"), new Element("augustine", "person"));
		String transition = "Philosophers.newPhilosopher";
		assertTrue(controller.addFutureWorld(transition, binding));
		
		assertEquals(1, model.enabledTransitions().size());
		assertTrue(model.isEnabled(transition));
		
		assertTrue(controller.fire(transition));
		binding.clear();
		binding.put(new Variable("p", "person"), new Element("augustine", "person"));
		transition = "Philosophers.removePhilosopher";
		
		assertTrue(controller.addFutureWorld(transition, binding), "Transition: " + transition + " should be enabled");
		
		assertEquals(1, model.enabledTransitions().size());
		assertTrue(model.isEnabled(transition));
		
		assertTrue(controller.fire(transition));
		
		Element a = new Element("augustine", "person");
		assertFalse(model.getWorld().contains(a));
		assertFalse(model.getWorld().contains(new Relation("human", a)));
		assertFalse(model.getWorld().contains(new Relation("philosopher", a)));
	}
	
	@Test
	void createPhilosopher() {
		Map<Variable, Element> binding = new HashMap<>();
		binding.put(new Variable("nu1", "person"), new Element("augustine", "person"));
		String transition = "Philosophers.newPhilosopher";
		assertTrue(controller.addFutureWorld(transition, binding));
		
		assertEquals(1, model.enabledTransitions().size());
		assertTrue(model.isEnabled(transition));
		
		assertTrue(controller.fire(transition));
		
		Element a = new Element("augustine", "person");
		assertTrue(model.getWorld().contains(a));
		assertTrue(model.getWorld().contains(new Relation("human", a)));
		assertTrue(model.getWorld().contains(new Relation("philosopher", a)));
	}
	
	@Test
	void newHuman() {
		Map<Variable, Element> binding = new HashMap<>();
		binding.put(new Variable("nu1", "person"), new Element("augustine", "person"));
		String transition = "Philosophers.newHuman";
		
		assertFalse(controller.addFutureWorld(transition, binding));
		
		assertEquals(0, model.enabledTransitions().size());

		assertFalse(controller.fire(transition));
		
	}
	
	@Test
	void newPerson() {
		Map<Variable, Element> binding = new HashMap<>();
		binding.put(new Variable("nu1", "person"), new Element("augustine", "person"));
		String transition = "Philosophers.newPhilosopher";
		// assertTrue(model.constructNextWorldFor(transition, binding));
		// assertTrue(model.setNextWorld(transition));
		
		binding.put(new Variable("nu1", "person"), new Element("janMartijn", "person"));
		binding.put(new Variable("p", "person"), new Element("augustine", "person"));
		transition = "Philosophers.newPerson";

		// assertTrue(model.constructNextWorldFor(transition, binding));		
		// assertTrue(model.setNextWorld(transition));
		
		Element a = new Element("augustine", "person");
		Element jm = new Element("janMartijn", "person");
		
		// assertTrue(model.contains(jm));
		// assertTrue(model.contains(new Relation("human", jm)));
		// assertTrue(model.contains(new Relation("likes", jm, a)));		
		
	}


}
