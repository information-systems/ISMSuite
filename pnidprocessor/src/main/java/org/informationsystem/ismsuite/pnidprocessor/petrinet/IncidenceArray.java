package org.informationsystem.ismsuite.pnidprocessor.petrinet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

	public class Arc {
		private List<String> variableList;
		private int multiplicity;
		
		public Arc(String...variables) {
			this(1, variables);
		}
		
		public Arc(List<String> variables) {
			this(1, variables);
		}
		
		public Arc(int multiplicity, String...variables) {
			this.multiplicity = multiplicity;
			this.variableList = new ArrayList<>();
			for(String var: variables) {
				this.variableList.add(var);
			}
		}
		
		public Arc(int multiplicity, List<String> variables) {
			this.multiplicity = multiplicity;
			variableList = new ArrayList<String>(variables);
		}
		
		public int getMultiplicity() {
			return multiplicity;
		}
		
		public List<String> getVariableList() {
			return variableList;
		}
	}

	/**
	 * Contains the actual value of the Incidence matrix, i.e., T x S -> Arc
	 */
	Map<String, Map<String, IncidenceArray.Arc>> mapping = new HashMap<>();

	/**
	 * Contains the set of variables attached to a Transition.
	 */
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

	public List<String> getVariables(String transition, String place) {
		if (mapping.containsKey(transition)) {
			if (mapping.get(transition).containsKey(place)) {
				return mapping.get(transition).get(place).getVariableList();
			}
		}
		return Collections.emptyList();
	}
	
	public IncidenceArray.Arc get(String transition, String place) {
		if (mapping.containsKey(transition)) {
			if (mapping.get(transition).containsKey(place)) {
				return mapping.get(transition).get(place);
			}
		}
		return null;
	}

	public Set<String> getPlaces(String transition) {
		if (mapping.containsKey(transition)) {
			return mapping.get(transition).keySet();
		} else {
			return new HashSet<>();
		}
	}
	
	public void set(String transition, String place, List<String> variables) {
		set(transition, place, 1, variables);
	}

	public void set(String transition, String place, int multiplicity, List<String> variables) {
		if (!mapping.containsKey(transition)) {
			mapping.put(transition, new HashMap<>());
		}
		 
		mapping.get(transition).put(place, new IncidenceArray.Arc(multiplicity, variables));
		
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
