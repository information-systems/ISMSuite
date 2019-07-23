package org.informationsystem.ismsuite.prover.model;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.Stack;

import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Not;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.prover.model.World;
import org.junit.Test;

public class TestWorld {

	@Test
	public void testAddElements() {
		World w = new World();
		assertEquals(0, w.elementSize("human"));
		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		w.addElement(socrates);
		w.addElement(plato);
		
		assertEquals(2, w.elementSize("human"));
		Element plato2 = new Element("Plato", "human");
		w.addElement(plato2);
		assertEquals(2, w.elementSize("human"));
		
		assertTrue(w.contains(plato));
		assertTrue(w.contains(plato2));
		assertTrue(w.contains(new Element("Socrates", "human")));
		assertFalse(w.contains(new Element("Augustine", "human")));

		Stack<Clause> ex = plato.findExplanationFor(w);
		assertTrue(ex.isEmpty());
		
		ex = plato2.findExplanationFor(w);
		assertTrue(ex.isEmpty());
		
		ex = (new Element("Socrates", "human")).findExplanationFor(w);
		assertTrue(ex.isEmpty());
		
		ex = (new Element("Augustine", "human")).findExplanationFor(w);
		assertEquals(1, ex.size());
		// TODO: add assertion that not(Element(Augustine, human)) is in ex.
		assertTrue(ex.contains(new Not(new Element("Augustine", "human"))));
		
		w.removeElement(new Element("Socrates", "human"));
		assertFalse(w.contains(socrates));
		ex = socrates.findExplanationFor(w);
		assertEquals(1, ex.size());
		// TODO: add assertion that not(Element(Socrates, human))
		assertTrue(ex.contains(new Not(new Element("Socrates", "human"))));
	}
	
	@Test
	public void testAddRelations() {
		World w = new World();
		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		Element augustine = new Element("Augustine", "human");
		
		w.addElement(socrates);
		w.addElement(plato);
		w.addElement(augustine);
		
		w.addRelation(new Relation("likes", socrates, socrates));
		w.addRelation(new Relation("likes", plato, plato));
		w.addRelation(new Relation("likes", augustine, augustine));
		
		assertEquals(3, w.relationSize());
		assertEquals(3, w.relations("likes").size());
		Set<Relation> myLikes = w.relations("likes");
				
		assertTrue(w.contains(new Relation("likes", new Element("Socrates","human"), new Element("Socrates", "human"))));
		assertTrue(w.contains(new Relation("likes", new Element("Plato","human"), new Element("Plato", "human"))));
		assertTrue(w.contains(new Relation("likes", new Element("Augustine","human"), new Element("Augustine", "human"))));
		
		assertTrue(myLikes.contains(new Relation("likes", new Element("Socrates","human"), new Element("Socrates", "human"))));
		assertTrue(myLikes.contains(new Relation("likes", new Element("Plato","human"), new Element("Plato", "human"))));
		assertTrue(myLikes.contains(new Relation("likes", new Element("Augustine","human"), new Element("Augustine", "human"))));
		
		assertFalse(w.contains(new Relation("likes", new Element("Augustine","human"), new Element("Plato", "human"))));
		assertFalse(myLikes.contains(new Relation("likes", new Element("Augustine","human"), new Element("Plato", "human"))));
		
		Stack<Clause> ex = (new Relation("likes", new Element("Socrates","human"), new Element("Socrates", "human"))).findExplanationFor(w);
		assertTrue(ex.isEmpty());
		ex = (new Relation("likes", new Element("Plato","human"), new Element("Plato", "human"))).findExplanationFor(w);
		assertTrue(ex.isEmpty());
		ex = (new Relation("likes", new Element("Augustine","human"), new Element("Augustine", "human"))).findExplanationFor(w);
		assertTrue(ex.isEmpty());
		ex = ((new Relation("likes", new Element("Augustine","human"), new Element("Plato", "human")))).findExplanationFor(w);
		assertEquals(1, ex.size());
		assertTrue(ex.contains(new Not(new Relation("likes", new Element("Augustine","human"), new Element("Plato", "human")))));
		
		w.addRelation(new Relation("likes", new Element("Socrates", "human"), new Variable("Someone", "human")));
		
		assertEquals(3, w.relationSize());
		
		w.removeRelation(new Relation("likes", new Element("Socrates", "human"), new Element("Socrates", "human")));

		assertEquals(2, w.relationSize());
		assertEquals(2, w.relations("likes").size());
		
		myLikes = w.relations("likes");
		assertFalse(w.contains(new Relation("likes", new Element("Socrates","human"), new Element("Socrates", "human"))));
		assertFalse(myLikes.contains(new Relation("likes", new Element("Socrates","human"), new Element("Socrates", "human"))));
		
		ex = (new Relation("likes", new Element("Socrates","human"), new Element("Socrates", "human"))).findExplanationFor(w);
		assertEquals(1, ex.size());
		assertTrue(ex.contains(new Not(new Relation("likes", new Element("Socrates","human"), new Element("Socrates", "human")))));
		
	}
	
	@Test
	public void testClone() {
		World w = new World();
		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		Element augustine = new Element("Augustine", "human");
		
		w.addElement(socrates);
		w.addElement(plato);
		w.addElement(augustine);
		
		w.addRelation(new Relation("likes", socrates, socrates));
		w.addRelation(new Relation("likes", plato, plato));
		w.addRelation(new Relation("likes", augustine, augustine));
		
		World w2 = (World) w.clone();
		
		assertEquals(w.elementSize("human"), w2.elementSize("human"));
		
		assertTrue(w2.contains(socrates));
		assertTrue(w2.contains(plato));
		assertTrue(w2.contains(augustine));
		
	}
	
}
