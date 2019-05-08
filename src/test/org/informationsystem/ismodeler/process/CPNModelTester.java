package test.org.informationsystem.ismodeler.process;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.informationsystem.ismodeler.process.BoundTransition;
import org.informationsystem.ismodeler.process.MultiSet;
import org.informationsystem.ismodeler.process.Token;
import org.informationsystem.ismodeler.process.cpntools.CPNModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CPNModelTester {

	
	void testTransitionList() {
		try {
			CPNModel model = CPNModel.getInstance();
			Collection<String> list = model.getTransitions();
			
			assertEquals(list.size(), 4, "Unexpected number of transitions");
			assertTrue(list.contains("Philosophers.newHuman"));
			assertTrue(list.contains("Philosophers.newPerson"));
			assertTrue(list.contains("Philosophers.Reads"));
			assertTrue(list.contains("Philosophers.Discuss"));
			
		} catch (Exception e) {
			fail("CPN Model trows unexpected exception: " + e.getMessage());
		}
	}
	
	@Test
	void testPlaceList() {
		try {
			CPNModel model = CPNModel.getInstance();
			Collection<String> list = model.getPlaces();
			
			assertEquals(list.size(), 4, "Unexpected number of places");
			assertTrue(list.contains("Philosophers.Counter"));
			assertTrue(list.contains("Philosophers.Philosopher"));
			assertTrue(list.contains("Philosophers.Human"));
			assertTrue(list.contains("Philosophers.test"));

		} catch (Exception e) {
			fail("CPN Model trows unexpected exception: " + e.getMessage());
		}
	}
	
	@Test
	void testMarking() {
		try {
			CPNModel model = CPNModel.getInstance();
			Map<String, MultiSet<Token>> m = model.getCurrentMarking();
			
			// there should be a counter
			Token tCounter = new Token(1);
			tCounter.set(0, 2);
			
			testPlaceWithMarking("Philosophers.Counter", m, tCounter, 1);
			
			Token p1 = new Token(1);
			p1.set(0, 1);
			
			testPlaceWithMarking("Philosophers.Philosopher", m, p1, 1);
			testPlaceWithMarking("Philosophers.Human", m, p1, 1);
			
			Token p2 = new Token(1);
			p2.set(0, 2);
			
			testPlaceWithMarking("Philosophers.Philosopher", m, p1, 1);
			testPlaceWithMarking("Philosophers.Human", m, p1, 1);
			
			Token test1 = new Token(2);
			test1.set(0, 1);
			test1.set(1, 2);
			
			Token test2 = new Token(2);
			test2.set(0, 3);
			test2.set(1, 4);
			assertEquals(2, m.get("Philosophers.test").size());
			
			testPlaceWithMarking("Philosophers.test", m, test1, 1);
			testPlaceWithMarking("Philosophers.test", m, test2, 1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testEnabledTransitions() throws Exception {
		BoundTransition b = null;
		for(BoundTransition t: CPNModel.getInstance().getEnabledTransitions()) {
			System.out.println(t);
			if (t.getName().equals("Philosophers.newHuman")) b = t;
		}

		System.out.println("I fire: " + b);
		
		if (b != null) {
			CPNModel.getInstance().fire(b);
		}
		
		System.out.println(" New Marking: " + CPNModel.getInstance().getCurrentMarking());
		
		for(BoundTransition t: CPNModel.getInstance().getEnabledTransitions()) {
			System.out.println(t);
			b = t;
		}
		
	}
	
	
	@AfterAll
	static void stop() throws Exception {
		try {
			CPNModel.getInstance().terminate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void testPlaceWithMarking(String place, Map<String, MultiSet<Token>> m, Token t, int count) {
		assertTrue(m.containsKey(place));
		// with value one token carrying value 2
		assertEquals(count, m.get(place).size(t));
	}

}
