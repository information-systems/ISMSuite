package org.informationsystem.ismsuite.modeler.process.simulator;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Arc;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence;
import org.informationsystem.ismsuite.modeler.process.simulator.exceptions.InvalidPNID;
import org.informationsystem.ismsuite.modeler.process.simulator.exceptions.UnknownNetType;
import org.informationsystem.ismsuite.modeler.process.validator.PNIDSyntaxChecker;
import org.informationsystem.ismsuite.pnidprocessor.petrinet.MarkedPetriNet;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.PlaceNode;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

public class PNIDEngine {

	private MarkedPetriNet markedNet;
	
	private PetriNet net;
	private FlatAccess flat;
	
	private Map<org.pnml.tools.epnk.pnmlcoremodel.Place, TokenBag> marking;
	
	private Map<PNIDBinding,Binding> enabledBindings = new HashMap<>();
	private Map<Transition, Set<PNIDBinding>> enabledTransitions = new HashMap<>();
		
	private Map<String, Transition> transitions = new HashMap<>();
	
	private Stack<PNIDBinding> history = new Stack<>();
	
	public PNIDEngine(PetriNet petrinet) throws UnknownNetType, InvalidPNID {
		if (petrinet.getType() == null) {
			throw new UnknownNetType(petrinet);
		}
		if (!(petrinet.getType() instanceof PNID)) {
			throw new UnknownNetType(petrinet);
		}
		if (!PNIDSyntaxChecker.checkPetrinet(petrinet)) {
			throw new InvalidPNID(petrinet);
		}
		this.net = petrinet;
		
		this.flat = FlatAccess.getFlatAccess(net);
		
		reset();
	}
	
	
	public PetriNet getPetriNet() {
		return net;
	}
	
	public MarkedPetriNet getMarkedPetriNet() {
		return markedNet;
	}
	
	public boolean reset() {
		constructMarkedPetriNet();
		calculateMarking();
		calculateBindings();
		
		history = new Stack<>();
		
		return true;
	}
	
	private void constructMarkedPetriNet() {
		markedNet = new MarkedPetriNet();
		transitions = new HashMap<>();
		
		for(Transition transition: flat.getTransitions()) {
			transitions.put(transition.getId(), transition );
			
			// Add the In arcs
			for (org.pnml.tools.epnk.pnmlcoremodel.Arc a: flat.getIn(transition)) {
				// It is an In-arc, hence, the Source is a place
				Place place = (Place) flat.resolve((PlaceNode) a.getSource());
				List<String> vars = new ArrayList<>();
				int multiplicity = 1;
				if (a instanceof Arc) {
					if ((((Arc) a).getInscription()!= null) && (((Arc) a).getInscription().getStructure() != null)) {
						VariableSequence inscription = ((Arc) a).getInscription().getStructure();
						for(Variable v: inscription.getVariable()) {
							vars.add(v.getText());
						}
						if (inscription.getMultiplicity() > 1) {
							multiplicity = inscription.getMultiplicity();
						}
					}
				}
				markedNet.addInArc(transition.getId(), place.getId(), multiplicity, vars);
			}
			
			// Add the outgoing arcs
			for(org.pnml.tools.epnk.pnmlcoremodel.Arc a: flat.getOut(transition)) {
				// It is an in arc, hence, the source is this transition
				Place place = (Place) flat.resolve((PlaceNode) a.getTarget());
				List<String> vars = new ArrayList<>();
				int multiplicity = 1;
				if (a instanceof Arc) {
					if ((((Arc) a).getInscription()!= null) && (((Arc) a).getInscription().getStructure() != null)) {
						VariableSequence inscription = ((Arc) a).getInscription().getStructure();
						for(Variable v: inscription.getVariable()) {
							vars.add(v.getText());
						}
						if (inscription.getMultiplicity() > 1) {
							multiplicity = inscription.getMultiplicity();
						}
					}
				}
				markedNet.addOutArc(transition.getId(), place.getId(), multiplicity, vars);
			}
		}
		
		// Process the places,
		for(org.pnml.tools.epnk.pnmlcoremodel.Place p: flat.getPlaces()) {
			Place place = (Place) p;
			
			// get the cardinality
			int cardinality = 0;
			if ((place.getType() != null) && (place.getType().getStructure() != null) ) {
				cardinality = place.getType().getStructure().getEntityType().size();
			}
			markedNet.addPlace(p.getId(), cardinality);
			
			// Add the tokens
			Set<String> entities = new HashSet<>();
			if ((place.getInitialMarking() != null) && (place.getInitialMarking().getStructure() != null) ) {
				for(Token t: place.getInitialMarking().getStructure().getToken()) {
					String[] token = new String[cardinality];
					if ((t.getEntity() != null) && (cardinality <= t.getEntity().size())) {
						for(int i = 0; i < cardinality ; i++) {
							token[i] = t.getEntity().get(i).getText();
						}
					}
					markedNet.addToken(p.getId(), token);
				}
			}
		}
	}
	
