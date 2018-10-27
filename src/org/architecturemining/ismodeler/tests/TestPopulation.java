package org.architecturemining.ismodeler.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;

import org.architecturemining.ismodeler.model.Population;
import org.architecturemining.ismodeler.model.Predicate;
import org.architecturemining.ismodeler.proving.Prover;
import org.junit.jupiter.api.Test;

class TestPopulation {

	private HashMap<String, String> inhabitants;
	private HashSet<Predicate> predicates;
	private Population pop;
	
	private void buildPopulation() {
		inhabitants = new HashMap<String,String>();
		inhabitants.put("socrates", "human");
		inhabitants.put("plato", "human");
		
		Predicate p1 = new Predicate("philosopher", new String[] {"socrates"} );
		Predicate p2 = new Predicate("philosopher", new String[] {"plato"} );
		
		predicates = new HashSet<Predicate>();
		predicates.add(p1);
		predicates.add(p2);
		
		pop = new Population(inhabitants, predicates);
	}
	
	@Test
	void testPopulation() {
		Population p = new Population();
		
		assertEquals(0, p.getPredicates().size());
		assertEquals(0, p.getInhabitants().size());
		assertEquals(0, p.size());
		assertTrue(p.isEmpty());
	}

	@Test
	void testPopulationMapOfStringStringSetOfPredicate() {
		buildPopulation();
		
		assertEquals(inhabitants.keySet().size(), pop.size());
		for(String inh : inhabitants.keySet()) {
			assertTrue(pop.containsInhabitant(inh));
			assertEquals(inhabitants.get(inh), pop.getTypeFor(inh));
		}
		
		assertEquals(predicates.size(), pop.getPredicates().size());
		
		for(Predicate p: predicates) {
			assertTrue(pop.containsPredicate(p));
		}
		
		
	}

	@Test
	void testPopulationPopulation() {
		buildPopulation();
		
		Population nextPop = new Population(pop);
		
		assertEquals(inhabitants.keySet().size(), nextPop.size());
		for(String inh : inhabitants.keySet()) {
			assertTrue(nextPop.containsInhabitant(inh));
			assertEquals(inhabitants.get(inh), nextPop.getTypeFor(inh));
		}
		
		assertEquals(predicates.size(), nextPop.getPredicates().size());
		
		for(Predicate p: predicates) {
			assertTrue(nextPop.containsPredicate(p));
		}
	}

	@Test
	void testAddingAndRemoving() {
		buildPopulation();
		
		pop.addInhabitant("augustine", "human");
		assertTrue(pop.containsInhabitant("augustine"));
		assertEquals("human", pop.getTypeFor("augustine"));
		
		Predicate p = new Predicate("philosopher", new String[] {"augustine"});
		pop.addPredicate(p);
		
		assertTrue(pop.containsPredicate(p));
		
		pop.removeInhabitant("augustine");
		assertFalse(pop.containsInhabitant("augustine"));
		
		pop.removePredicate(p);
		assertFalse(pop.containsPredicate(p));
	}

	@Test
	void testGetTFFUniquenessConstraints() {
		buildPopulation();
		pop.addInhabitant("augustine", "human");
		String unique = pop.getTFFUniquenessConstraints();
		
		System.out.println(unique);
		
		assertTrue(unique.indexOf("socrates != plato") > 0, "Socrates equal to Plato");
		assertTrue(unique.indexOf("plato != socrates") > 0, "Plato equal to Socrates");
		assertTrue(unique.indexOf("socrates != augustine") > 0, "Socrates equal to Augustine");
		assertTrue(unique.indexOf("augustine != socrates") > 0, "Augustine equal to Socrates");
		assertTrue(unique.indexOf("plato != augustine") > 0, "Plato equal to Augustine");
		assertTrue(unique.indexOf("augustine != plato") > 0, "Augustine equal to Plato");
		
		assertFalse(unique.indexOf("socrates != socrates") > 0, "Socrates equal to itself");
		assertFalse(unique.indexOf("plato != plato") > 0, "Plato equal to itself");
		assertFalse(unique.indexOf("augustine != augustine") > 0, "Augustine equal to itself");
	}
	
	@Test
	void testToTFF() {
		
	}
}
