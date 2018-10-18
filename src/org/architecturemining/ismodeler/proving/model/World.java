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
	
	private Map<String,Set<Literal>> inhabitants = new HashMap<>(); 
	
	public boolean addInhabitant(Literal l, String type) {
		if (l.isComplex()||l.isAbstract()) {
			return false;
		}
		if (!inhabitants.containsKey(type)) {
			inhabitants.put(type, new HashSet<Literal>());
		}
		inhabitants.get(type).add(l);
		
		return true;
	}
	
	public boolean containsType(String type) {
		return inhabitants.containsKey(type);
	}
	
	public Set<Literal> getInhabitants(String type) {
		return inhabitants.get(type);
	}
	
	public boolean addRelation(Relation r) {
		if (r.isAbstract()) {
			return false;
		}
		return relations.add(r);
	}
	
	public Set<Relation> getRelations() {
		return relations;
	}
	
	public boolean contains(Literal l) {
		if (l instanceof Relation) {
			return relations.contains(l);
		} else {
			for(Set<Literal> inh : inhabitants.values() ) {
				if (inh.contains(l)) {
					return true;
				}
			}
			return false;
		}
	}
}
