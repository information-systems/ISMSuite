package org.architecturemining.ismodeler.tests.prover.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

import org.architecturemining.ismodeler.proving.model.Literal;
import org.architecturemining.ismodeler.proving.model.NotOperator;
import org.architecturemining.ismodeler.proving.model.Relation;
import org.architecturemining.ismodeler.proving.model.World;
import org.junit.jupiter.api.Test;

class TestOperators {

	private World constructWorld() {
		World w = new World();
		
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
		
		// Plato likes himself
		ArrayList<Literal> rel4 = new ArrayList<>();
		rel4.add(plato);
		rel4.add(plato);
		
		// Augustine likes himself
		ArrayList<Literal> rel5 = new ArrayList<>();
		rel5.add(augustine);
		rel5.add(augustine);
		
		Relation r1 = new Relation("likes", rel1);
		System.out.println(r1);
		System.out.println(r1.hashCode());
		Relation r2 = new Relation("likes", rel2);
		System.out.println(r2);
		System.out.println(r2.hashCode());
		Relation r3 = new Relation("likes", rel3);
		System.out.println(r3);
		System.out.println(r3.hashCode());
		Relation r4 = new Relation("likes", rel4);
		System.out.println(r4);
		System.out.println(r4.hashCode());
		Relation r5 = new Relation("likes", rel5);
		System.out.println(r5);
		System.out.println(r5.hashCode());
		
		w.addRelation(r1);
		w.addRelation(r2);
		w.addRelation(r3);
		w.addRelation(r4);
		w.addRelation(r5);
		
		return w;
	}
	
	@Test
	void testClauseValidIn() {
		World w = constructWorld();
		
		Literal socrates = new Literal("Socrates");
		Literal plato = new Literal("Plato");
		
		ArrayList<Literal> rel = new ArrayList<>();
		rel.add(plato);
		rel.add(socrates);
		
		Relation testPlatoLikesSocrates = new Relation("likes", rel);
		assertFalse(w.addRelation(testPlatoLikesSocrates));		
		assertTrue(testPlatoLikesSocrates.isValidIn(w));
		
	}
	
	@Test
	void testNotOperator() {
		World w = constructWorld();
		
		Literal socrates = new Literal("Socrates");
		Literal plato = new Literal("Plato");
		
		ArrayList<Literal> rel = new ArrayList<>();
		rel.add(socrates);
		rel.add(plato);
		
		Relation testSocratesLikesPlato = new Relation("likes", rel);
		NotOperator not = new NotOperator(testSocratesLikesPlato);
		
		assertFalse(testSocratesLikesPlato.isValidIn(w));
		assertTrue(not.isValidIn(w));		
	}

}
