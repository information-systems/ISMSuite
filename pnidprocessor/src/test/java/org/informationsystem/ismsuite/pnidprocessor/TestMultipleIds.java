package org.informationsystem.ismsuite.pnidprocessor;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.informationsystem.ismsuite.pnidprocessor.petrinet.MarkedPetriNet;
import org.informationsystem.ismsuite.pnidprocessor.petrinet.Marking;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.junit.Test;

public class TestMultipleIds {

    @Test
    public void testSingleRun() {
    	MarkedPetriNet net = buildPetriNet();
    	Marking m;
    	
    	MultiSet<Token> bag = new MultiSet<Token>();
    	MultiSet<Token> bage1 = new MultiSet<Token>();
    	bage1.add(new Token("e1"));
    	MultiSet<Token> bage2 = new MultiSet<Token>();
    	bage2.add(new Token("e2"));
    	MultiSet<Token> bage1e2 = new MultiSet<Token>();
    	bage1e2.add(new Token("e1","e2"));
    	
    	System.out.println(net.toString());
    	
        Map<String, String> valuation = new HashMap<>();
    	
        // The net is empty
        assertEquals(0, net.getMarking().tokens().size());
        
        // Initially, only transition rt1 is enabled
        assertEquals(1, net.getBindings().size());
        valuation.put("r", "e1");
        assertBinding("rt1", valuation, net.getBindings().get(0));
        
        // Fire transition rt1
        net.fire(net.getBindings().get(0));
        
        // There are two tokens, in o1, and in rp1
        m = net.getMarking();
        assertEquals(2, m.tokens().size());
        assertTokenBag(bage1, m.getTokens("o1"));
        assertTokenBag(bage1, m.getTokens("rp1"));
        
        // Transitions rt1 and pt1 are enabled.
        assertEquals(2, net.getBindings().size());
        valuation.clear();
        valuation.put("r","e2");
        assertEquals(1, net.getBindings("rt1").size());
        assertBinding("rt1", valuation, net.getBindings("rt1").get(0));
        valuation.clear();
        valuation.put("p", "e2");
        valuation.put("r", "e1");
        assertEquals(1, net.getBindings("pt1").size());
        assertBinding("pt1", valuation, net.getBindings("pt1").get(0));
        
        // Fire transition pt1
        net.fire(net.getBindings("pt1").get(0));
        
        // There are three tokens, in rp1, in pp1 and in pp2
        m = net.getMarking();
        assertEquals(3, m.tokens().size());
        assertTokenBag(bage1, m.getTokens("rp1"));
        assertTokenBag(bage2, m.getTokens("pp1"));
        assertTokenBag(bage1e2, m.getTokens("pp2"));
        
        // Transitions rt1 and pt2 are enabled.
        assertEquals(2, net.getBindings().size());
        valuation.clear();
        valuation.put("r","e3");
        assertEquals(1, net.getBindings("rt1").size());
        assertBinding("rt1", valuation, net.getBindings("rt1").get(0));
        valuation.clear();
        valuation.put("p", "e2");
        assertEquals(1, net.getBindings("pt2").size());
        assertBinding("pt2", valuation, net.getBindings("pt2").get(0));
        
        // Fire transition pt2
        net.fire(net.getBindings("pt2").get(0));
        
        // There are three tokens, in rp1, in pp3 and in pp2
        m = net.getMarking();
        assertEquals(3, m.tokens().size());
        assertTokenBag(bage1, m.getTokens("rp1"));
        assertTokenBag(bage2, m.getTokens("pp3"));
        assertTokenBag(bage1e2, m.getTokens("pp2"));
                
        // Transitions rt1 and pt3 are enabled.
        assertEquals(2, net.getBindings().size());
        valuation.clear();
        valuation.put("r","e3");
        assertEquals(1, net.getBindings("rt1").size());
        assertBinding("rt1", valuation, net.getBindings("rt1").get(0));
        valuation.clear();
        valuation.put("p", "e2");
        valuation.put("r", "e1");
        assertEquals(1, net.getBindings("pt3").size());
        assertBinding("pt3", valuation, net.getBindings("pt3").get(0));
        
        // Fire transition pt2
        net.fire(net.getBindings("pt3").get(0));
        
        // There are two tokens, in rp1, and in i1
        m = net.getMarking();
        assertEquals(2, m.tokens().size());
        assertTokenBag(bage1, m.getTokens("rp1"));
        assertTokenBag(bage1, m.getTokens("i1"));
        
        // Transitions rt1 and rt2 are enabled.
        assertEquals(2, net.getBindings().size());
        valuation.clear();
        valuation.put("r","e3");
        assertEquals(1, net.getBindings("rt1").size());
        assertBinding("rt1", valuation, net.getBindings("rt1").get(0));
        valuation.clear();
        valuation.put("r", "e1");
        assertEquals(1, net.getBindings("rt2").size());
        assertBinding("rt2", valuation, net.getBindings("rt2").get(0));

        net.fire(net.getBindings("rt2").get(0));
        
        m = net.getMarking();
        assertEquals(0, m.tokens().size());
        
        valuation.clear();
        valuation.put("r","e3");
        assertEquals(1, net.getBindings("rt1").size());
        assertBinding("rt1", valuation, net.getBindings("rt1").get(0));
        
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
    
    /*
     * Creates the following net:
     * 
     *     rt1   ->  o1  ->  pt1  -> pp1
     *     \/                \/      \/
     *     rp1               pp2     pt2
     *     \/                \/      \/
     *     rt2   <-  i1  <-  pt3     pp3
     */
    private MarkedPetriNet buildPetriNet() {
        MarkedPetriNet net = new MarkedPetriNet();
        
        // Create the requester
        net.addOutArc("rt1", "o1", "r");
        net.addOutArc("rt1", "rp1", "r");
        net.addInArc("rt2", "rp1", "r");
        net.addInArc("rt2", "i1", "r");
        
        net.setCardinality("rp1", 1);
        net.setCardinality("rp1", 1);
        net.setCardinality("i1", 1);
        
        // Create the producer
        net.addInArc("pt1", "o1", "r");
        net.addOutArc("pt1", "pp1", "p");
        net.addOutArc("pt1", "pp2", "r","p");
        net.addInArc("pt2", "pp1", "p");
        net.addOutArc("pt2", "pp3", "p");
        net.addInArc("pt3", "pp2", "r", "p");
        net.addInArc("pt3", "pp3", "p");
        net.addOutArc("pt3", "i1", "r");
        
        net.setCardinality("pp1", 1);
        net.setCardinality("pp3", 1);
        net.setCardinality("pp2", 2);
        
        return net;
    }

}
