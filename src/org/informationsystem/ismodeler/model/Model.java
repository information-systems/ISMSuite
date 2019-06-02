package org.informationsystem.ismodeler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.informationsystem.ismodeler.process.Binding;
import org.informationsystem.ismodeler.process.Binding.BindingElement;
import org.informationsystem.ismodeler.process.BoundTransition;
import org.informationsystem.ismodeler.process.MultiSet;
import org.informationsystem.ismodeler.process.ProcessModel;
import org.informationsystem.ismodeler.process.Token;
import org.informationsystem.ismodeler.specification.Specification;
import org.informationsystem.ismodeler.specification.Transaction;
import org.informationsystem.proving.model.Clause;
import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Variable;
import org.informationsystem.proving.model.World;

public class Model {
		
	private World currentWorld;
	
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
	
	public World getWorld() {
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
	
	void update(World w, Map<BoundTransition,World> enabled, Map<BoundTransition, String> disabled) {
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
