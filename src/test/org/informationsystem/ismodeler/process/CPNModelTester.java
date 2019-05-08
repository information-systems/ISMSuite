package test.org.informationsystem.ismodeler.process;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Collection;

import org.informationsystem.ismodeler.process.cpntools.CPNModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CPNModelTester {

	@Test
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
			
			assertEquals(list.size(), 4, "Unexpected number of transitions");
			assertTrue(list.contains("Philosophers.Counter"));
			assertTrue(list.contains("Philosophers.Philosopher"));
			assertTrue(list.contains("Philosophers.Human"));

			model.getCurrentMarking();
			
		} catch (Exception e) {
			fail("CPN Model trows unexpected exception: " + e.getMessage());
		}
	}

}
