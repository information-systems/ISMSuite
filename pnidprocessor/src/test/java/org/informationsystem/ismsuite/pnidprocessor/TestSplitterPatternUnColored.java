package org.informationsystem.ismsuite.pnidprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.Map;

import org.informationsystem.ismsuite.pnidprocessor.petrinet.MarkedPetriNet;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.junit.Test;


/*
 * Tests the following net:
 * 
 *   (i)-->[A]----->(p)---->[B]-->(f)
 *          |               /|\
 *          |  [C]->(q)->[D] |
 *          |  /|\       |   |3
 *          |    \      /    /
 *          |3    \   |/_   /
 *          \---->(max)----/
 */
public class TestSplitterPatternUnColored {

	@Test
	public void simpleRun() {
		MarkedPetriNet net = createNet();
		
		MultiSet<Token> e1 = new MultiSet<Token>();
		e1.add(new Token());
		
		MultiSet<Token> e2 = new MultiSet<Token>();
		e2.add(new Token(), 2);
		
		MultiSet<Token> e3 = new MultiSet<Token>();
		e3.add(new Token(), 3);
		
		Binding binding = null;
		
		// Assert one token in the marking
		assertEquals(1, net.getMarking().size());
		assertTokenBag(e1, net.getMarking().getTokens("i"));
		
		// Assert only one transition is enabled
		assertEquals(1, net.getBindings().size());
		assertBinding("A", Collections.emptyMap(), net.getBindings().get(0));
		
		net.fire(net.getBindings().get(0));
		
		// Assert one token in p, and 3 in max
		assertEquals(4, net.getMarking().size());
		assertTokenBag(e1, net.getMarking().getTokens("p"));
		assertTokenBag(e3, net.getMarking().getTokens("max"));
		
		// Assert transitions C and B to be enabled.
		assertEquals(2, net.getBindings().size());
		
		for(Binding b : net.getBindings()) {
			if (b.getTransition().equals("B")) {
				assertBinding("B", Collections.emptyMap(), b);
				binding = b;
			} else if (b.getTransition().equals("C")) {
				assertBinding("C", Collections.emptyMap(), b);
			} else {
				fail("Wrong binding");
			}
		}
		assertNotNull(binding);
		
		net.fire(binding);
		
		// Assert one token in the marking
		assertEquals(1, net.getMarking().size());
		assertTokenBag(e1, net.getMarking().getTokens("f"));
		
		// Assert only one transition is enabled
		assertEquals(0, net.getBindings().size());
	}
	
	@Test
	public void longRun() {
		MarkedPetriNet net = createNet();
		
		MultiSet<Token> e1 = new MultiSet<Token>();
		e1.add(new Token());
		
		MultiSet<Token> e2 = new MultiSet<Token>();
		e2.add(new Token(), 2);
		
		MultiSet<Token> e3 = new MultiSet<Token>();
		e3.add(new Token(), 3);
		
		Binding binding = null;
		
		// Assert one token in the marking
		assertEquals(1, net.getMarking().size());
		assertTokenBag(e1, net.getMarking().getTokens("i"));
		
		// Assert only one transition is enabled
		assertEquals(1, net.getBindings().size());
		assertBinding("A", Collections.emptyMap(), net.getBindings().get(0));
		
		net.fire(net.getBindings().get(0));
		
		// Assert one token in p, and 3 in max
		assertEquals(4, net.getMarking().size());
		assertTokenBag(e1, net.getMarking().getTokens("p"));
		assertTokenBag(e3, net.getMarking().getTokens("max"));
		
		// Assert transitions C and B to be enabled.
		assertEquals(2, net.getBindings().size());
		
		for(Binding b : net.getBindings()) {
			if (b.getTransition().equals("B")) {
				assertBinding("B", Collections.emptyMap(), b);
			} else if (b.getTransition().equals("C")) {
				assertBinding("C", Collections.emptyMap(), b);
				binding = b;
			} else {
				fail("Wrong binding");
			}
		}
		assertNotNull(binding);
		
		net.fire(binding);
		binding = null;
		
		// Assert one token in p, one in q and 2 in max
		assertEquals(4, net.getMarking().size());
		assertTokenBag(e1, net.getMarking().getTokens("p"));
		assertTokenBag(e1, net.getMarking().getTokens("q"));
		assertTokenBag(e2, net.getMarking().getTokens("max"));
		
		// Assert only transitions C and D to be enabled.
		assertEquals(2, net.getBindings().size());
		for(Binding b : net.getBindings()) {
			if (b.getTransition().equals("C")) {
				assertBinding("C", Collections.emptyMap(), b);
			} else if (b.getTransition().equals("D")) {
				assertBinding("D", Collections.emptyMap(), b);
				binding = b;
			} else {
				fail("Wrong binding");
			}
		}
		
		assertNotNull(binding);
		net.fire(binding);
		
		// Assert one token in p, and 3 in max
		assertEquals(4, net.getMarking().size());
		assertTokenBag(e1, net.getMarking().getTokens("p"));
		assertTokenBag(e3, net.getMarking().getTokens("max"));
		
		// Assert transitions C and B to be enabled.
		assertEquals(2, net.getBindings().size());
		
		for(Binding b : net.getBindings()) {
			if (b.getTransition().equals("B")) {
				assertBinding("B", Collections.emptyMap(), b);
				binding = b;
			} else if (b.getTransition().equals("C")) {
				assertBinding("C", Collections.emptyMap(), b);

			} else {
				fail("Wrong binding");
			}
		}
		assertNotNull(binding);
		
		net.fire(binding);
		
		// Assert one token in the marking
		assertEquals(1, net.getMarking().size());
		assertTokenBag(e1, net.getMarking().getTokens("f"));
		
		// Assert only one transition is enabled
		assertEquals(0, net.getBindings().size());
		
	}
	
	public void assertTokenBag(MultiSet<Token> expected, MultiSet<Token> actual) {
    	assertEquals(expected.size(), actual.size());
    	for(Token t: expected.getUnique()) {
    		assertEquals(expected.size(t), actual.size(t));
    	}
    }
		
	public void assertBinding(String trans, Map<String, String> valuation, Binding actual) {
    	assertEquals(trans, actual.getTransition());
    	assertEquals(valuation.size(), actual.getValuation().size());
    	
    	for(Map.Entry<String, String> val: valuation.entrySet()) {
    		assertEquals(val.getValue(), actual.getValuation().get(val.getKey()));
    	}
    }
	
	
	public MarkedPetriNet createNet() {
		MarkedPetriNet net = new MarkedPetriNet();
		
		net.addPlace("i");
		net.addPlace("p");
		net.addPlace("max");
		net.addPlace("q");
		net.addPlace("f");
		
		net.addInArc("A", "i");
		net.addOutArc("A", "p");
		net.addOutArc("A", "max", 3);
		
		net.addInArc("B", "p");
		net.addInArc("B", "max", 3);
		net.addOutArc("B", "f");
		
		net.addInArc("C", "max");
		net.addInArc("C", "p");
		net.addOutArc("C", "p");
		net.addOutArc("C", "q");
		
		net.addInArc("D", "q");
		net.addOutArc("D", "max");
		
		// Initial marking
		net.addToken("i");
		
		return net;
	}
	
	
}
