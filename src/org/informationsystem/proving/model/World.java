/**
 * 
 */
package org.informationsystem.proving.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

/**
 * @author jmw
 *
 * A world is a set of finite sets, and relations
 */
public class World implements Cloneable {

	private Set<Relation> relations = new HashSet<>();
	
	private Map<String,Set<Element>> elements = new HashMap<>();
	private Map<String,String> items = new HashMap<>();
	
	private Map<String,Clause> conjectures = new HashMap<>();
	
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
	
	public boolean containsType(String type) {
		return elements.containsKey(type);
	}
	
	public Iterator<Element> elementsIn(String type) {
		if (elements.containsKey(type)) {
			return elements.get(type).iterator();
		} else {
			return Collections.emptyIterator();
		}
	}
	
	public int elementSize(String type) {
		if (elements.containsKey(type)) {
			return elements.get(type).size();
		} else {
			return 0;
		}
	}
	
	public Iterator<String> elementLabels() {
		return items.keySet().iterator();
	}
	
	public int relationSize() {
		return relations.size();
	}
	
	public boolean removeElement(Element e) {
		if (elements.containsKey(e.getType())) {
			return elements.get(e.getType()).remove(e);
		} else {
			return true;
		}
	}
	
	public boolean addRelation(Relation r) {
		if (r.isAbstract()) {
			return false;
		}
		return relations.add(r);
	}
	
	public boolean removeRelation(Relation r) {
		return relations.remove(r);
	}
	
	public Iterator<Relation> relations() {
		return relations.iterator();
	}
	
	public boolean contains(Literal l) {
		if (l instanceof Relation) {
			return relations.contains(l);
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
	
	public Clause getConjecture(String name) {
		if (conjectures.containsKey(name)) {
			return conjectures.get(name);
		} else {
			return null;
		}
	}
	
	public boolean isValid() {
		for(Clause c: conjectures.values() ) {
			if (!c.isValidIn(this)) {
				return false;
			}
		}
		return true;
	}
	
	public List<String> invalidates() {
		List<String> invalid = new ArrayList<>();
		for(Entry<String, Clause> c: conjectures.entrySet() ) {
			if (!c.getValue().isValidIn(this)) {
				invalid.add(c.getKey());
			}
		}
		return invalid;
	}
	
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
		for(Relation rel: relations) {
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
		
		for(Relation r: relations) {
			w.addRelation((Relation) r.clone());
		}
		
		for(Entry<String, Clause> e: conjectures.entrySet()) {
			w.addConjecture(e.getKey(), e.getValue());
		}
		
		return w;
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}
}
