package org.informationsystem.ismsuite.ismsuite.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.processengine.process.BoundTransition;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.ProcessModel;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.model.Transaction;

import java.util.Map.Entry;

public class Controller {

	private ProcessModel processModel;
	private Specification specification;
	private World initial;
	
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
		
		Pair<Map<BoundTransition, World>, Map<BoundTransition, String>> pair = 
				calculateFutureWorlds(world);
		
		state.update(world, pair.first(), pair.second());
	}
	
	public Model getModel() {
		return state;
	}
	
	public boolean fire(String transition) {
		for(BoundTransition b: state.enabledTransitions()) {
			if (b.getName().equals(transition)) {
				return fire(b);
			}
		}
		return false;
	}
	
	public boolean fire(BoundTransition transition) {
		World next = state.getFutureWorlds().get(transition);
		if (next == null) {
			return false;
		}
		
		if (processModel != null) processModel.fire(transition);
		
		
		Pair<Map<BoundTransition, World>, Map<BoundTransition, String>> pair = calculateFutureWorlds(next);

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
	
	public boolean addFutureWorld(String transition, Map<Variable, Element> binding) {
		if (!specification.containsTransition(transition)) {
			return false;
		}
		
		World next = (World)  state.getWorld();
		specification.getTransactionFor(transition).apply(binding, next);
		
		Entry<String, Clause> item = validWorld(next);
		if (item == null) {
			state.addFuture(new BoundTransition(transition, new Binding()), next);
			return true;
		} else {
			System.out.println("Transition: " + transition + " NOT valid because of: " + item.getKey());
		}
		
		return false;
	}
	
	private Pair<Map<BoundTransition, World>, Map<BoundTransition,String>> calculateFutureWorlds(World current) {
		Map<BoundTransition, World> enabled = new HashMap<>();
		Map<BoundTransition,String> disabled = new HashMap<>();
		if (processModel == null) {
			return new Pair<>(enabled, disabled);
		}
		
		for(BoundTransition transition : processModel.getEnabledTransitions()) {
			if(!specification.containsTransition(transition.getName())) {
				continue;
			}
			Transaction t = specification.getTransactionFor(transition.getName());
			
			Map<Variable, Element> binding = new HashMap<>();
			for(Binding.BindingElement bb: transition.getBinding()) {
				Variable v = t.getVariable(bb.getVariable());
				if (v != null) binding.put(v, new Element(v.getType() + "_" + bb.getValue(), v.getType()));
			}
			
			World p2 = (World) current.clone();
			
			t.apply(binding, p2);
			
			Entry<String, Clause> item = validWorld(p2);
			if (item == null) {
				enabled.put(transition, p2);
			} else {
				disabled.put(transition, item.getKey());
			}
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
						transaction.apply(binding, world);
					}
				}
			}
		}
		return world;
	}

}
