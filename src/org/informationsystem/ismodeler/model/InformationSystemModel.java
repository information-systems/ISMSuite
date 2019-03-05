package org.informationsystem.ismodeler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.informationsystem.ismodeler.proving.model.Clause;
import org.informationsystem.ismodeler.proving.model.Element;
import org.informationsystem.ismodeler.proving.model.Variable;
import org.informationsystem.ismodeler.proving.model.World;
import org.informationsystem.ismodeler.specification.Specification;
import org.informationsystem.ismodeler.specification.Transaction;

public class InformationSystemModel {
	
	private Specification specification;
	private World initial;
	
	private World current;
	
	private Map<String,World> nextValidWorlds;

	public InformationSystemModel(Specification spec, World initialWorld) {
		this(spec, initialWorld.getConjectures());
	}
	
	public InformationSystemModel(Specification spec, Map<String, Clause> conjectures) {
		this(spec, conjectures.entrySet());
	}
	
	public InformationSystemModel(Specification spec, Set<Entry<String, Clause>> conjectures) {
		this.specification = spec;
		this.initial = new World();
		for(Entry<String, Clause> e: conjectures ) {
			initial.addConjecture(e.getKey(), e.getValue());
		}
		
		this.current = new World();
		this.nextValidWorlds = new HashMap<>();
	}
		
	public boolean contains(Clause c) {
		return c.isValidIn(current);
	}
	
	public boolean isEmpty() {
		return current.isEmpty();
	}
	
	public boolean isValid() {
		return invalidateWorld(current).isEmpty();
	}
	
	public List<String> invalidates() {
		return invalidateWorld(current);
	}
	
	public Set<String> possibleNextWorlds() {
		return nextValidWorlds.keySet();
	}
	
	public boolean setNextWorld(String next) {
		if (nextValidWorlds.containsKey(next)) {
			
			current = nextValidWorlds.get(next);
			nextValidWorlds = new HashMap<>();
			return true;
			
		} else {
			return false;
		}
	}
	
	public Set<String> getTransitions() {
		return specification.transitions();
	}
	
	public boolean containsTransition(String transition) {
		return specification.containsTransition(transition);
	}
	
	public boolean constructNextWorldFor(String transition, Map<Variable, Element> binding) {
		if (!specification.containsTransition(transition)) {
			return false;
		}
		World next = constructNextWorldFor(specification.getTransactionFor(transition), binding);
		if (validWorld(next)) {
			nextValidWorlds.put(transition, next);
			return true;
		} else {
			return false;
		}
	}
	
	private World constructNextWorldFor(Transaction t, Map<Variable, Element> binding) {
		World next = (World) current.clone();
		t.apply(binding, next);		
		return next;
	}
	
	private boolean validWorld(World world) {
		for(Entry<String, Clause> c: initial.getConjectures()) {
			if (!c.getValue().isValidIn(world)) {
				return false;
			}
		}
		
		return true;
	}
	
	private List<String> invalidateWorld(World world) {
		List<String> invalid = new ArrayList<>();
		
		for(Entry<String, Clause> c: initial.getConjectures()) {
			if (!c.getValue().isValidIn(world)) {
				invalid.add(c.getKey());
			}
		}
		
		return invalid;
	}
	
	public Set<Entry<String,Clause>> conjectures() {
		return initial.getConjectures();
	}

}
