package org.informationsystem.ismsuite.pnidprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.informationsystem.ismsuite.pnidprocessor.petrinet.MarkedPetriNet;
import org.informationsystem.ismsuite.pnidprocessor.petrinet.Marking;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.junit.Test;

public class TestMarkedPetriNet {

	@Test
	public void testBasicPetrinet() {
		// This function creates a basic net: p1[1] -t1-> p2 -t2-> p1
		
		MarkedPetriNet net = getBasicNet();
		Marking m;
		Token blackToken = new Token();
		Binding b;
		
		assertEquals(2, net.places().size());
		assertTrue(net.places().contains("p1"));
		assertTrue(net.places().contains("p2"));
		
		m = net.getMarking();
		
		assertEquals(1, m.getTokens("p1").size());
		assertTrue(m.getTokens("p1").contains(blackToken));
		
		assertEquals(0, m.getTokens("p2").size());
		
		assertEquals(2, net.transitions().size());
		assertTrue(net.transitions().contains("t1"));
		assertTrue(net.transitions().contains("t2"));
		
		assertEquals(1, net.getBindings().size());
		b = net.getBindings().get(0);
		validateBinding("t1", Collections.emptyMap(), b);

		// Fire transition t1
		net.fire(b);
		
		m = net.getMarking();
		assertEquals(0, m.getTokens("p1").size());
		assertEquals(1, m.getTokens("p2").size());
		assertTrue(m.getTokens("p2").contains(blackToken));
		
		assertEquals(1, net.getBindings().size());
		b = net.getBindings().get(0);
		validateBinding("t2", Collections.emptyMap(), b);
		
		net.fire(b);
		m = net.getMarking();
		assertEquals(1, m.getTokens("p1").size());
		assertEquals(0, m.getTokens("p2").size());
		assertTrue(m.getTokens("p1").contains(blackToken));
		
		b = net.getBindings().get(0);
		validateBinding("t1", Collections.emptyMap(), b);
	}
	
	public void validateBinding(String expectedTransition, Map<String, String> expectedValuation, Binding actual) {
		assertEquals(expectedTransition, actual.getTransition());
		assertEquals(expectedValuation.size(), actual.getValuation().size());
		
		for (Map.Entry<String, String> assignment: expectedValuation.entrySet()) {
			assertEquals(assignment.getValue(), actual.getValuation().get(assignment.getKey()));
		}
	}
		
	public static void test2() {
		MarkedPetriNet net = getComplexNet();
		testFirings(net, 10);
	}
	
	public static void printBindings(List<Binding> bindings) {
		for(Binding b: bindings) {
			System.out.println(b);
		}
	}
	
	public static void test1() {
		MarkedPetriNet net = getBasicNet();
		testFirings(net, 10);
	}
	
	public static void testFirings(MarkedPetriNet net, int count) {
		System.out.println(net.print());
		Random random = new Random();
		for(int i = 0; i < count ; i++) {
			List<Binding> bindings = net.getBindings();
			printBindings(bindings);
			if (bindings.size() > 0) {
				int index = random.nextInt(bindings.size());
				net.fire(bindings.get(index));
				System.out.println(net.getMarking().print());
			} else {
				System.out.println("DEAD");
			}
			System.out.println();
		}
	}
	
	public static MarkedPetriNet getBasicNet() {
		MarkedPetriNet net = new MarkedPetriNet();
		
		net.addPlace("p1");
		net.addToken("p1");
		net.addPlace("p2");
		net.addInArc("t1", "p1");
		net.addOutArc("t1", "p2");
		net.addInArc("t2", "p2");
		net.addOutArc("t2", "p1");
		
		return net;
	}
	
	public static MarkedPetriNet getComplexNet() {
		MarkedPetriNet net = new MarkedPetriNet();
		
		net.addPlace("p1",2);
		net.addToken("p1", "e1", "e2");
		net.addToken("p1", "e3", "e2");
		
		net.addPlace("p2",2);
		net.addToken("p2", "e4", "e2");
		net.addToken("p2", "e5", "e7");
		
		net.addPlace("p3",2);
		net.addToken("p3", "e6", "e8");
		net.addToken("p3", "e9", "e10");
		
		net.addPlace("p4");
		net.addToken("p4");
		net.addToken("p4");
		net.addToken("p4");
		
		net.addPlace("p5",2);
		net.addPlace("p6");
		net.addPlace("p7",2);
		net.addPlace("p8",5);
		
		net.addInArc("t1", "p1", "p", "t");
		net.addInArc("t1", "p2", "s", "t");
		net.addInArc("t1", "p3", "a", "v");
		net.addInArc("t1", "p4");
		
		net.addOutArc("t1", "p5", "a","t");
		net.addOutArc("t1", "p6");
		net.addOutArc("t1", "p7", "v","p");
		net.addOutArc("t1", "p8", "v", "a", "s", "t","n");
		
		net.getMarking().setCounter(10);
				
		return net;
	}
	
	public static MarkedPetriNet getSimpleCreatorNet() {
		MarkedPetriNet net = new MarkedPetriNet();
		
		net.addPlace("store");
		for(int i = 0 ; i < 2; i++) {
			net.addToken("store");
		}
		
		net.addPlace("p1",1);
		net.addPlace("p2",1);
		
		net.addInArc("t1", "store");
		net.addOutArc("t1", "p1", "e");
		
		net.addInArc("t2", "p1", "e");
		net.addOutArc("t2", "p2", "e");
		
		net.addInArc("t3", "p2", "e");
		net.addOutArc("t3", "store");
		
		return net;
	}
	
}
