package org.informationsystem.ismsuite.pnidprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.informationsystem.ismsuite.pnidprocessor.petrinet.Marking;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.junit.Test;

public class TestMarking {

	@Test
	public void testCounter() {
		int value = 10;
		Marking m = new Marking();
		m.setCounter(value);
		assertEquals(value, m.getCounter());
		
		int incr = 12;
		
		m.increaseCounter(incr);
		
		assertEquals(value+incr, m.getCounter());
	}
	
	@Test
	public void addTokens() {
		Marking m = new Marking();
		Token t1 = new Token("e1","e2", "e3");
		Token t2 = new Token("e4","e5", "e6");
		Token t3 = new Token("e1","e5", "e3");
		
		m.add("p1", t1);
		
		Set<String> ids = m.getIdentities("p1", 0);
		assertEquals(1, ids.size());
		assertTrue(ids.contains("e1"));
		
		ids = m.getIdentities("p1", 1);
		assertEquals(1, ids.size());
		assertTrue(ids.contains("e2"));
		
		ids = m.getIdentities("p1", 2);
		assertEquals(1, ids.size());
		assertTrue(ids.contains("e3"));
		
		m.add("p1", t2);
		
		ids = m.getIdentities("p1", 0);
		assertEquals(2, ids.size());
		assertTrue(ids.contains("e1"));
		assertTrue(ids.contains("e4"));
		
		ids = m.getIdentities("p1", 1);
		assertEquals(2, ids.size());
		assertTrue(ids.contains("e2"));
		assertTrue(ids.contains("e5"));
		
		ids = m.getIdentities("p1", 2);
		assertEquals(2, ids.size());
		assertTrue(ids.contains("e3"));
		assertTrue(ids.contains("e6"));
		
		m.add("p1", t3);
		
		ids = m.getIdentities("p1", 0);
		assertEquals(2, ids.size());
		assertTrue(ids.contains("e1"));
		assertTrue(ids.contains("e4"));
		
		ids = m.getIdentities("p1", 1);
		assertEquals(2, ids.size());
		assertTrue(ids.contains("e2"));
		assertTrue(ids.contains("e5"));
		
		ids = m.getIdentities("p1", 2);
		assertEquals(2, ids.size());
		assertTrue(ids.contains("e3"));
		assertTrue(ids.contains("e6"));
		
		// Remove token t2
		m.remove("p1", t2);
		
		ids = m.getIdentities("p1", 0);
		assertEquals(1, ids.size());
		assertTrue(ids.contains("e1"));
		
		ids = m.getIdentities("p1", 1);
		assertEquals(2, ids.size());
		assertTrue(ids.contains("e2"));
		assertTrue(ids.contains("e5"));
		
		ids = m.getIdentities("p1", 2);
		assertEquals(1, ids.size());
		assertTrue(ids.contains("e3"));
		
	}
	
	
	
}
