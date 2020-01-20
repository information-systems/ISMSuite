package org.informationsystem.ismsuite.modeler.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.informationsystem.ismsuite.modeler.process.simulator.BasicPNIDSimulator;
import org.informationsystem.ismsuite.modeler.process.simulator.PNIDBinding;
import org.informationsystem.ismsuite.pnidprocessor.PNIDModel;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.specifier.model.OperationException;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.model.Transaction;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;
import org.pnml.tools.epnk.pnmlcoremodel.TransitionNode;

public class Simulator extends BasicPNIDSimulator {

	private Specification specification;
	private World initialWorld;
	
	private World currentWorld;
	
	public Simulator(PetriNet petrinet, Specification specification, World world) {
		super(petrinet);
		this.specification = specification;
		this.initialWorld = world;
	}

	private SimulationView viewer;
	
	
	@Override
	public void initializeContents() {
		initializeSimulator();
		
		try {
			SimulationView viewer = (SimulationView) PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(SimulationView.ID);
			
			viewer.setSimulator(this);
			addListener(viewer);
			
			currentWorld = (World) initialWorld.clone();
			calculateNextPossibleSteps();
			notifyFiring(null);
			
			generateCurrentAnnotations();
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void shutDown() {
		super.shutDown();
		if (viewer != null) {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(viewer);
		}
		
	}
	
	@Override 
	public Set<Transition> getEnabledTransitions() {
		return enabledTransitions.keySet();
	}
	
	@Override
	public Set<PNIDBinding> getBindings(TransitionNode transitionNode) {
		Transition transition = getFlatAccess().resolve(transitionNode);
		if (enabledTransitions.containsKey(transition)) {
			return enabledTransitions.get(transition);
		} else {
			return Collections.emptySet();
		}
	}
	
	@Override
	public void fire(PNIDBinding b) {
		if (enabledBindings.containsKey(b)) {
			currentWorld = enabledBindings.get(b);
			getEngine().fire(b);
			
			// Calculate the new possible states
			calculateNextPossibleSteps();
			
			// Update the annotations
			generateCurrentAnnotations();
			
			// Inform everyone that the firing succeeded. 
			notifyFiring(b);
		}
	}
	
	private Map<PNIDBinding, String> disabledBindings = new HashMap<>();
	private Map<PNIDBinding, World> enabledBindings = new HashMap<>();
	private Map<Transition, Set<PNIDBinding>> enabledTransitions = new HashMap<>();
	
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
		

		for(PNIDBinding binding: getEngine().getEnabledBindings()) {
			Binding b = getEngine().getBindingFor(binding);
			if (specification.containsTransition(b.getTransition())) {
				Transaction t = specification.getTransactionFor(b.getTransition());
				
				World next = (World) currentWorld.clone();
				
				Map<Variable, Element> valuation = new HashMap<>();
				Iterator<Variable> it = t.variables();
				if (!buildValuation(it, b.getValuation(), valuation)) {
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
					List<String> reasons = new ArrayList<>();
					for( Entry<String, Clause> c : initialWorld.getConjectures()) {
						if (!c.getValue().isValidIn(next)) {
							reasons.add(c.getKey());
						}
					}
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
					disabledBindings.put(binding, "Applying transaction failed:\n" + t.toString(true));
					continue;
				}
				
			} else {
				disabledBindings.put(binding, "Transition has an empty transaction");
				continue;
			}
		}
	}
	
	public FirstOrderLogicWorld getWorld() {
		return currentWorld;
	}
	
	public Set<Entry<String,Clause>> getConjectures() {
		return initialWorld.getConjectures();
	}
	
	
	private Set<FiringListener> listeners = new HashSet<>();
	
	public void addListener(FiringListener f) {
		listeners.add(f);
	}
	
	public void removeListener(FiringListener f) {
		listeners.remove(f);
	}
	
	private void notifyFiring(PNIDBinding binding) {
		for(FiringListener f: listeners) {
			f.onBindingFired(binding, currentWorld, Collections.unmodifiableMap(enabledBindings), Collections.unmodifiableMap(disabledBindings));
		}
	}
	
	
	
}
