package org.informationsystem.ismsuite.pnidprocessor.petrinet;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.processengine.process.Token;

import java.util.HashSet;

public class MarkedPetriNet {

	private IncidenceArray in  = new IncidenceArray();
	private IncidenceArray out = new IncidenceArray();

	private Map<String, Set<String>> variableMapping = new HashMap<>();
	private Map<String, Set<String>> creatorVariables = new HashMap<>();

	/**
	 * Sets an input arc, perspective is a transition!
	 * @param transition
	 * @param place
	 * @param variables
	 */
	public void addInArc(String transition, String place, List<String> variables) {
		addInArc(transition, place, 1, variables);
	}
	
	public void addInArc(String transition, String place, int multiplicity, List<String> variables) {
	
		addTransition(transition);
		in.set(transition, place, multiplicity, variables);
		mapInsertAll(transition, variables, variableMapping);
		if (creatorVariables.containsKey(transition)) {
			creatorVariables.get(transition).removeAll(variables);
		}
	}
	
	public void addInArc(String transition, String place, String... variableArray) {
		addInArc(transition, place, 1, variableArray);
	}

	/**
	 * Sets an input arc, perspective is a transition!
	 * @param transition
	 * @param place
	 * @param variables
	 */
	public void addInArc(String transition, String place, int multiplicity, String... variableArray) {
		addInArc(transition, place, multiplicity, List.of(variableArray));
	}

	/**
	 * Sets an output arc, perspective is a transition!
	 * @param transition
	 * @param place
	 * @param variables
	 */
	public void addOutArc(String transition, String place, List<String> variables) {
		addOutArc(transition, place, 1, variables);
	}
	
	public boolean hasInArc(String transition, String place) {
		return in.containsEntry(transition, place);
	}
	
	public boolean hasOutArc(String transition, String place) {
		return out.containsEntry(transition, place);
	}
	
	public List<String> getVariablesOnInputArc(String transition, String place) {
		if(in.containsEntry(transition, place)) {
			return in.getVariables(transition, place);
		} else {
			return Collections.emptyList();
		}
	}
	
	public List<String> getVariablesOnOutputArc(String transition, String place) {
		if(out.containsEntry(transition, place)) {
			return out.getVariables(transition, place);
		} else {
			return Collections.emptyList();
		}
	}
	
	public void addOutArc(String transition, String place, int multiplicity, List<String> variables) {
		addTransition(transition);
		out.set(transition, place, multiplicity, variables);
		mapInsertAll(transition, variables, variableMapping);
		for(String var: variables) {
			if (!in.getVariables(transition).contains(var)) {
				mapInsert(transition, var, creatorVariables);
			}
		}
	}

	/**
	 * Sets an input arc, perspective is a transition!
	 * @param transition
	 * @param place
	 * @param variables
	 */
	public void addOutArc(String transition, String place, String... variableArray) {
		addOutArc(transition, place, 1, variableArray);
	}
	
	public void addOutArc(String transition, String place, int multiplicity, String... variableArray) {
		addOutArc(transition, place, multiplicity, List.of(variableArray));
	}

	private <S,T> void mapInsert(S key, T value, Map<S,Set<T>> mapping) {
		if (!mapping.containsKey(key)) {
			mapping.put(key, new HashSet<>());
		}
		mapping.get(key).add(value);
	}

	private <S, T> void mapInsertAll(S key, Collection<T> values, Map<S, Set<T>> mapping) {
		if (!mapping.containsKey(key)) {
			mapping.put(key, new HashSet<>());
		}
		mapping.get(key).addAll(values);
	}

	public void addTransition(String transition) {
		transitions.add(transition);

		if (!variableMapping.containsKey(transition)) {
			variableMapping.put(transition, new HashSet<>());
		}
		if (!creatorVariables.containsKey(transition)) {
			creatorVariables.put(transition, new HashSet<>());
		}
	}
	
	private class PNIDPlace {
		private String id;
		private int cardinality = 0;
		private String[] type;

		public PNIDPlace(String id, int cardinality) {
			this.id = id;
			this.cardinality = cardinality;
			type = new String[cardinality];
			for(int i = 0 ; i < cardinality ; i++ ) {
				type[i] = "universe";
			}
		}
		
		public PNIDPlace(String id, String... entityTypes) {
			this.id = id;
			this.cardinality = entityTypes.length;
			type = new String[cardinality];
			for(int i = 0; i < cardinality ; i++) {
				type[i] = entityTypes[i];
			}
		}

		@SuppressWarnings("unused")
		public String getId() {
			return id;
		}

		public int getCardinality() {
			return cardinality;
		}
		
		public String getEntityType(int i) {
			if (i >= 0 && i < cardinality ) {
				return type[i];
			} else {
				return "universe";
			}
		}
	
		@SuppressWarnings("unused")
		public void setEntityType(int i, String entityType) {
			if (i >= 0 && i < cardinality ) {
				this.type[i] = entityType;
			}
		}
	}

	Map<String, PNIDPlace> places = new HashMap<>();
	Set<String> transitions = new HashSet<>();
		
	public Set<String> places() {
		return Collections.unmodifiableSet(places.keySet());
	}
	
	public String getPlaceType(String id, int index) {
		if (places.containsKey(id)) {
			return places.get(id).getEntityType(index);
		} else {
			return null;
		}
	}
	
	public Set<String> transitions() {
		return Collections.unmodifiableSet(transitions);
	}

