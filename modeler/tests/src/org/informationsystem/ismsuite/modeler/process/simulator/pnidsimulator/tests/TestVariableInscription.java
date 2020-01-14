package org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence;
import org.informationsystem.ismsuite.modeler.process.util.SequenceParser;
import org.junit.Test;

public class TestVariableInscription {

	@Test
	public void testMultipleSingleWithSign() {
		String s = "4`a";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNotNull(seq);
		assertEquals(4, seq.getMultiplicity());
		
		assertEquals(1, seq.getVariable().size());
		assertEquals("a", seq.getVariable().get(0).getText());
	}
	
	@Test
	public void testMultipleSingle() {
		String s = "4a";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNotNull(seq);
		assertEquals(4, seq.getMultiplicity());
		
		assertEquals(1, seq.getVariable().size());
		assertEquals("a", seq.getVariable().get(0).getText());
	}
	
	@Test
	public void testMultipleVector() {
		String s = "4(a,def,c)";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNotNull(seq);
		assertEquals(4, seq.getMultiplicity());
		
		assertEquals(3, seq.getVariable().size());
		assertEquals("a", seq.getVariable().get(0).getText());
		assertEquals("def", seq.getVariable().get(1).getText());
		assertEquals("c", seq.getVariable().get(2).getText());
	}
	
	@Test
	public void testMultipleVectorWithSign() {
		String s = "4`(a,def,c)";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNotNull(seq);
		assertEquals(4, seq.getMultiplicity());
		
		assertEquals(3, seq.getVariable().size());
		assertEquals("a", seq.getVariable().get(0).getText());
		assertEquals("def", seq.getVariable().get(1).getText());
		assertEquals("c", seq.getVariable().get(2).getText());
	}
	
	
	@Test
	public void testMultipleSingleton() {
		String s = "4(a)";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNotNull(seq);
		assertEquals(4, seq.getMultiplicity());
		
		assertEquals(1, seq.getVariable().size());
		assertEquals("a", seq.getVariable().get(0).getText());
	}
	
	@Test
	public void testMultipleSingletonWithSign() {
		String s = "4`(a)";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNotNull(seq);
		assertEquals(4, seq.getMultiplicity());
		
		assertEquals(1, seq.getVariable().size());
		assertEquals("a", seq.getVariable().get(0).getText());
	}
	
	@Test
	public void testMultipleEmpty() {
		String s = "2";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNotNull(seq);
		assertEquals(2, seq.getMultiplicity());
	}
	
	@Test
	public void testEmptyString() {
		String s = "";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNotNull(seq);
		assertEquals(0, seq.getVariable().size());
	}
	
	@Test
	public void testEmptyVariableVector() {
		String s = "()";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNull(seq);
	}
	
	@Test
	public void testSingleton() {
		String s = "variable";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNotNull(seq);
		assertEquals(1, seq.getVariable().size());
		assertEquals("variable", seq.getVariable().get(0).getText());
	}
	
	@Test
	public void testSingletonVector() {
		String s = "( variable )";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNotNull(seq);
		assertEquals(1, seq.getVariable().size());
		assertEquals("variable", seq.getVariable().get(0).getText());
	}
	
	@Test
	public void testMultiplicityInscriptions() {
		String s = "(a,b)";
		
		VariableSequence seq = SequenceParser.parse(s);
		
		assertNotNull(seq);
		assertEquals(2, seq.getVariable().size());
		assertEquals("a", seq.getVariable().get(0).getText());
		assertEquals("b", seq.getVariable().get(1).getText());
	}
	
}
