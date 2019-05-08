package test.org.informationsystem.ismodeler.process;

import static org.junit.jupiter.api.Assertions.*;

import org.informationsystem.ismodeler.process.cpntools.TokenParser;
import org.junit.jupiter.api.Test;

class TokenParserTest {

	@Test
	void testSingle1id() {
		String s = "1`1";
		TokenParser.parse(s);
	}
	
	
	void testMultiple1id() {
		String s = "1`1++" + System.lineSeparator() + "2`3";
	}
	
	
	void testSingle2ids() {
		String s = "1`(1,2)";
		TokenParser.parse(s);
	}

	
	void testMultiple2ids() {
		String s = "1`(1,2)++" + System.lineSeparator() + "1`(3,4)";
		TokenParser.parse(s);
	}
	
}
