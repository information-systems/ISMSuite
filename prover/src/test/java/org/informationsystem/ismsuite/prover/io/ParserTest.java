package org.informationsystem.ismsuite.prover.io;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;

import org.informationsystem.ismsuite.prover.io.ClauseReader;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.informationsystem.ismsuite.prover.model.operators.All;
import org.informationsystem.ismsuite.prover.model.operators.And;
import org.informationsystem.ismsuite.prover.model.operators.Equality;
import org.informationsystem.ismsuite.prover.model.operators.Implies;
import org.informationsystem.ismsuite.prover.model.operators.Not;

public class ParserTest {

	@Test
	public void parsertestSimple() {
		FirstOrderLogicWorld world = ClauseReader.buildWorldFrom(getWorldString());
		testWorld(world);
	}
	
	@Test
	public void testWriter() {
		FirstOrderLogicWorld world = ClauseReader.buildWorldFrom(getWorldString());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		WorldWriter.write(world, out);
		
		String result = new String(out.toByteArray());
		
		FirstOrderLogicWorld newWorld = ClauseReader.buildWorldFrom(result);
		testWorld(newWorld);
		
	}
	
	private String getWorldString() {
		return "tff(a_type, type, a: human)."
				+ "tff(b_type, type, b: human)."
				+ "tff(c_type, type, c: human)."
				+ "tff(d_type, type, d: human)."
				+ "tff( r_a_b, axiom, r(a,b))."
				+ "tff( r_b_c, axiom, r(b,c))."
				+ "tff( r_refl, conjecture, ! [X: human] : ( r(X,X) ) )."
			    + "tff( r_trans, conjecture, ! [X: human, Y: human, Z: human ] : ( ( ( r(X,Y) & r(Y,Z) )  => r(X,Z) ) ) )."
			    + "tff( r_symm, conjecture,  ! [X: human, Y: human, Z: human ] : ( X!=Y ) ).";
	}
	
	private void testWorld(FirstOrderLogicWorld world) {
		
		Element a = new Element("a", "human");
		Element b = new Element("b", "human");
		Element c = new Element("c", "human");
		Element d = new Element("d", "human");
		
		// assert that we have all elements: a, b, c, and d
		assertEquals(4, world.elementSize("human"));
		
		assertTrue(world.contains(a));
		assertTrue(world.contains(b));
		assertTrue(world.contains(c));
		assertTrue(world.contains(d));
		
		Relation r_ab = new Relation("r", a, b);
		Relation r_bc = new Relation("r", b, c);
		
		// assert that we have two axioms
		assertEquals(2, world.relationSize());
		assertTrue(world.contains(r_ab));
		assertTrue(world.contains(r_bc));	
		
		// assert that we have three conjectures
		// assertEquals(3, world.conjectureSize());
		
		Variable x = new Variable("X", "human");
		Variable y = new Variable("Y", "human");
		Variable z = new Variable("Z", "human");
		
		// ! [X: human] : ( r(X,X) )"
		Clause refl = new All(x, new Relation("r", x, x));
		// ! [X: human, Y: human, Z: human ] : ( ( ( r(X,Y) & r(Y,Z) )  => r(X,Z) ) ) )."
		Clause trans = new All(x, new All(y, new All(z, new Implies(new And(new Relation("r",x,y), new Relation("r",y,z)), new Relation("r",x,z)))));
		// ! [X: human, Y: human, Z: human ] : ( X!=Y ) 
		Clause symm = new All(x, new All(y, new All(z, new Not(new Equality(x,y)))));
	
		assertNotNull(world.getConjecture("r_refl"));
		assertEquals(refl, world.getConjecture("r_refl"));
		
		assertNotNull(world.getConjecture("r_trans"));
		assertEquals(trans, world.getConjecture("r_trans"));
		
		assertNotNull(world.getConjecture("r_symm"));
		assertEquals(symm, world.getConjecture("r_symm"));
		
    }
	
}
