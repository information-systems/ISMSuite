package org.architecturemining.ismodeler;

import java.util.HashMap;
import java.util.List;

import org.architecturemining.ismodeller.model.ProcessModel;
import org.architecturemining.ismodeller.model.Population;

public class WorldChanger {

	private Population currentPopulation = new Population();
	private HashMap<String,Population> possibleFirings;
	
	private VirtualWorld world;
	private ProcessModel net;
	
	// TODO Add Petri net connection here
	public WorldChanger(VirtualWorld world, ProcessModel net) {
		this.world = world;
		this.net = net;
		this.possibleFirings = new HashMap<String,Population>();
	
		buildInitialPopulation();
		
		calculateActiveTransitions();
	}
	
	private void buildInitialPopulation() {
		
	}
	
	private void calculateActiveTransitions() {
		// calculate all enabled and valid transitions. 
		// These are called *active*.		
	}
	
	public List<String> getActiveTransitions() {
		return null;
	}
	
	public boolean fireTransition(String name) {
		return false;
	}
	
	public Population getPopulation() {
		return this.currentPopulation;
	}
}
