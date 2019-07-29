package org.informationsystem.ismsuite.itsatrueworld.model;

import java.util.HashSet;
import java.util.Set;

import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;

public class TrueWorld {
	
	private FirstOrderLogicWorld world;
	
	private Set<TrueWorldListener> listeners;
	
	public TrueWorld(FirstOrderLogicWorld world) {
		this.world = world;
		listeners = new HashSet<TrueWorldListener>();
	}
	
	public FirstOrderLogicWorld getWorld() {
		return world;
	}
	
	public TrueWorld update(FirstOrderLogicWorld world) {
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
		for(TrueWorldListener l: listeners) {
			l.notify(this);
		}
	}

}
