package org.informationsystem.ismsuite.specification.parser;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.specifier.io.SpecificationReader;
import org.informationsystem.ismsuite.specifier.io.TransactionReader;
import org.informationsystem.ismsuite.specifier.model.InsertOperation;
import org.informationsystem.ismsuite.specifier.model.Operation;
import org.informationsystem.ismsuite.specifier.model.RegisterOperation;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.model.Transaction;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestOperations {

	static Specification spec;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		String specificationString = "process Likes {place persons(p: person) {register p;insert (p) into human;}transition addHuman(p: person) {register p;insert (p) into human;}transition addLikes(p1: person, p2: person) {insert (p1, p2) into likes;}transition removeLikes(p1: person, p2: person) {remove (p1, p2) from likes;}transition removeHuman(p: person) {remove (p) from human;}}";
		
		spec = SpecificationReader.fromString(specificationString);
		
	}
	
	@Test
	public void testReadWriteTransaction() {
		String specificationString = "transaction Likes.addHuman(p: person) { register p; insert (p) into human(p); }";
		
		Transaction t = TransactionReader.fromString(specificationString);
		
		assertNotNull(t);
		assertEquals("Likes.addHuman", t.getLabel());
		assertEquals(1, t.variableSize());
		int counter = 0;
		for(Iterator<Operation> it = t.operations() ; it.hasNext() ; ) {
			Operation o = it.next();
			if (o instanceof RegisterOperation) {
				assertEquals(0, counter);
			} else if (o instanceof InsertOperation) {
				assertEquals(1, counter);
			} else {
				fail("Unknown operation");
			}
			counter++;
		}
	}
	
	@Test
	public void testReadWriteTransactionWithNoProcessName() {
		String specificationString = "transaction addHuman(p: person) { register p; insert (p) into human(p); }";
		
		Transaction t = TransactionReader.fromString(specificationString);
		
		assertNotNull(t);
		assertEquals("addHuman", t.getLabel());
		assertEquals(1, t.variableSize());
		int counter = 0;
		for(Iterator<Operation> it = t.operations() ; it.hasNext() ; ) {
			Operation o = it.next();
			if (o instanceof RegisterOperation) {
				assertEquals(0, counter);
			} else if (o instanceof InsertOperation) {
				assertEquals(1, counter);
			} else {
				fail("Unknown operation");
			}
			counter++;
		}
	}
	
	@Test
	public void testRegister() {
		// System.out.println(spec);
		
		World w = new World();
		
		Transaction t = spec.getTransactionFor("Likes.addHuman");
		assertNotNull(t);
		RegisterOperation register = null;
		for(Iterator<Operation> it = t.operations(); it.hasNext(); ) {
			Operation op = it.next();
			if (op instanceof RegisterOperation) {
				register = (RegisterOperation) op;
			}
		}
		
		assertNotNull(register);
		
		Map<Variable, Element> binding = new HashMap<>();
		binding.put(new Variable("p", "person"), new Element("augustine", "person"));
		
		register.apply(binding, w);
		
		assertTrue(w.contains(new Element("augustine", "person")));
	}
	
	@Test
	public void testInsertAndRemoveHuman() {
		World w = new World();
		
		Transaction t = spec.getTransactionFor("Likes.addHuman");
		
		assertNotNull(t);
		
		Map<Variable, Element> binding = new HashMap<>();
		binding.put(new Variable("p", "person"), new Element("augustine", "person"));
		t.apply(binding, w);
		
		Element a = new Element("augustine", "person");
		
		assertTrue(w.contains(a));
		assertTrue(w.contains(new Relation("human", a)));
		
		spec.getTransactionFor("Likes.removeHuman").apply(binding, w);
		assertFalse(w.contains(new Relation("human", a)));
		
	}

}
