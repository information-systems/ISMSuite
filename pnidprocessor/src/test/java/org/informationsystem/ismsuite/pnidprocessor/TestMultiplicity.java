package org.informationsystem.ismsuite.pnidprocessor;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.informationsystem.ismsuite.pnidprocessor.petrinet.MarkedPetriNet;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.junit.Test;

public class TestMultiplicity {

	@Test
	public void testMultiplicityRun() {
		MarkedPetriNet net = getPetriNet();
		
		MultiSet<Token> bag = new MultiSet<Token>();
		
		MultiSet<Token> bag0 = new MultiSet<Token>();
		bag0.add(new Token());
		
    	MultiSet<Token> bag1 = new MultiSet<Token>();
    	bag1.add(new Token("e1"));
    	
    	MultiSet<Token> bag12 = new MultiSet<Token>();
    	bag12.add(new Token("e1"), 2);
    	
    	MultiSet<Token> bag2 = new MultiSet<Token>();
    	bag2.add(new Token("e1","e2"));
    	
    	MultiSet<Token> bag22 = new MultiSet<Token>();
    	bag22.add(new Token("e1","e2"), 2);
		
    	System.out.println(net.toString());
    	
        Map<String, String> valuation = new HashMap<>();
        valuation.put("p", "e1");
        valuation.put("q", "e2");
    	
        // The net is empty
        assertEquals(1, net.getMarking().size());
        assertTokenBag(bag0, net.getMarking().getTokens("p4"));
        
        // Only one transition should be enabled: t1
        assertEquals(1, net.getBindings().size());
        
        assertEquals(1, net.getBindings("t1").size());
        assertBinding("t1", valuation, net.getBindings("t1").get(0));
        
        net.fire(net.getBindings("t1").get(0));
        
        assertEquals(3, net.getMarking().size());
        assertEquals(1, net.getMarking().size("p1"));
        assertEquals(2, net.getMarking().size("p2"));
        assertTokenBag(bag1, net.getMarking().getTokens("p1"));
        assertTokenBag(bag22, net.getMarking().getTokens("p2"));
        
        // Only one transition should be enabled: t2
        assertEquals(1, net.getBindings().size());
        
        assertEquals(1, net.getBindings("t2").size());
        assertBinding("t2", valuation, net.getBindings("t2").get(0));
        
        net.fire(net.getBindings("t2").get(0));
        
        assertEquals(3, net.getMarking().size());
        assertEquals(1, net.getMarking().size("p2"));
        assertEquals(2, net.getMarking().size("p3"));
        assertTokenBag(bag2, net.getMarking().getTokens("p2"));
        assertTokenBag(bag12, net.getMarking().getTokens("p3"));
        
        // Only one transition should be enabled: t3
        assertEquals(1, net.getBindings().size());
        
        assertEquals(1, net.getBindings("t3").size());
        assertBinding("t3", valuation, net.getBindings("t3").get(0));
        
        net.fire(net.getBindings("t3").get(0));
        
        assertEquals(1, net.getMarking().size());
        assertEquals(1, net.getMarking().size("p4"));
        assertTokenBag(bag0, net.getMarking().getTokens("p4"));
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
    
	
	
	public MarkedPetriNet getPetriNet() {
		MarkedPetriNet m = new MarkedPetriNet();
		
		m.addInArc("t1", "p4");
		m.addOutArc("t1", "p1", "p");
		m.addOutArc("t1", "p2", 2, "p", "q");
		
		m.addInArc("t2", "p1", "p");
		m.addInArc("t2", "p2", "p","q");
		m.addOutArc("t2", "p3", 2, "p");
		
		m.addInArc("t3", "p2", "p", "q");
		m.addInArc("t3", "p3", 2, "p");
		m.addOutArc("t3", "p4");
		
		m.setCardinality("p1", 1);
		m.setCardinality("p2", 2);
		m.setCardinality("p3", 1);
		m.setCardinality("p4", 0);
		
		m.addToken("p4");
		
		return m;
	}
}
