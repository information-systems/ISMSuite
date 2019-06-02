package test.org.informationsystem.ismodeler.specification;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.ismodeler.model.Controller;
import org.informationsystem.ismodeler.model.Model;
import org.informationsystem.ismodeler.process.Binding;
import org.informationsystem.ismodeler.process.BoundTransition;
import org.informationsystem.ismodeler.specification.Specification;
import org.informationsystem.ismodeler.specification.SpecificationReader;
import org.informationsystem.ismodeler.specification.Transaction;
import org.informationsystem.proving.model.Clause;
import org.informationsystem.proving.model.ClauseReader;
import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Relation;
import org.informationsystem.proving.model.Variable;
import org.informationsystem.proving.model.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestInformationSystemWithProcess {

	private Controller controller;
	private Model model;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		String specification = "process Philosophers { "
				+ "place Philosopher(p: person) {"
					+ "register p;"
					+ "insert (p) into human;"
					+ "insert (p) into philosopher;"
				+ "}"
				+ "transition newHuman(nu1: person) {"
					+ "register nu1;"
					+ "insert (nu1) into human;"
				+ "}"
				+ "transition newPerson(nu1: person, p: person) {"
					+ "register nu1;"
					+ "insert (nu1) into human;"
					+ "insert (nu1, p) into likes;"
				+ "}"
				+ "transition newPhilosopher(nu1: person) {"
					+ "register nu1;"
					+ "insert (nu1) into human;"
					+ "insert (nu1) into philosopher;"
				+ "}"
				+ "transition Reads(r: person, p: person) {"
					+ "insert (r, p) into likes;"
				+ "}"
				+ "transition Discuss(r: person, p: person) {"
					+ "remove (r, p) from likes;"
				+ "} "
				+ "transition removePhilosopher(p: person) { "
					+ "remove (p) from philosopher; "
					+ "remove (p) from human; "
					+ "deregister p; "
				+ "} "
			+ "}";
		String datamodel = "tff( all_philosophers_are_human, conjecture, 	! [X: person]: (  philosopher(X) => human(X) )).tff( like_domain_human, conjecture, 	! [X: person, Y: person] : (likes(X,Y) => ( human(X) & human(Y) ) )).tff( all_humans_not_a_philo_like_a_philo, conjecture,	! [X: person] : ( ~( philosopher(X) )  => ( ? [Y: person] : ( likes(X,Y) & philosopher(Y) ) ) ) ).";
		
		controller = new Controller(
				new TestProcess(),
				SpecificationReader.fromString(specification),
				ClauseReader.buildWorldFrom(datamodel)
				);
		
		model = controller.getModel();
	}
	
	Element p1 = new Element("person_1", "person");
	Element p2 = new Element("person_2", "person");
	Element p3 = new Element("person_3", "person");
	
	Relation philo1 = new Relation("philosopher", p1);
	Relation philo2 = new Relation("philosopher", p2);
	Relation philo3 = new Relation("philosopher", p3);
	
	Relation human1 = new Relation("human", p1);
	Relation human2 = new Relation("human", p2);
	Relation human3 = new Relation("human", p3);
	
	Relation likes31 = new Relation("likes", p3, p1);
	Relation likes32 = new Relation("likes", p3, p2);
	
	@Test
	void testInitialState() {
		
		assertEquals(2,model.getWorld().elementSize("person"));
		
		// contains 2 elements
		assertTrue(model.getWorld().contains(p1));
		assertTrue(model.getWorld().contains(p2));
		
		assertEquals(4, model.getWorld().relationSize());
		
		// contains 2 humans
		assertTrue(model.getWorld().contains(human1));
		assertTrue(model.getWorld().contains(human2));
		assertTrue(model.getWorld().contains(philo1));
		assertTrue(model.getWorld().contains(philo2));
		
	}
	
	
	@Test
	void testRunRemoveBothPhilosophers() {
		
		validateWorld12(model.getWorld());
		
		String name = "Philosophers.removePhilosopher";
		Binding binding = new Binding();
		binding.set("p", 1);
		BoundTransition brp1 = new BoundTransition(name, binding);
		assertTrue(model.isEnabled(brp1));
		
		assertTrue(controller.fire(brp1));
		
		validateWorld2(model.getWorld());
		
		assertTrue(model.isEnabled(brp1));
		
		System.out.println("Current world");
		System.out.println(model.getWorld());
		
		System.out.println(model.disabledTransitions());
		
		Binding binding2 = new Binding();
		binding2.set("p", 2);
		BoundTransition brp2 = new BoundTransition(name, binding2);
		
		assertTrue(model.isEnabled(brp2), "brp2 enabled");
		assertTrue(controller.fire(brp2), "Firing brp2");
		
		validateEmptyWorld(model.getWorld());
	}
	
	@Test
	void testManualRemoveBothPhilosophers() {
		World w = (World) model.getWorld().clone();
		validateConjectures(w);
		
		validateWorld12(w);
				
		Specification s = controller.getSpecification();
		
		Map<Variable, Element> binding1 = new HashMap<>();
		binding1.put(new Variable("p","person"), p1);
		
		Transaction t = s.getTransactionFor("Philosophers.removePhilosopher");
		
		t.apply(binding1, w);
		validateConjectures(w);
		
		validateWorld2(w);
		
		Map<Variable, Element> binding2 = new HashMap<>();
		binding2.put(new Variable("p","person"), p2);
		
		t.apply(binding2, w);
		validateConjectures(w);
		
		validateEmptyWorld(w);
	}
	
	public void validateWorld12(World w) {
		// contains 0 elements
		assertEquals(2, w.elementSize("person"));
		assertTrue(w.contains(p1));
		assertTrue(w.contains(p2));
		
		
		// contains 0 humans & 0 philosophers
		assertEquals(4, w.relationSize());
		assertTrue(w.contains(human1));
		assertTrue(w.contains(human2));
		assertTrue(w.contains(philo1));
		assertTrue(w.contains(philo2));
	}
	
	
	public void validateWorld2(World w) {
		// contains 0 elements
		assertEquals(1, w.elementSize("person"));
		assertFalse(w.contains(p1));
		assertTrue(w.contains(p2));
		
		
		// contains 0 humans & 0 philosophers
		assertEquals(2, w.relationSize());
		assertFalse(w.contains(human1));
		assertTrue(w.contains(human2));
		assertFalse(w.contains(philo1));
		assertTrue(w.contains(philo2));
	}
	
	public void validateWorld1(World w) {
		// contains 0 elements
		assertEquals(1, w.elementSize("person"));
		assertTrue(w.contains(p1));
		assertFalse(w.contains(p2));
		
		
		// contains 0 humans & 0 philosophers
		assertEquals(2, w.relationSize());
		assertTrue(w.contains(human1));
		assertFalse(w.contains(human2));
		assertTrue(w.contains(philo1));
		assertFalse(w.contains(philo2));
	}
	
	public void validateEmptyWorld(World w) {
		// contains 0 elements
		assertEquals(0, w.elementSize("person"));
		assertFalse(w.contains(p1));
		assertFalse(w.contains(p2));
		
		
		// contains 0 humans & 0 philosophers
		assertEquals(0, w.relationSize());
		assertFalse(w.contains(human1));
		assertFalse(w.contains(human2));
		assertFalse(w.contains(philo1));
		assertFalse(w.contains(philo2));
	}
	
	public void validateConjectures(World w) {
		for(Entry<String, Clause> c : controller.getConjectures()) {
			if (!c.getValue().isValidIn(w)) {
				fail(c.getKey());
			}
		}
	}

}
