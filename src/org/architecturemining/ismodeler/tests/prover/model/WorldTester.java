package org.architecturemining.ismodeler.tests.prover.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

import org.architecturemining.ismodeler.proving.model.Element;
import org.architecturemining.ismodeler.proving.model.Literal;
import org.architecturemining.ismodeler.proving.model.Not;
import org.architecturemining.ismodeler.proving.model.Relation;
import org.architecturemining.ismodeler.proving.model.World;
import org.junit.jupiter.api.Test;


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
