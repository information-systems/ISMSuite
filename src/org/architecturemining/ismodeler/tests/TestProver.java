/**
 * 
 */
package org.architecturemining.ismodeler.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.architecturemining.ismodeler.proving.GeneralProverException;
import org.architecturemining.ismodeler.proving.Prover;
import org.architecturemining.ismodeler.proving.SyntaxException;
import org.junit.jupiter.api.Test;

/**
 * @author Werf0006
 *
 */
class TestProver {

	/**
	 * Test method for {@link org.architecturemining.ismodeler.proving.Prover#proof(java.lang.String)}.
	 */
	@Test
	void testProofStringProof() {
		String theorem = "fof(wealldie, axiom, ![X]:(human(X) => mortal(X))). fof(socrateshuman, axiom, ![X]:(human(socrates))). fof(socratesdies, conjecture, mortal(socrates)).";
		boolean result;
		try {
			result = Prover.getInstance().proof(theorem);
			assertTrue(result);
		} catch (SyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralProverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for {@link org.architecturemining.ismodeler.proving.Prover#proof(java.lang.String)}.
	 */
	@Test
	void testProofStringNoProof() {
		String theorem = "fof(somepeopledie, axiom, ?[X]:(human(X) => mortal(X))). fof(socrateshuman, axiom, ![X]:(human(socrates))). fof(socratesdies, conjecture, mortal(socrates)).";
		boolean result;
		try {
			result = Prover.getInstance().proof(theorem);
			assertFalse(result);
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("I did not expect a syntax-exception: " + e.getMessage());
		} catch (GeneralProverException e) {
			fail("I did not expect a general-exception: " + e.getMessage());
		}
	}
	
	@Test
	void testProofwithSyntaxError() {
		String theorem = "fof(somepeopledie, axiom, ?[X]:(human(X) => mortal(X))). fof(socrateshuman, axiom, ![X]:(human(socrates))). fof(socratesdies, conjecture, mortal(socrates)). dd";
		boolean result;
		try {
			result = Prover.getInstance().proof(theorem);
		} catch (SyntaxException e) {
			e.printStackTrace();
			assertTrue(true, e.getMessage());
		} catch (GeneralProverException e) {
			fail("I did not expect a general-exception: " + e.getMessage());
		}
	}

	/**
	 * Test method for {@link org.architecturemining.ismodeler.proving.Prover#proof(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testProofStringStringNoProof() {
		String axioms = "fof(wealldie, axiom, ![X]:(human(X) => mortal(X))). fof(socrateshuman, axiom, ![X]:(human(socrates))).";
		String theorem = "fof(socratesdies, conjecture, mortal(socrates)).";
		boolean result;
		try {
			result = Prover.getInstance().proof(axioms, theorem);
			assertTrue(result);
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("I did not expect a syntax-exception: " + e.getMessage());
		} catch (GeneralProverException e) {
			fail("I did not expect a general-exception: " + e.getMessage());
		}
		
		
	}
	
	/**
	 * Test method for {@link org.architecturemining.ismodeler.proving.Prover#proof(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testProofStringStringProof() {
		String axioms = "fof(somepeopledie, axiom, ?[X]:(human(X) => mortal(X))). fof(socrateshuman, axiom, ![X]:(human(socrates))).";
		String theorem = "fof(socratesdies, conjecture, mortal(socrates)).";
		boolean result;
		try {
			result = Prover.getInstance().proof(axioms, theorem);
			assertFalse(result);
		} catch (SyntaxException e) {
			e.printStackTrace();
			fail("I did not expect a syntax-exception: " + e.getMessage());
		} catch (GeneralProverException e) {
			fail("I did not expect a general-exception: " + e.getMessage());
		}
		
		
	}

	/**
	 * Test method for {@link org.architecturemining.ismodeler.proving.Prover#getInstance()}.
	 * I should always get the same instance, as it implements the Singleton pattern.
	 */
	@Test
	void testGetInstance() {
		Prover prover = Prover.getInstance();
		Prover prover2 = Prover.getInstance();
		
		assertEquals(prover, prover2);
	}

}
