package org.informationsystem.ismsuite.cpnprocessor;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Map;

import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.informationsystem.ismsuite.processengine.process.cpntools.CPNModel;

import org.junit.Test;

@SuppressWarnings("unused")
public class CPNModelTester {

	public void testTransitionList() {
		try {
			CPNModel model = CPNModel.getInstance();
			Collection<String> list = model.getTransitions();
			
			assertEquals("Unexpected number of transitions", list.size(), 4);
			assertTrue(list.contains("Philosophers.newHuman"));
			assertTrue(list.contains("Philosophers.newPerson"));
			assertTrue(list.contains("Philosophers.Reads"));
			assertTrue(list.contains("Philosophers.Discuss"));
			
		} catch (Exception e) {
			fail("CPN Model trows unexpected exception: " + e.getMessage());
		}
	}
	
	public void testPlaceList() {
		try {
			CPNModel model = CPNModel.getInstance();
			Collection<String> list = model.getPlaces();
			
			assertEquals("Unexpected number of places", list.size(), 3);
			assertTrue(list.contains("Philosophers.Counter"));
			assertTrue(list.contains("Philosophers.Philosopher"));
			assertTrue(list.contains("Philosophers.Human"));

		} catch (Exception e) {
			fail("CPN Model trows unexpected exception: " + e.getMessage());
		}
	}
	
	public void testMarkingAndFiring() {
		try {
			CPNModel model = CPNModel.getInstance();
			Map<String, MultiSet<Token>> m = model.getCurrentMarking();
			
			Token p1 = new Token("1");	
			Token p2 = new Token("2");		
			Token p3 = new Token("3");		
			Token p4 = new Token("4");
			
			testPlaceWithMarking("Philosophers.Counter", m, p3, 1);
			
			testPlaceWithMarking("Philosophers.Philosopher", m, p1, 1);
			testPlaceWithMarking("Philosophers.Human", m, p1, 1);
			
			testPlaceWithMarking("Philosophers.Philosopher", m, p2, 1);
			testPlaceWithMarking("Philosophers.Human", m, p2, 1);
			
			Binding b = null;
			for(Binding t: CPNModel.getInstance().getEnabledTransitions()) {
				if (t.getTransition().equals("Philosophers.newHuman")) b = t;
			}
			
			assertNotNull(b);
			CPNModel.getInstance().fire(b);
			
			Map<String, MultiSet<Token>> m2 = model.getCurrentMarking();
			
			testPlaceWithMarking("Philosophers.Philosopher", m2, p1, 1);
			testPlaceWithMarking("Philosophers.Human", m2, p1, 1);
			
			testPlaceWithMarking("Philosophers.Philosopher", m2, p2, 1);
			testPlaceWithMarking("Philosophers.Human", m2, p2, 1);
			
			testPlaceWithMarking("Philosophers.Human", m2, p4, 1);
			
			testPlaceWithMarking("Philosophers.Counter", m2, p4, 1);
			
		} catch (Exception e) {
			fail();
		}
		
	}
	
	public static void stop() throws Exception {
		try {
			CPNModel.getInstance().terminate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testPlaceWithMarking(String place, Map<String, MultiSet<Token>> m, Token t, int count) {
		assertTrue(m.containsKey(place));
		// with value one token carrying value 2
		assertEquals(count, m.get(place).size(t));
	}

}
