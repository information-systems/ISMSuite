package org.informationsystem.ismsuite.ismsuite.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.World;

public class Model {
		
	private FirstOrderLogicWorld currentWorld;
	
	/**
	 * This map contains per transaction the next world.
	 * A transaction is only in this list, if the resulting world is valid.
	 */
	private Map<Binding,World> futureWorlds;
	
	private Map<Binding, String> disabled;
	
	public Model() {
		currentWorld = new World();
		futureWorlds = new HashMap<>();
	}
	
	public FirstOrderLogicWorld getWorld() {
		return currentWorld;
	}
	
	public boolean isEnabled(String transition) {
		for(Binding b: futureWorlds.keySet()) {
			if (b.getTransition().equals(transition)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEnabled(Binding transition) {
		return futureWorlds.containsKey(transition);
	}
		
	public Set<Binding> enabledTransitions() {
		return futureWorlds.keySet();
	}
	
	public Map<Binding, String> disabledTransitions() {
		return disabled;
	}
	
	void update(FirstOrderLogicWorld w, Map<Binding,World> enabled, Map<Binding, String> disabled) {
		this.currentWorld = w;
		this.futureWorlds = enabled;
		this.disabled = disabled;
		
		notifyStateChanged();
	}
	
	void addFuture(Binding t, World w) {
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

	public Map<Binding, World> getFutureWorlds() {
		return futureWorlds;
	}

}
