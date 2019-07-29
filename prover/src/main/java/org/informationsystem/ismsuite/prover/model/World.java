/**
 * 
 */
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

/**
 * @author jmw
 *
 * A world is a set of finite sets, and relations
 */
public class World implements Cloneable, FirstOrderLogicWorld {

	/**
	 * We store relations per label name
	 */
	private NamedClauseSet<String, Relation> relationset = new NamedClauseSet<>();
	
	private Map<String,Set<Element>> elements = new HashMap<>();
	private Map<String,String> items = new HashMap<>();
	
	private Map<String,Clause> conjectures = new HashMap<>();
	
	@Override
	public Set<Entry<String, Clause>> getConjectures() {
		return conjectures.entrySet();
	}
		
	public boolean addElement(Element e) {
		if (items.containsKey(e.getLabel())) {
			// label can only occur once
			return false;
		}
		if (!elements.containsKey(e.getType())) {
			elements.put(e.getType(), new HashSet<Element>());
		}
		items.put(e.getLabel(), e.getType());
		
		return elements.get(e.getType()).add(e);
	}
	
	public String findTypeFor(String element) {
		if (items.containsKey(element)) {
			return items.get(element);
		} else {
			return "";
		}
	}
	
	@Override
	public boolean containsType(String type) {
		return elements.containsKey(type);
	}
	
	@Override
	public Set<String> getElementTypes() {
		return elements.keySet();
	}
	
	@Override
	public Iterator<Element> getElementsIn(String type) {
		if (elements.containsKey(type)) {
			return elements.get(type).iterator();
		} else {
			return Collections.emptyIterator();
		}
	}
	
	@Override
	public int elementSize(String type) {
		if (elements.containsKey(type)) {
			return elements.get(type).size();
		} else {
			return 0;
		}
	}
	
	@Override
	public Iterator<String> getElementLabels() {
		return items.keySet().iterator();
	}
	
	@Override
	public int relationSize() {
		return relationset.valueSize();
	}
	
	public boolean removeElement(Element e) {
		if (elements.containsKey(e.getType())) {
			items.remove(e.getLabel());
			return elements.get(e.getType()).remove(e);
		} else {
			return true;
		}
	}
	
	public boolean addRelation(Relation r) {
		if (r.isAbstract()) {
			return false;
		}
		relationset.put(r.getLabel(), r);
		return true;
	}
	
	public boolean removeRelation(Relation r) {
		relationset.remove(r.getLabel(), r);
		return true;
	}
	
	@Override
	public Iterator<Relation> getRelations() {
		return relationset.values().iterator();
	}
	
	/**
	 * @param label
	 * @return all Relation Clauses of "type" label
	 */
	@Override
	public Set<Relation> getRelations(String label) {
		return relationset.values(label);
	}
	
	/**
	 * 
	 * @return all relation labels present in the world
	 */
	@Override
	public Set<String> getRelationLabels() {
		return relationset.keySet();
	}
	
	/**
	 * @param l
	 * @return true if l is contained in this World
	 */
	@Override
	public boolean contains(Literal l) {
		if (l instanceof Relation) {
			return relationset.containsValue(l);
		} else if (l instanceof Element) {
			if (elements.containsKey(((Element) l).getType())) {
				return elements.get(((Element) l).getType()).contains(l);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void addConjecture(String name, Clause clause) {
		conjectures.put(name, clause);
	}
	
	@Override
	public Clause getConjecture(String name) {
		if (conjectures.containsKey(name)) {
			return conjectures.get(name);
		} else {
			return null;
		}
	}
	
	public void removeConjecture(String name) {
		conjectures.remove(name);
	}
	
	/**
	 * 
	 * @return true if all Conjectures in this world are valid
	 */
	@Override
	public boolean isValid() {
		for(Clause c: conjectures.values() ) {
			if (!c.isValidIn(this)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @return all conjecture labels that are invalid
	 */
	@Override
	public List<String> invalidates() {
		List<String> invalid = new ArrayList<>();
		for(Entry<String, Clause> c: conjectures.entrySet() ) {
			if (!c.getValue().isValidIn(this)) {
				invalid.add(c.getKey());
			}
		}
		return invalid;
	}
	
	@Override
	public Map<String,Stack<Clause>> invalidateAndExplain() {
		Map<String,Stack<Clause>> results = new HashMap<>();
		
		for(Entry<String, Clause> c: conjectures.entrySet() ) {
			Stack<Clause> st = c.getValue().findExplanationFor(this);
			if (!st.isEmpty()) {
				results.put(c.getKey(), st);
			}
		}
		
		return results;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		// Population
		sb.append("%%%%%%%%%%%%%%%%%%%%\n");
		sb.append("%%%  Population  %%%\n");
		for(String type: elements.keySet()) {
			sb.append("% " + type + ":\n");
			for(Element el: elements.get(type)) {
				sb.append(el);
				sb.append("\n");
			}
		}
		sb.append("%%%%%%%%%%%%%%%%%%%%\n");
		sb.append("%%%   Relation   %%%\n");
		for(Clause rel: relationset.values()) {
			sb.append(rel);
			sb.append("\n");
		}
		sb.append("%%%%%%%%%%%%%%%%%%%%\n");
		sb.append("%%%  Conjecture  %%%\n");
		for(Entry<String, Clause> c: conjectures.entrySet()) {
			sb.append("% ");
			sb.append(c.getKey());
			sb.append("\n    ");
			sb.append(c.getValue().toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	@Override
	public Object clone() {
		World w = new World();
		for(Entry<String, String> e: items.entrySet()) {
			w.addElement(new Element(e.getKey(), e.getValue()));
		}
		
		for(Clause r: relationset.values()) {
			w.addRelation((Relation) r.clone());
		}
		
		for(Entry<String, Clause> e: conjectures.entrySet()) {
			w.addConjecture(e.getKey(), e.getValue());
		}
		
		return w;
	}
	
	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}
}
