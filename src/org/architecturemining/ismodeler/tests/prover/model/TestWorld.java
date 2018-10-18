package org.architecturemining.ismodeler.tests.prover.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

import org.architecturemining.ismodeler.proving.model.Literal;
import org.architecturemining.ismodeler.proving.model.Relation;
import org.architecturemining.ismodeler.proving.model.Variable;
import org.architecturemining.ismodeler.proving.model.World;
import org.junit.jupiter.api.Test;

class TestWorld {

	@Test
	void testAddElements() {
		World w = new World();
		assertNull(w.getInhabitants("human"));
		
		assertEquals(0, w.getRelations().size());
		
		Literal socrates = new Literal("Socrates");
		Literal plato = new Literal("Plato");
		Literal augustine = new Literal("Augustine");
		
		w.addInhabitant(socrates, "human");
		w.addInhabitant(plato, "human");
		w.addInhabitant(augustine, "human");
		
		// Socrates likes himself
		ArrayList<Literal> rel1 = new ArrayList<>();
		rel1.add(socrates);
		rel1.add(socrates);
		
		// Plato likes Socrates
		ArrayList<Literal> rel2 = new ArrayList<>();
		rel2.add(plato);
		rel2.add(socrates);
		
		// Augustine likes Plato
		ArrayList<Literal> rel3 = new ArrayList<>();
		rel3.add(augustine);
		rel3.add(plato);
		
		Relation r1 = new Relation("likes", rel1);
		Relation r2 = new Relation("likes", rel2);
		Relation r3 = new Relation("likes", rel3);
		
		w.addRelation(r1);
		w.addRelation(r2);
		w.addRelation(r3);
		
		Set<Literal> items = w.getInhabitants("human");
		assertEquals(3, items.size());
		assertTrue(items.contains(plato));
		assertTrue(items.contains(socrates));
		assertTrue(items.contains(augustine));
		
		Set<Relation> rel = w.getRelations();
		assertEquals(3, rel.size());
		assertTrue(rel.contains(r1));
		assertTrue(rel.contains(r2));
		assertTrue(rel.contains(r3));
	}
	
	@Test
	void testAddAbstractItem() {
		World w = new World();
		
		Literal socrates = new Literal("Socrates");
		
		assertTrue(w.addInhabitant(socrates, "human"));
		assertEquals(1, w.getInhabitants("human").size());
		
		Variable someone = new Variable("someone", "human");
		
		assertFalse(w.addInhabitant(someone, "human"));
		assertEquals(1, w.getInhabitants("human").size());
		
		// Socrates likes himself
		ArrayList<Literal> rel1 = new ArrayList<>();
		rel1.add(socrates);
		rel1.add(someone);
		
		Relation r = new Relation("likes", rel1);
		assertFalse(w.addRelation(r));
		assertEquals(0, w.getRelations().size());
	}
}
