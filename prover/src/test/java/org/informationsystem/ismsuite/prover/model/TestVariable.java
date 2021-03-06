package org.informationsystem.ismsuite.prover.model;

import static org.junit.Assert.*;

import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.junit.Test;

public class TestVariable {

	@Test
	public void testElement() {
		Variable var = new Variable("Someone", "human");
		assertEquals("human", var.getType());
		assertEquals("Someone", var.getLabel());
	}
	
	@Test
	public void testEqualsAndHashCode() {
		Variable soc1 = new Variable("Someone", "human");
		
		assertTrue(soc1.equals(soc1));
		assertTrue(soc1.hashCode() == soc1.hashCode());
		
		assertFalse(soc1.equals(null));
		
		Variable soc2 = new Variable("Someone", "human");
		
		assertTrue(soc1.equals(soc2));
		assertTrue(soc2.equals(soc1));
		
		assertTrue(soc1.hashCode() == soc2.hashCode());
		
		Variable plato = new Variable("Something", "human");
		
		assertFalse(plato.equals(soc1));
		assertFalse(soc1.equals(plato));
		assertFalse(plato.equals(soc2));
		assertFalse(soc2.equals(plato));
		
		assertFalse(soc1.hashCode() == plato.hashCode());
		assertFalse(soc2.hashCode() == plato.hashCode());
		
		Variable dogSoc = new Variable("Someone", "dog");
		
		assertFalse(dogSoc.equals(soc1));
		assertFalse(soc1.equals(dogSoc));
		assertFalse(dogSoc.equals(soc2));
		assertFalse(soc2.equals(dogSoc));
		
		assertFalse(soc1.hashCode() == dogSoc.hashCode());
		assertFalse(soc2.hashCode() == dogSoc.hashCode());
		
		Element realSoc = new Element("Someone", "dog");
		
		assertFalse(realSoc.equals(soc1));
		assertFalse(soc1.equals(realSoc));
		assertFalse(realSoc.equals(soc2));
		assertFalse(soc2.equals(realSoc));
		
		assertFalse(soc1.hashCode() == realSoc.hashCode());
		assertFalse(soc2.hashCode() == realSoc.hashCode());
	}
	

}
