package org.informationsystem.ismsuite.prover.model;

import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;

public class WorldTester {

	protected World createWorld() {
		World w = new World();
		
		w.addElement(new Element("Socrates", "human"));
		w.addElement(new Element("Plato", "human"));
		w.addElement(new Element("Augustine", "human"));
		
		w.addRelation(new Relation("philosopher", new Element("Socrates", "human")));
		w.addRelation(new Relation("philosopher", new Element("Plato", "human")));
		w.addRelation(new Relation("philosopher", new Element("Augustine", "human")));
		
		w.addRelation(new Relation("likes", new Element("Socrates", "human"), new Element("Socrates", "human")));
		w.addRelation(new Relation("likes", new Element("Plato", "human"), new Element("Plato", "human")));
		w.addRelation(new Relation("likes", new Element("Augustine", "human"), new Element("Augustine", "human")));
		w.addRelation(new Relation("likes", new Element("Plato", "human"), new Element("Socrates", "human")));
		w.addRelation(new Relation("likes", new Element("Augustine", "human"), new Element("Plato", "human")));
		
		return w;
	}
	
}
