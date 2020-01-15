package org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag;
import org.informationsystem.ismsuite.modeler.process.util.TokenParser;
import org.junit.Test;

public class TestTokenParsing {

	@Test
	public void testSingleNumber() {
		String s = "4";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(4, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(0, t.getEntity().size());
		}
	}
	
	@Test
	public void testSingleNumberWithEmptyVector() {
		String s = "4()";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(4, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(0, t.getEntity().size());
		}
	}
	
	@Test
	public void testSingleNumberWithSingleton() {
		String s = "4(a)";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(4, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(1, t.getEntity().size());
			assertEquals("a", t.getEntity().get(0).getText());
		}
	}
	
	@Test
	public void testSingleNumberWithMultiple() {
		String s = "4(a,b,c)";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(4, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(3, t.getEntity().size());
			assertEquals("a", t.getEntity().get(0).getText());
			assertEquals("b", t.getEntity().get(1).getText());
			assertEquals("c", t.getEntity().get(2).getText());
		}
	}
	
	@Test
	public void testSingleNumberWithSingleElement() {
		String s = "4a";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(4, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(1, t.getEntity().size());
			assertEquals("a", t.getEntity().get(0).getText());
		}
	}
		
	@Test
	public void testSingleNumberWithEmptyVectorAndAmountSign() {
		String s = "4`()";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(4, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(0, t.getEntity().size());
		}
	}
	
	@Test
	public void testSingleNumberWithSingletonAndAmountSign() {
		String s = "4`(a)";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(4, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(1, t.getEntity().size());
			assertEquals("a", t.getEntity().get(0).getText());
		}
	}
	
	@Test
	public void testSingleNumberWithMultipleAndAmountSign() {
		String s = "4`(a,b,c)";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(4, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(3, t.getEntity().size());
			assertEquals("a", t.getEntity().get(0).getText());
			assertEquals("b", t.getEntity().get(1).getText());
			assertEquals("c", t.getEntity().get(2).getText());
		}
	}
	
	@Test
	public void testSingleNumberWithSingleElementAndAmountSign() {
		String s = "4`a";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(4, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(1, t.getEntity().size());
			assertEquals("a", t.getEntity().get(0).getText());
		}
	}
	
	@Test
	public void testMultipleNumberAnd1() {
		String s = "4`a + 3`b";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(7, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(1, t.getEntity().size());
			if (!(t.getEntity().get(0).getText().equals("a") || t.getEntity().get(0).getText().equals("b") ) ) {
				fail("Entity wrong text!");					
			}
		}
	}
	
	@Test
	public void testMultipleNumberAnd2() {
		String s = "4`a ++ 3`b";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(7, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(1, t.getEntity().size());
			if (!(t.getEntity().get(0).getText().equals("a") || t.getEntity().get(0).getText().equals("b") ) ) {
				fail("Entity wrong text!");					
			}
		}
	}
	
	@Test
	public void testMultipleNumberAnd3() {
		String s = "4`a & 3`b";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(7, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(1, t.getEntity().size());
			if (!(t.getEntity().get(0).getText().equals("a") || t.getEntity().get(0).getText().equals("b") ) ) {
				fail("Entity wrong text!");					
			}
		}
	}
	
	@Test
	public void testMultipleNumberAnd4() {
		String s = "4`a && 3`b";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(7, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(1, t.getEntity().size());
			if (!(t.getEntity().get(0).getText().equals("a") || t.getEntity().get(0).getText().equals("b") ) ) {
				fail("Entity wrong text!");					
			}
		}
	}
	
	@Test
	public void testMultipleNumberAnd5() {
		String s = "4`a && 3`b";
		TokenBag b = TokenParser.parse(s);
		assertNotNull(b);
		assertEquals(7, b.getToken().size());
		
		for(Token t: b.getToken()) {
			assertEquals(1, t.getEntity().size());
			if (!(t.getEntity().get(0).getText().equals("a") || t.getEntity().get(0).getText().equals("b") ) ) {
				fail("Entity wrong text!");					
			}
		}
	}
}