	private void calculateMarking() {
		marking = new HashMap<>();
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Place p : flat.getPlaces()) {
			if (p instanceof Place) {
				String placeId = p.getId();
				MultiSet<org.informationsystem.ismsuite.processengine.process.Token>  m = markedNet.getMarking().getTokens(placeId);
				if (m.size() > 0) {
					TokenBag bag = PnidsFactory.eINSTANCE.createTokenBag();
					for(org.informationsystem.ismsuite.processengine.process.Token token: m) {
						for(int i = 0 ; i < m.size(token) ; i++) {
							Token netToken = PnidsFactory.eINSTANCE.createToken();
							for(String ent: token.getEntities()) {
								Entity entity = PnidsFactory.eINSTANCE.createEntity();
								entity.setText(ent);
								netToken.getEntity().add(entity);
							}
							bag.getToken().add(netToken);
						}
					}
					marking.put(p, bag);
				}
			}
		}
	}
	
	private void calculateBindings() {
		enabledBindings = new HashMap<>();
		enabledTransitions = new HashMap<>();
		
		for(Binding b: markedNet.getBindings()) {
			Transition transition = transitions.get(b.getTransition());
			PNIDBinding binding = PNIDBinding.createBinding(transition, b.getValuation());
			enabledBindings.put(binding, b);
			if (!enabledTransitions.containsKey(transition)) {
				enabledTransitions.put(transition, new HashSet<>());
			}
			enabledTransitions.get(transition).add(binding);
		}
	}
	
	
	public Set<PNIDBinding> getEnabledBindings() {
		return enabledBindings.keySet();
	}
	
	public Set<PNIDBinding> getEnabledBindings(Transition transition) {
		if(enabledTransitions.containsKey(transition)) {
			return enabledTransitions.get(transition);
		} else {
			return Collections.emptySet();
		}
	}
	
	/**
	 * Gives the list of enabled transitions in the current marking. Note that
	 * if there are multiple bindings for a transition, the transition is only
	 * shown once.
	 * 
	 * @return the enabled transitions in the current marking 
	 */
	public Set<Transition> getEnabledTransitions() {
		return enabledTransitions.keySet();
	}
	
	public boolean isEnabled(PNIDBinding binding) {
		return enabledBindings.containsKey(binding);
	}
	
	public boolean isEnabled(Transition transition) {
		return enabledTransitions.containsKey(transition);
	}
	
	public Transition getTransition(String id) {
		return transitions.get(id);
	}
	
	public Binding getBindingFor(PNIDBinding b) {
		if (enabledBindings.containsKey(b)) {
			return enabledBindings.get(b);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @return the current marking of the net
	 */
	public Map<org.pnml.tools.epnk.pnmlcoremodel.Place, TokenBag> getCurrentMarking() {
		return marking;
	}
	
	/**
	 * Fires a given binding, provided that the binding is enabled
	 * @param binding
	 * @return true iff firing the binding succeeded.
	 */
	public boolean fire(PNIDBinding binding) {
		if (enabledBindings.containsKey(binding)) {
			if (markedNet.fire(enabledBindings.get(binding))) {
				calculateMarking();
				calculateBindings();
				
				history.add(binding);
				
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Fire a random binding. We use a uniform distribution over all
	 * enabled bindings.
	 *  
	 * @return the fired binding, null if no binding could be fired.
	 */
	public PNIDBinding fire() {
		if (enabledBindings.size() > 0) {
			for(PNIDBinding b: enabledBindings.keySet()) {
				if (fire(b)) {
					history.add(b);
					return b;
				} else {
					return null;
				}
			}
		}
		return null;
	}
	
	/**
	 * Fires a random binding for that transition.
	 * @param transition
	 * @return the fired binding, null if no binding could be fired 
	 */
	public PNIDBinding fire(Transition transition) {
		if (enabledTransitions.containsKey(transition)) {
			for(PNIDBinding b: enabledTransitions.get(transition)) {
				if (fire(b)) {
					return b;
				} else {
					return null;
				}
			}
		}
		return null;
	}
	
	/**
	 * Rewinds to the previous marking
	 * @return
	 */
	public boolean rewind() {
		// TODO Needs an implementation
		return false;
	}
	
	/**
	 * Returns a stack of all bindings that fired. As it is a stack, the binding fired last is on top. 
	 * @return stack of all bindings fired for this run.
	 */
	public Stack<PNIDBinding> getHistory() {
		return history;
	}

	public int getTokenCount(org.pnml.tools.epnk.pnmlcoremodel.Place place) {
		String id = place.getId();
		return markedNet.getMarking().getTokens(id).size();
	}
}
