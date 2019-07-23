package org.informationsystem.ismsuite.prover.model;

import org.informationsystem.ismsuite.prover.parser.ClauseReader;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.informationsystem.ismsuite.prover.model.World;

public class ParserTest {

	@Test
	public void testParser() {
	
		World world = ClauseReader.buildWorldFrom(
					  "tff(a_type, type, a: human)."
					+ "tff(b_type, type, b: human)."
					+ "tff(c_type, type, c: human)."
					+ "tff(d_type, type, d: human)."
					+ "tff( r_a_b, axiom, r(a,b))."
					+ "tff( r_b_c, axiom, r(b,c))."
					+ "tff( r_a_b_and_r_b_c, conjecture, r(X,b) & r(b,c) )."
					+ "tff( r_a_b_and_r_b_c_and_r_a_d, conjecture, r(X,b) & r(b,c) & r(a,d) )."
					+ "tff( r_a_b_or_r_b_c, conjecture, r(X,b) | r(b,c) )."
					+ "tff( r_a_b_or_r_b_c_or_r_a_d, conjecture, r(X,b) | r(b,c) | r(a,d) )."
					+ "tff( r_a_b_and_r_b_c_or_r_a_d, conjecture, (r(X,b) & r(b,c)) | r(a,d) )."
					+ "tff( r_refl, conjecture, ! [X: human] : ( r(X,X) ) )."
				    + "tff( r_trans, conjecture, ! [X: human, Y: human, Z: human ] : ( ( ( r(X,Y) & r(Y,Z) )  => r(X,Z) ) ) )."
				    + "tff( r_symm, conjecture,  ! [X: human, Y: human, Z: human ] : ( X!=Y ) )."
				);
		
		// assert that we have all elements: a, b, c, and d
		assertTrue(world.contains(new Element("a", "human")));
		assertTrue(world.contains(new Element("b", "human")));
		assertTrue(world.contains(new Element("c", "human")));
		assertTrue(world.contains(new Element("d", "human")));
		
		// assert that we have two axioms
		
    }
	
}
