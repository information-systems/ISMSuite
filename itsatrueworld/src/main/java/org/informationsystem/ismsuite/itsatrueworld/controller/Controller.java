package org.informationsystem.ismsuite.itsatrueworld.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorld;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorldListener;
import org.informationsystem.ismsuite.prover.io.ClauseReader;
import org.informationsystem.ismsuite.prover.io.WorldWriter;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.informationsystem.ismsuite.prover.model.World;

public class Controller {
	
	private World world;
	private TrueWorld model;	
	
	public Controller() {
		this(new World());
	}
	
	public Controller(World world) {
		this.world = world;
		this.model = new TrueWorld(this.world);
		
		updateTrueWorldModel();
	}
	
	public void open(InputStream input) throws IOException {
		this.world = ClauseReader.buildWorldFrom(input);
		
		updateTrueWorldModel();
	}
	
	public void export(OutputStream output) {
		WorldWriter.write(world, output);
	}
	
	public void addConjecture(String name, Clause conjecture) {
		this.world.addConjecture(name, conjecture);
		
		updateTrueWorldModel();
	}
	
	public void removeConjecture(String name) {
		this.world.removeConjecture(name);
		
		updateTrueWorldModel();
	}
	
	public void addElement(Element e) {
		this.world.addElement(e);
		
		updateTrueWorldModel();
	}
	
	public void addElement(String name, String type) {
		this.addElement(new Element(name, type));
		
		updateTrueWorldModel();
	}
	
	public void removeElement(String name, String type) {
		this.removeElement(new Element(name, type));
	}
	
	public void removeElement(Element e) {
		this.world.removeElement(e);
		
		updateTrueWorldModel();
	}
	
	public void addRelation(Relation r) {
		this.world.addRelation(r);
		
		updateTrueWorldModel();
	}
	
	public void removeRelation(Relation r) {
		this.world.removeRelation(r);
		
		updateTrueWorldModel(); 
	}
	
	
	private void updateTrueWorldModel() {
		this.model.update(world);
	}
	
	public void register(TrueWorldListener listener) {
		this.model.register(listener);
	}
	
	public void deregister(TrueWorldListener listener) {
		this.model.deregister(listener);
	}
	
	public TrueWorld getModel() {
		return model;
	}

}
