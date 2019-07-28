package org.informationsystem.ismsuite.ismsuite.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.informationsystem.ismsuite.processengine.process.BoundTransition;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.World;

public class Model {
		
	private FirstOrderLogicWorld currentWorld;
	
	/**
	 * This map contains per transaction the next world.
	 * A transaction is only in this list, if the resulting world is valid.
	 */
	private Map<BoundTransition,World> futureWorlds;
	
	private Map<BoundTransition, String> disabled;
	
	public Model() {
		currentWorld = new World();
		futureWorlds = new HashMap<>();
	}
	
	public FirstOrderLogicWorld getWorld() {
		return currentWorld;
	}
	
	public boolean isEnabled(String transition) {
		for(BoundTransition b: futureWorlds.keySet()) {
			if (b.getName().equals(transition)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEnabled(BoundTransition transition) {
		return futureWorlds.containsKey(transition);
	}
		
	public Set<BoundTransition> enabledTransitions() {
		return futureWorlds.keySet();
	}
	
	public Map<BoundTransition, String> disabledTransitions() {
		return disabled;
	}
	
	void update(FirstOrderLogicWorld w, Map<BoundTransition,World> enabled, Map<BoundTransition, String> disabled) {
		this.currentWorld = w;
		this.futureWorlds = enabled;
		this.disabled = disabled;
		
		notifyStateChanged();
	}
	
	void addFuture(BoundTransition t, World w) {
		futureWorlds.put(t, w);
		notifyStateChanged();
	}
	
	/// Observer pattern
	
	Set<StateChangedListener> listeners = new HashSet<>();
	
	public void addListener(StateChangedListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(StateChangedListener listener) {
		listeners.remove(listener);
	}
	
	private void notifyStateChanged() {
		for(StateChangedListener listener: listeners) {
			listener.update(this);
		}
	}

	public Map<BoundTransition, World> getFutureWorlds() {
		return futureWorlds;
	}

}
