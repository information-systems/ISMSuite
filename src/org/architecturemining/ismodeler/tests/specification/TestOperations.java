package org.architecturemining.ismodeler.tests.specification;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.architecturemining.ismodeler.proving.model.Element;
import org.architecturemining.ismodeler.proving.model.Relation;
import org.architecturemining.ismodeler.proving.model.Variable;
import org.architecturemining.ismodeler.proving.model.World;
import org.architecturemining.ismodeler.specification.Operation;
import org.architecturemining.ismodeler.specification.RegisterOperation;
import org.architecturemining.ismodeler.specification.Specification;
import org.architecturemining.ismodeler.specification.SpecificationReader;
import org.architecturemining.ismodeler.specification.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestOperations {

	Specification spec;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		String specificationString = "process Likes {place persons(p: person) {register p;insert (p) into human;}transition addHuman(p: person) {register p;insert (p) into human;}transition addLikes(p1: person, p2: person) {insert (p1, p2) into likes;}transition removeLikes(p1: person, p2: person) {remove (p1, p2) from likes;}transition removeHuman(p: person) {remove (p) from human;}}";
		
		spec = SpecificationReader.fromString(specificationString);
		
	}
	
	@Test
	void testRegister() {
		World w = new World();
		
		Transaction t = spec.getTransactionFor("Likes.addHuman");
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
	void testInsertAndRemoveHuman() {
		World w = new World();
		
		Transaction t = spec.getTransactionFor("Likes.addHuman");
		
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
