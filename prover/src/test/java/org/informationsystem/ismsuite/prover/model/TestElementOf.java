package org.informationsystem.ismsuite.prover.model;

import static org.junit.Assert.*;

import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.informationsystem.ismsuite.prover.model.operators.All;
import org.informationsystem.ismsuite.prover.model.operators.ElementOf;
import org.informationsystem.ismsuite.prover.model.operators.Implies;
import org.junit.Test;

public class TestElementOf extends WorldTester {

	@Test
	public void basicTest() {
		FirstOrderLogicWorld world = createWorld();
		
		Element socrates = new Element("Socrates", "human");
		
		ElementOf p1 = new ElementOf(socrates, "human");
		assertTrue(p1.isValidIn(world));
		
		ElementOf p2 = new ElementOf(socrates, "person");
		assertFalse(p2.isValidIn(world));
	}
	
	@Test
	public void formulaTest() {
		World world = createWorld();
		
		Variable varX = new Variable("X", "");
		
		// ! [ X ] : ( philosopher(X) => (X IS IN human ) )
		All all = new All( varX, 
				new Implies(new Relation("philosopher", varX), new ElementOf(varX, "human"))
				);
		System.out.println(all.toString());
		System.out.println(all.findExplanationFor(world));
		assertTrue(all.isValidIn(world));
		
		world.addElement(new Element("JanMartijn", "person"));
		world.addRelation(new Relation("philosopher", new Element("JanMartijn", "person")));
		
		assertFalse(all.isValidIn(world));
	}
}
