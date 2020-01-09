package org.informationsystem.ismsuite.cpnprocessor;

import static org.junit.Assert.*;

import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.informationsystem.ismsuite.processengine.process.cpntools.TokenParser;
import org.junit.Test;

public class MarkingParserTest {

	@Test
	public void testSingle1id() {
		String s = "4`2";
		MultiSet<Token> m = TokenParser.parse(s);
		
		Token t = new Token(1);
		t.set(0, 2);
		
		assertEquals(4, m.size());
		assertEquals(4, m.size(t));
	}
	
	@Test
	public void testMultiple1id() {
		String s = "4`3++" + System.lineSeparator() + "2`4";
		
		MultiSet<Token> m = TokenParser.parse(s);
		
		Token t1 = new Token(1);
		t1.set(0, 3);
		
		Token t2 = new Token(1);
		t2.set(0, 4);
		
		assertEquals(6, m.size());
		assertEquals(4, m.size(t1));
		assertEquals(2, m.size(t2));
		
	}
	
	@Test
	public void testSingle2ids() {
		String s = "6`(1,2)";
		MultiSet<Token> m = TokenParser.parse(s);
		
		Token t1 = new Token(2);
		t1.set(0, 1);
		t1.set(1, 2);
		
		assertEquals(6, m.size());
		assertEquals(6, m.size(t1));
	}

	@Test
	public void testMultiple2ids() {
		String s = "4`(6,2)++" + System.lineSeparator() + "2`(3,4)";
		MultiSet<Token> m = TokenParser.parse(s);
		
		Token t1 = new Token(2);
		t1.set(0, 6);
		t1.set(1, 2);
		
		Token t2 = new Token(2);
		t2.set(0, 3);
		t2.set(1, 4);
		
		assertEquals(6, m.size());
		assertEquals(4, m.size(t1));
		assertEquals(2, m.size(t2));
	}
	
}
