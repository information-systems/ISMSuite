package org.informationsystem.ismsuite.prover.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import org.informationsystem.ismsuite.prover.io.WorldWriter;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Literal;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.literals.storage.LiteralStore;

public class World implements FirstOrderLogicWorld {
	
	private HashMap<String, Clause> conjectures;
	
	private HashMap<String, LiteralStore<Element>> types;
	/**
	 * Map of all elements: label -> type
	 */
	private HashMap<String, Element> elements;
	private HashSet<Relation> relations;
	private HashMap<String, Set<Relation>> namedRelations;
	
	
	public World() {
		conjectures = new HashMap<>();
		types = new HashMap<>();
		elements = new HashMap<>();
		relations = new HashSet<>();
		namedRelations = new HashMap<>();
	}
	

	@Override
	public boolean contains(Literal l) {
		if (l instanceof Element) {
			return containsElement((Element) l);
		} else if (l instanceof Relation) {
			return containsRelation((Relation) l);
		}
		return false;
	}
	

	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}
	
	@Override
	public Object clone() {
		World w = new World();
		
		for(Entry<String, Clause> c: this.conjectures.entrySet()) {
			w.addConjecture(c.getKey(), c.getValue());
		}
		
		for(String type: types.keySet()) {
			Iterator<Element> it = types.get(type).iterator();
			while(it.hasNext()) {
				w.addElement(it.next());
			}
		}
		
		for(Relation rel: relations) {
			w.addRelation(rel);
		}
		
		return w;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	/// Conjectures
	/////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Set<Entry<String, Clause>> getConjectures() {
		return conjectures.entrySet();
	}
	
	@Override
	public Clause getConjecture(String name) {
		if (conjectures.containsKey(name)) {
			return conjectures.get(name);
		}
		return null;
	}
	
	public boolean addConjecture(String name, Clause c) {
		conjectures.put(name, c);
		return true;
	}
	
	public boolean removeConjecture(String name) {
		conjectures.remove(name);
		return true;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	/// Types and elements
	/////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public boolean containsType(String type) {
		return types.containsKey(type);
	}

	@Override
	public Iterator<Element> getElementsIn(String type) {
		if (type.equals("")) {
			return elements.values().iterator();			
		} else 	if (types.containsKey(type)) {
			return types.get(type).iterator();
		}
		
		return Collections.emptyIterator();
	}
	
	@Override
	public boolean containsElement(Element e) {
		if (types.containsKey(e.getType())) {
			return types.get(e.getType()).contains(e);
		}
		return false;
	}

	@Override
	public int elementSize(String type) {
		if (types.containsKey(type)) {
			return types.get(type).size();
		}
		return 0;
	}
		
	@Override
	public Set<String> getElementTypes() {
		return types.keySet();
	}

	@Override
	public String findTypeFor(String element) {
		if (elements.containsKey(element)) {
			return elements.get(element).getType();
		}
		
		return "";
	}
	
	public void addElement(Element e) {	
		if (!elements.containsKey(e.getLabel())) {
			elements.put(e.getLabel(), e);
			
			if (!types.containsKey(e.getType())) {
				types.put(e.getType(), new LiteralStore<Element>(e.getType()));
			}
			types.get(e.getType()).add(e);
		}
	}
	
	public void removeElement(Element e) {
		elements.remove(e.getLabel());
		if (types.containsKey(e.getType())) {
			types.get(e.getType()).remove(e);
			
			if (types.get(e.getType()).isEmpty()) {
				types.remove(e.getType());
			}
		}
		
	}
		
	/////////////////////////////////////////////////////////////////////////////////////
	/// Relations
	/////////////////////////////////////////////////////////////////////////////////////

	@Override
	public int relationSize() {
		return relations.size();
	}

	@Override
	public Iterator<Relation> getRelations() {
		return relations.iterator();
	}

	@Override
	public Set<Relation> getRelations(String label) {
		if (namedRelations.containsKey(label)) {
			return Collections.unmodifiableSet(namedRelations.get(label));
		}
		return Collections.emptySet();
	}

	@Override
	public Set<String> getRelationLabels() {
		return namedRelations.keySet();
	}
	
	public void addRelation(Relation r) {
		if (r.isAbstract()) {
			return;
		}
		
		
		if (!namedRelations.containsKey(r.getLabel())) {
			namedRelations.put(r.getLabel(), new HashSet<>());
		}
		namedRelations.get(r.getLabel()).add(r);
		relations.add(r);
	}
	
	public void removeRelation(Relation r) {
		relations.remove(r);
		if (namedRelations.containsKey(r.getLabel())) {
			namedRelations.get(r.getLabel()).remove(r);
			
			if (namedRelations.get(r.getLabel()).isEmpty()) {
				namedRelations.remove(r.getLabel());
			}
		}
	}
	
	public boolean containsRelation(Relation r) {
		return relations.contains(r);
	}

	
	/////////////////////////////////////////////////////////////////////////////////////
	/// Proving
	/////////////////////////////////////////////////////////////////////////////////////


	@Override
	public boolean isValid() {
		for(Clause c: conjectures.values()) {
			if (!c.isValidIn(this)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<String> invalidates() {
		List<String> results = new ArrayList<>();
		
		for(Entry<String, Clause> c: conjectures.entrySet()) {
			if (!c.getValue().isValidIn(this)) {
				results.add(c.getKey());
			}
		}
		return results;
	}
	
	@Override
	public Map<String, Stack<Clause>> invalidateAndExplain() {
		Map<String, Stack<Clause>> results = new HashMap<>();
		
		for(Entry<String, Clause> c: conjectures.entrySet()) {
			Stack<Clause> stack = c.getValue().findExplanationFor(this);
			if (!stack.isEmpty()) {
				results.put(c.getKey(), stack);
			}
		}
		
		return results;
	}


	/////////////////////////////////////////////////////////////////////////////////////
	/// Misc
	/////////////////////////////////////////////////////////////////////////////////////

	
	@Override
	public String toString() {
		return WorldWriter.toString(this);
	}
	

}
