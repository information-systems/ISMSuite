package org.informationsystem.ismodeler.tests.prover.model;

import static org.junit.jupiter.api.Assertions.*;

import org.informationsystem.ismodeler.proving.model.Element;
import org.junit.jupiter.api.Test;

class TestElement {

	@Test
	void testElement() {
		Element elt = new Element("Socrates", "human");
		assertEquals("human", elt.getType());
		assertEquals("Socrates", elt.getLabel());
		
		assertFalse(elt.isAbstract());
	}
	
	@Test
	void testEqualsAndHashCode() {
		Element soc1 = new Element("Socrates", "human");
		
		assertTrue(soc1.equals(soc1));
		assertTrue(soc1.hashCode() == soc1.hashCode());
		assertFalse(soc1.equals(null));
		
		Element soc2 = new Element("Socrates", "human");
		
		assertTrue(soc1.equals(soc2));
		assertTrue(soc2.equals(soc1));
		
		assertTrue(soc1.hashCode() == soc2.hashCode());
		
		Element plato = new Element("Plato", "human");
		
		assertFalse(plato.equals(soc1));
		assertFalse(soc1.equals(plato));
		assertFalse(plato.equals(soc2));
		assertFalse(soc2.equals(plato));
		
		assertFalse(soc1.hashCode() == plato.hashCode());
		assertFalse(soc2.hashCode() == plato.hashCode());
		
		Element dogSoc = new Element("Socrates", "dog");
		
		assertFalse(dogSoc.equals(soc1));
		assertFalse(soc1.equals(dogSoc));
		assertFalse(dogSoc.equals(soc2));
		assertFalse(soc2.equals(dogSoc));
		
		assertFalse(soc1.hashCode() == dogSoc.hashCode());
		assertFalse(soc2.hashCode() == dogSoc.hashCode());
	}
	

}
