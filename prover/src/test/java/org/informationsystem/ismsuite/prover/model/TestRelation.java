package org.informationsystem.ismsuite.prover.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Literal;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.junit.Test;

public class TestRelation {

	@Test
	public void testIsAbstract() {
		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		Relation likes = new Relation("likes", socrates, plato);
		
		assertFalse(likes.isAbstract());
		
		Variable someone = new Variable("Someone", "human");
		Relation likes2 = new Relation("likes", socrates, someone);
		
		assertTrue(likes2.isAbstract());
		
		Element augustine = new Element("Augustine", "human");
		Relation inflAS = new Relation("influences", augustine, new Relation("influences", socrates, plato));
		
		assertFalse(inflAS.isAbstract());
		
		Relation inflAS2 = new Relation("influences", augustine, new Relation("influences", socrates, someone));
		
		assertTrue(inflAS2.isAbstract());
	}

	@Test
	public void testRelationStringListOfLiteral() {
		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		ArrayList<Literal> params = new ArrayList<>();
		params.add(socrates);
		params.add(plato);
		
		Relation likes = new Relation("likes", params);
		
		assertEquals("likes", likes.getLabel());
		assertEquals(2, likes.size());
		
		int i = 0;
		int trues = 0;
		for(Iterator<Literal> it = likes.iterator() ; it.hasNext() ; ) {
			Literal l = it.next();
			switch(i) {
			case 0:
				assertTrue(l.equals(new Element("Socrates", "human")));
				trues++;
				break;
			case 1:
				assertTrue(l.equals(new Element("Plato", "human")));
				trues++;
				break;
			}
			i++;
		}
		assertEquals(2, trues);
	}

	@Test
	public void testRelationStringLiteralArray() {
		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		Relation likes = new Relation("likes", socrates, plato);
		
		assertEquals("likes", likes.getLabel());
		assertEquals(2, likes.size());
		
		int i = 0;
		int trues = 0;
		for(Iterator<Literal> it = likes.iterator() ; it.hasNext() ; ) {
			Literal l = it.next();
			switch(i) {
			case 0:
				assertTrue(l.equals(new Element("Socrates", "human")));
				trues++;
				break;
			case 1:
				assertTrue(l.equals(new Element("Plato", "human")));
				trues++;
				break;
			}
			i++;
		}
		assertEquals(2, trues);
	}

	@Test
	public void testInstantiateParameter() {
		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		Relation likes = new Relation("likes", socrates, plato);
		
		assertFalse(likes.isAbstract());
		
		Variable someone = new Variable("Someone", "human");
		Relation likes2 = new Relation("likes", socrates, someone);
		
		assertTrue(likes2.isAbstract());
		
		
	}
	
	@Test
	public void testEqualsAndHashCode() {
		Element socrates = new Element("Socrates", "human");
		Element plato = new Element("Plato", "human");
		Relation likes1 = new Relation("likes", socrates, plato);
		
		assertTrue(likes1.equals(likes1));
		
		Relation likes2 = new Relation("likes", socrates, plato);
		
		assertTrue(likes1.equals(likes2));
		assertTrue(likes2.equals(likes1));
		
		Variable someone = new Variable("Someone", "human");
		Relation likes3 = new Relation("likes", socrates, someone);
		
		assertFalse(likes3.equals(likes1));
		assertFalse(likes1.equals(likes3));
	}

}

