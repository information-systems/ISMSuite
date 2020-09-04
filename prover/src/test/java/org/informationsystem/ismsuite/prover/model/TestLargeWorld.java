package org.informationsystem.ismsuite.prover.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import org.informationsystem.ismsuite.prover.io.TFFClauseVisitor;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;


public class TestLargeWorld {

	@Test
	public void testLargeReflexivityCorrect() {
		for (int i = 1; i < 10 ; i += 1) {
			doTest(i * 100000);
		}
	}
	
	public void doTest(int size) {
		
		System.out.println("Size           : " + size);
		
		String formula = "![ E : universe ] : ( e(E) => r(E, E) )";
		
		long tsStart = System.currentTimeMillis();
		
		FirstOrderLogicWorld w = buildReflexiveWorld(size);
		
		long tsEnd = System.currentTimeMillis();
		
		System.out.println("Generation time: " + (tsEnd - tsStart) + " ms");

		TFFClauseVisitor tv = new TFFClauseVisitor(w);
		Clause c = tv.visit(formula);
		
		assertNotNull("Formula cannot be parsed!", c);
		
		tsStart = System.currentTimeMillis();
		
		assertTrue(c.isValidIn(w));
		
		tsEnd = System.currentTimeMillis();
		
		System.out.println("Checking time  : " + (tsEnd - tsStart) + " ms");
	}
	
	private FirstOrderLogicWorld buildReflexiveWorld(int size) {
		World w = new World();
		
		for (int i = 0 ; i < size ; i++) {
			Element e = new Element("e" + i , "universe");
			w.addElement(e);
			
			Relation re = new Relation("e", e);
			w.addRelation(re);
			
			Relation r = new Relation("r", e, e);
			w.addRelation(r);
		}
		
		return w;
	}
	
}
