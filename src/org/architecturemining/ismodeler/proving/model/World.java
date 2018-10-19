/**
 * 
 */
package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * @author jmw
 *
 * A world is a set of finite sets, and relations
 */
public class World {

	private Set<Relation> relations = new HashSet<>();
	
	private Map<String,Set<Literal>> elements = new HashMap<>(); 
	
	public boolean addElement(Element e) {
		if (!elements.containsKey(e.getType())) {
			elements.put(e.getType(), new HashSet<Literal>());
		}
		return elements.get(e.getType()).add(e);
	}
	
	public boolean containsType(String type) {
		return elements.containsKey(type);
	}
	
	public Iterator<Literal> elementsIn(String type) {
		return elements.get(type).iterator();
	}
	
	public int elementSize(String type) {
		if (elements.containsKey(type)) {
			return elements.get(type).size();
		} else {
			return 0;
		}
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
}
