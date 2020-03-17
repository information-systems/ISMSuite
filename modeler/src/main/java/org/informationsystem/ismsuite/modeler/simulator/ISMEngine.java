package org.informationsystem.ismsuite.modeler.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.pnidprocessor.petrinet.MarkedPetriNet;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.specifier.model.OperationException;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.model.Transaction;

public class ISMEngine {

	private World initialWorld;
	private Specification specification;
	private MarkedPetriNet petriNet;
	
	public ISMEngine(World world, Specification specification, MarkedPetriNet net) {
		this.initialWorld = world;
		this.specification = specification;
		this.petriNet = net;
	}
	
	/**
	 * Maintains the current world. Note that this is actually 
	 * part of the state of the Engine.
	 */
	private World currentWorld;
	
	/**
	 * By returning only the interface, we ensure that nobody can
	 * alter the world...
	 * 
	 * @return the current world
	 */
	public FirstOrderLogicWorld getCurrentWorld() {
		return currentWorld;
	}
	
	private String error;
	
	/**
	 * @return the error set by one of the methods
	 */
	public String getError() {
		return error;
	}
	
	public boolean initializeWorld() {
		currentWorld = (World) initialWorld.clone();
		
		error = "";
		String msg = "";
		
		// System.out.println(specification);
		
		for(Entry<String, MultiSet<Token>> bag : petriNet.getMarking().map().entrySet()) {
			Transaction transaction = specification.get(bag.getKey());
			if (transaction != null && !bag.getValue().isEmpty()) {
				// msg += "Transaction found for: " + bag.getKey() + "\n"; 
				// System.out.println("Transaction: " + bag.getKey());
				for(Token token : bag.getValue().getUnique()) {
					if (token.size() == transaction.variableSize()) {
						Map<Variable, Element> valuation = new HashMap<>();
						
						for(int i = 0 ; i < token.size() ; i++) {
							
							String varLabel = petriNet.getPlaceType(bag.getKey(), i);
							Variable var = transaction.getVariable(varLabel);
							
							Element elem = new Element(token.get(i), var.getType());
							
							/*
							System.out.print("nr: " + i + ": ");
							System.out.print(var.getLabel());
							System.out.print(" -> ");
							System.out.println(elem.getLabel());
							*/
							
							valuation.put(var, elem);
						}
						try {
							if (!transaction.apply(valuation, currentWorld)) {
								msg += "Something went wrong in execution " + bag.getKey() + "\n";
							}
						} catch(OperationException e) {
							msg += "* Error on place " + bag.getKey() +":\n"; 
							msg += e.getMessage();
							msg += "\n";
						}
					} else {
						msg += "* Variable signature of place " + bag.getKey() + " is incorrect.\n";
					}
				}
			} else {
				msg += "* No transaction found for place: " + bag.getKey() + "\n";
			}
		}
		
		if (msg.isBlank()) {
			return true;
		} else {
			error = msg;
			return false;
		}
	}
	
	public List<String> validateCurrentWorld() {
		if (currentWorld != null) {
			return validateWorld(currentWorld);
		} else {
			return Collections.emptyList();
		}
	}
	
	private List<String> validateWorld(World next) {
		List<String> reasons = new ArrayList<>();
		for( Entry<String, Clause> c : initialWorld.getConjectures()) {
			if (!c.getValue().isValidIn(next)) {
				reasons.add(c.getKey());
			}
		}
		return reasons;
	}
	
	public boolean initialize() {
		error = "";
		
		List<String> reasons = validateCurrentWorld();
		if (reasons.isEmpty()) {
					
			calculateNextPossibleSteps();
			
			return true;

		} else {
			StringBuilder msg = new StringBuilder();
			msg.append("Initial world contains the following errors:");
			for(String reason: reasons) {
				msg.append("\n * ");
				msg.append(reason);
			}
			
			error = msg.toString();
			return false;
		}
		
	}
	
	
	private Map<Binding, String> disabledBindings = new HashMap<>();
	private Map<Binding, World> enabledBindings = new HashMap<>();
	private Map<String, Set<Binding>> enabledTransitions = new HashMap<>();
	
	
	public Set<String> getEnabledTransitions() {
		return enabledTransitions.keySet();
	}
	
	public Set<Binding> getBindings(String transition) {
		if (enabledTransitions.containsKey(transition)) {
			return enabledTransitions.get(transition);
		} else {
			return Collections.emptySet();
		}
	}
	
	public Set<Binding> getBindings() {
		return enabledBindings.keySet();
	}
	
	public Map<Binding, String> getDisabledBindings() {
		return Collections.unmodifiableMap(disabledBindings);
	}
	
	public boolean fire(Binding b) {
		if (enabledBindings.containsKey(b)) {

			if (petriNet.fire(b)) {
				currentWorld = enabledBindings.get(b);
			
				calculateNextPossibleSteps();
			
				return true;
			}
		}
		return false;
	}
	
	private boolean buildValuation(Iterator<Variable> it, Map<String, String> values, Map<Variable, Element> valuation) {
		while(it.hasNext()) {
			Variable var = it.next();
			if (values.containsKey(var.getLabel())) {
				valuation.put(var, new Element(values.get(var.getLabel()), var.getType()));
			} else {
				return false;
			}
		}
		return true;
	}
	
	private void calculateNextPossibleSteps() {
		disabledBindings.clear();
		enabledBindings.clear();
		enabledTransitions.clear();
		
		for(Binding binding: petriNet.getBindings()) {
			if (specification.containsKey(binding.getTransition())) {
				Transaction t = specification.get(binding.getTransition());
				
				World next = (World) currentWorld.clone();
				
				Map<Variable, Element> valuation = new HashMap<>();
				Iterator<Variable> it = t.variables();
				if (!buildValuation(it, binding.getValuation(), valuation)) {
					disabledBindings.put(binding, "No valuation for some variables!");
					continue;
				}
				boolean applied = false;
				try {
					applied = t.apply(valuation, next);
				} catch(OperationException e) {
					disabledBindings.put(binding, "Transaction failed with exception:\n\t" + e.getMessage());
					continue;
				}
				
				if (applied) {
					
					// Check whether it is a valid world!
					List<String> reasons = validateWorld(next);
					// if reasons is empty, we can continue. Otherwise, 
					if (reasons.isEmpty()) {
						enabledBindings.put(binding, next);
						if (!enabledTransitions.containsKey(binding.getTransition())) {
							enabledTransitions.put(binding.getTransition(), new HashSet<>());
						}
						enabledTransitions.get(binding.getTransition()).add(binding);
					} else {
						StringBuilder sb = new StringBuilder();
						sb.append("Transaction violates the following conjectures:");
						for(String r: reasons) {
							sb.append("\n  * ");
							sb.append(r);
						}
						disabledBindings.put(binding, sb.toString());
						continue;
					}
				} else {
					disabledBindings.put(binding, "Applying transaction failed:\n" + t.toString());
					continue;
				}
				
			} else {
				disabledBindings.put(binding, "Transition has an empty transaction");
				continue;
			}
		}
	}
	
	
}