	public void addPlace(String place, int cardinality) {
		places.put(place, new PNIDPlace(place, cardinality));
	}
	
	public void addPlace(String place, String...entityTypes) {
		places.put(place, new PNIDPlace(place, entityTypes));
	}

	public void addPlace(String place) {
		places.put(place, new PNIDPlace(place, 0));
	}

	public void setCardinality(String place, int cardinality) {
		addPlace(place, cardinality);
	}

	public int getCardinality(String place) {
		if (places.containsKey(place)) {
			return places.get(place).getCardinality();
		} else {
			return -1;
		}
	}

	private Marking marking = new Marking();

	public Marking getMarking() {
		return marking;
	}

	public void setMarkingCounter(int counter) {
		marking.setCounter(counter);
	}

	public void addToken(String place, String... identities) {
		if (identities.length == getCardinality(place)) {
			marking.add(place, identities);
		}
	}

	public void removeToken(String place, String[] token) {
		marking.remove(place, token);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("PLACES:\n");
		for(Entry<String, PNIDPlace> e: places.entrySet()) {
			sb.append(e.getKey());
			sb.append(": ");
			sb.append(e.getValue().getCardinality());
			sb.append("\n");
		}

		sb.append("\nTRANSITIONS:\n");
		for(String t: transitions) {
			sb.append(t);
			sb.append("\n\tIN");
			for(String p: in.getPlaces(t)) {
				sb.append("\n\t\t");
				sb.append(p);
				sb.append(in.get(t, p).toString());
			}
			sb.append("\n\tOUT");
			for(String p: out.getPlaces(t)) {
				sb.append("\n\t\t");
				sb.append(p);
				sb.append(out.get(t, p).toString());
			}
			sb.append("\n");
		}
		sb.append("\n");
		sb.append(marking.toString());
		return sb.toString();
	}

	public List<Binding> getBindings() {
		List<Binding> result = new ArrayList<>();
		for(String transition: transitions) {
			result.addAll(getBindings(transition));
		}
		return result;
	}

	public List<Binding> getBindings(String transition) {
		Map<String, Set<String>> candidateValuations = new HashMap<>();
		
		// Build the valuations for existing tokens.
		// Notice that if two places share a variable, only the
		// intersection of the entities gives the possible enabled values...
		for(String place: in.getPlaces(transition)) {
			IncidenceArray.Arc arc = in.get(transition, place);
			for(int i = 0; i < arc.getVariableList().size() ; i++) {
				String var = arc.getVariableList().get(i);
				Set<String> values = marking.getIdentities(place, i);
				if (candidateValuations.containsKey(var)) {
					candidateValuations.get(var).retainAll(values);
				} else {
					candidateValuations.put(var, values);
				}
			}
		}

		// Add the creator variables
		int newCounter = marking.getCounter();

		for(String var: creatorVariables.get(transition)) {
			newCounter++;
			mapInsert(var, "e" + newCounter, candidateValuations);
		}

		// now create real valuations
		List<Binding> bindings = generateValidBindings(transition, candidateValuations);

		return bindings;
	}

	private List<Binding> generateValidBindings(String transition, Map<String,Set<String>> candidateValuations) {
		List<Binding> result = new ArrayList<>();
		List<String> variables = new ArrayList<>(variableMapping.get(transition));

		return unfoldValuation(transition, variables, 0, new HashMap<>(), candidateValuations, result);
	}

	private List<Binding> unfoldValuation(String transition, List<String> variables, int index, HashMap<String,String> current, Map<String,Set<String>> candidates, List<Binding> result) {

		// If the index is at the end of the variable list, check whether it is a valid binding.
		// If so, we add it to the result list, and we return the list.
		// Otherwise, we keep expanding the possible valuations.
		if (index >= variables.size()) {
			@SuppressWarnings("unchecked")
			Binding b = new Binding(transition, (Map<String, String>) current.clone());
			if (validateBinding(b)) {
				result.add(b);
			}
		} else {
			String var = variables.get(index);
			for(String identity : candidates.get(var)) {
				current.put(var, identity);
				unfoldValuation(transition, variables, index+1, current, candidates, result);
			}
		}
		return result;
	}

	public boolean validateBinding(Binding binding) {
		for(String place: in.getPlaces(binding.getTransition())) {
			IncidenceArray.Arc arc = in.get(binding.getTransition(), place);
			Token token = binding.instantiate(in.get(binding.getTransition(), place).getVariableList());
			if (!marking.getTokens(place).contains(token, arc.getMultiplicity())) {
				return false;
			}
		}
		return true;
	}

	public boolean fire(Binding binding) {
		// Remove all tokens from the input places
		for(String place: in.getPlaces(binding.getTransition())) {
			IncidenceArray.Arc arc = in.get(binding.getTransition(), place);
			Token token = binding.instantiate(arc.getVariableList());
			if (!marking.getTokens(place).contains(token)) {
				return false;
			} else {
				// int countbefore = marking.getTokens(place).size();
				marking.remove(place, arc.getMultiplicity(), token);
				// assert(marking.getTokens(place).size() == countbefore-1);
			}
		}
		// Add a token to all output places
		for(String place: out.getPlaces(binding.getTransition())) {
			IncidenceArray.Arc arc = out.get(binding.getTransition(), place);
			Token token = binding.instantiate(arc.getVariableList());
			marking.add(place, arc.getMultiplicity(), token);
		}

		// And last, update the counter for the creatorVariables
		marking.increaseCounter(creatorVariables.get(binding.getTransition()).size());

		return true;
	}

}
