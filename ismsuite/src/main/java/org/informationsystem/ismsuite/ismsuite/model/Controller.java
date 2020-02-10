package org.informationsystem.ismsuite.ismsuite.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.ProcessModel;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.specifier.model.OperationException;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.model.Transaction;

import java.util.Map.Entry;

public class Controller {

	private ProcessModel processModel;
	private Specification specification;
	private World initial;
	
	private boolean verbose = false;
	
	public boolean isVerbose() {
		return verbose;
	}
	
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	
	private boolean allowEmptySpecifications = false;
	
	public boolean emptySpecificationsAllowed() {
		return allowEmptySpecifications;
	}
	
	public void setEmptySpecificationsAllowed(boolean allowed) {
		allowEmptySpecifications = allowed;
	}
	
	private Model state;
	
	public ProcessModel getProcessModel() {
		return processModel;
	}
	
	public Specification getSpecification() {
		return specification;
	}
	
	public Set<Entry<String, Clause>> getConjectures() {
		return initial.getConjectures();
	}
	
	public Controller(ProcessModel process, Specification spec, FirstOrderLogicWorld initialWorld) {
		this(process, spec, initialWorld.getConjectures());
	}
	
	public Controller(ProcessModel process, Specification spec, Map<String, Clause> conjectures) {
		this(process, spec, conjectures.entrySet());
	}
	
	public Controller(ProcessModel process, Specification spec, Set<Entry<String, Clause>> conjectures) {
		this.processModel = process;
		
		this.specification = spec;
		this.initial = new World();
		for(Entry<String, Clause> e: conjectures ) {
			initial.addConjecture(e.getKey(), e.getValue());
		}
		state = new Model();
		
		World world = new World();
		// calculate the individuals for the initial state of the process model
		if (processModel != null) {
			initializeTokensAsElements(world);
		}
		
		Pair<Map<Binding, World>, Map<Binding, String>> pair = 
				calculateFutureWorlds(world);
		
		state.update(world, pair.first(), pair.second());
	}
	
	public Model getModel() {
		return state;
	}
	
	public boolean fire(String transition) {
		for(Binding b: state.enabledTransitions()) {
			if (b.getTransition().equals(transition)) {
				return fire(b);
			}
		}
		return false;
	}
	
	public boolean fire(Binding transition) {
		World next = state.getFutureWorlds().get(transition);
		if (next == null) {
			return false;
		}
		
		if (processModel != null) processModel.fire(transition);
		
		
		Pair<Map<Binding, World>, Map<Binding, String>> pair = calculateFutureWorlds(next);

		state.update(next, pair.first(), pair.second());
		
		return true;
	}
	
	public Set<Entry<String,Clause>> conjectures() {
		return initial.getConjectures();
	}
	
	public boolean isValid() {
		return (validWorld(this.state.getWorld()) == null);
	}
	
	private Entry<String, Clause> validWorld(FirstOrderLogicWorld world) {
		for(Entry<String, Clause> c: initial.getConjectures()) {
			if (!c.getValue().isValidIn(world)) {
				return c;
			}
		}
		return null;
	}
		
	private Pair<Map<Binding, World>, Map<Binding,String>> calculateFutureWorlds(World current) {
		Map<Binding, World> enabled = new HashMap<>();
		Map<Binding,String> disabled = new HashMap<>();
		if (processModel == null) {
			return new Pair<>(enabled, disabled);
		}
		
		for(Binding binding : processModel.getEnabledTransitions()) {
			
			System.out.println("--- Working on: " + binding.getTransition());
			
			World p2 = (World) current.clone();
			
			if(specification.containsTransition(binding.getTransition())) {
				Transaction t = specification.getTransactionFor(binding.getTransition());
				
				if (verbose) {
					System.out.println("--- Checking world for transition: " + binding.getTransition());
					System.out.print("Binding: ");
					for(Entry<String, String> e: binding.getValuation().entrySet()) {
						System.out.print(" [");
						System.out.print(e.getKey());
						System.out.print(" -> ");
						System.out.print(e.getValue());
						System.out.print("]");
					}
					System.out.println();
					System.out.println("------ Current world:");
					System.out.println(current.toString());
					System.out.println("------ Checking transaction:");
					System.out.println(t.toString());
				}
								
				Map<Variable, Element> valuation = new HashMap<>();
				
				for(Entry<String, String> val : binding.getValuation().entrySet()) {
					Variable v = t.getVariable(val.getKey());
					if (v != null) {
						valuation.put(v, new Element(val.getValue(), v.getType()));
					}
				}
				
				// Now apply the transaction on the current world
				boolean result = false;
				try {
					result = t.apply(valuation, p2);
				} catch (OperationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (result) {
					if (verbose) {
						System.out.println("------ Resulting world");
						System.out.println(p2.toString());
					}
					
					// And check if it is valid
					Entry<String, Clause> item = validWorld(p2);
				
					if (item == null) {
						if (verbose) {
							System.out.println("------ World is valid!");
						}
						enabled.put(binding, p2);
					} else {
						if (verbose) {
							System.out.println("------ World is INVALID: " + item.getKey());
						}
						disabled.put(binding, item.getKey());
					}
				} else {
					if (verbose) {
						System.out.println("------ Transaction was unsuccessful!");
					}
					disabled.put(binding, "Result of transaction failed");
				}
				
			} else if (allowEmptySpecifications){
				// It is not in the specification, hence, it does not change the world:
				if (verbose) {
					System.out.println("------ Adding empty specification for: " + binding.getTransition());
				}
				enabled.put(binding, p2);
			} else {
				if (verbose) {
					System.out.println("------ No specification for: " + binding.getTransition());
				}
			}
			System.out.println("-----------------------------------------");
		}
		
		return new Pair<>(enabled, disabled);
	}
	
	/*
	private World constructNextWorldFor(Transaction t, Map<Variable, Element> binding, World w) {
		World next = (World) w.clone();
		t.apply(binding, next);
		return next;
	}*/
	
	private FirstOrderLogicWorld initializeTokensAsElements(World world) {
		for(Entry<String, MultiSet<Token>> m : processModel.getCurrentMarking().entrySet()) {
			String place = m.getKey();
			// check if place is in the specification
			Transaction transaction = specification.getPlace(place);
			if (transaction != null ) {
				// apply specification for each token
				for(Token t: m.getValue()) {
					if (t.size() == transaction.variableSize()) {
						Map<Variable, Element> binding = new HashMap<>();
						
						for(int i = 0 ; i < t.size(); i++) {
							Variable v = transaction.getVariable(i);
							binding.put(v, new Element(v.getType() + "_" + t.get(i), v.getType()));
						}						
						try {
							transaction.apply(binding, world);
						} catch (OperationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return world;
	}

}
