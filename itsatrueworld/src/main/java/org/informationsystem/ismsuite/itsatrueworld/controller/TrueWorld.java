package org.informationsystem.ismsuite.itsatrueworld.controller;

import java.util.ArrayList;
import java.util.List;

import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;

public class TrueWorld {
	
	private String fileName = "New.tff";
	
	private boolean modified = false;
	
	private FirstOrderLogicWorld world;
	
	private List<TrueWorldListener> listeners;
	
	public TrueWorld(FirstOrderLogicWorld world) {
		this.world = world;
		listeners = new ArrayList<TrueWorldListener>();
	}
	
	public FirstOrderLogicWorld getWorld() {
		return world;
	}
	
	TrueWorld update(FirstOrderLogicWorld world) {
		this.world = world;
		notifyAllListeners();
		return this;
	}
	
	public void register(TrueWorldListener listener) {
		listeners.add(listener);
	}
	
	public void deregister(TrueWorldListener listener) {
		listeners.remove(listener);
	}
	
	private void notifyAllListeners() {
		for(int i = 0 ; i < listeners.size() ; i++) {
			if (listeners.get(i) != null) {
				listeners.get(i).notify(this);
			}
		}
	}
	
	public String getFileName() {
		return fileName;
	}

	TrueWorld setFileName(String name) {
		this.fileName = name;
		return this;
	}
	
	public boolean isModified() {
		return modified;
	}
	
	TrueWorld setModified(boolean modified) {
		this.modified = modified;
		return this;
	}

}
