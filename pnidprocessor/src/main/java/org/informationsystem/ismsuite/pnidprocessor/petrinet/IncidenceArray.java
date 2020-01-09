package org.informationsystem.ismsuite.pnidprocessor.petrinet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class stores a T -> P -> L(Var) incidencematrix
 * The T and P are fixed at initialization.
 * @author jmw
 *
 */
public class IncidenceArray implements Cloneable {


	Map<String, Map<String, List<String>>> mapping = new HashMap<>();

	Map<String, Set<String>> variableMapping = new HashMap<>();

	public Set<String> getVariables(String transition) {
		if (variableMapping.containsKey(transition)) {
			return variableMapping.get(transition);
		} else {
			return new HashSet<>();
		}
	}

	@Override
	public Object clone() {
		return null;
	}

	public void clear() {
		mapping = new HashMap<>();
	}

	public boolean containsEntry(String transition, String place) {
		if (mapping.containsKey(transition)) {
			return mapping.get(transition).containsKey(place);
		} else {
			return false;
		}
	}

	public List<String> get(String transition, String place) {
		if (mapping.containsKey(transition)) {
			return mapping.get(transition).get(place);
		} else {
			return null;
		}
	}

	public Set<String> getPlaces(String transition) {
		if (mapping.containsKey(transition)) {
			return mapping.get(transition).keySet();
		} else {
			return new HashSet<>();
		}
	}

	public void set(String transition, String place, List<String> variables) {
		if (!mapping.containsKey(transition)) {
			mapping.put(transition, new HashMap<>());
		}
		mapping.get(transition).put(place, variables);
		if (!variableMapping.containsKey(transition)) {
			variableMapping.put(transition, new HashSet<>());
		}
		variableMapping.get(transition).addAll(variables);
	}

	public boolean isEmpty() {
		return mapping.isEmpty();
	}

	public int size() {
		return mapping.size();
	}

}
