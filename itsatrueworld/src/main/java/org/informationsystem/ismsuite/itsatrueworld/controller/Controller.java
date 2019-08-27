package org.informationsystem.ismsuite.itsatrueworld.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
	
	public void setName(String name) {
		model.setName(name);
		updateTrueWorldModel();
	}
	
	public void open(InputStream input, String name) throws IOException {
		this.world = ClauseReader.buildWorldFrom(input);
		this.model.setName(name);
		
		updateTrueWorldModel();
	}
	
	public void open(InputStream input) throws IOException {
		open(input, "Stream");
	}
	
	public void export(OutputStream output) {
		WorldWriter.write(world, output);
		this.model.setModified(false);
		
		updateTrueWorldModel();
	}
	
	public void addConjecture(String name, Clause conjecture) {
		this.world.addConjecture(name, conjecture);
		
		this.model.setModified(true);
		
		updateTrueWorldModel();
	}
	
	public void updateConjecture(String oldName, String newName, Clause clause) {
		this.world.removeConjecture(oldName);
		this.world.addConjecture(newName, clause);
		
		updateTrueWorldModel();
	}
	
	public void removeConjecture(String name) {
		this.world.removeConjecture(name);
		this.model.setModified(true);
		
		updateTrueWorldModel();
	}
	
	public void addElement(Element e) {
		this.world.addElement(e);
		this.model.setModified(true);
		
		updateTrueWorldModel();
	}
	
	public void addElement(String name, String type) {
		this.addElement(new Element(name, type));
	}
	
	public void removeElement(String name, String type) {
		this.removeElement(new Element(name, type));
	}
	
	public void removeElement(Element e) {
		this.world.removeElement(e);
		this.model.setModified(true);
		
		updateTrueWorldModel();
	}
	
	public void addRelation(Relation r) {
		this.world.addRelation(r);
		this.model.setModified(true);
		
		updateTrueWorldModel();
	}
	
	public void removeRelation(Relation r) {
		this.world.removeRelation(r);
		this.model.setModified(true);
		
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
