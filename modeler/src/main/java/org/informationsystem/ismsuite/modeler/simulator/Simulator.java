package org.informationsystem.ismsuite.modeler.simulator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.simulator.BasicPNIDSimulator;
import org.informationsystem.ismsuite.modeler.process.simulator.PNIDBinding;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;
import org.pnml.tools.epnk.pnmlcoremodel.TransitionNode;

public class Simulator extends BasicPNIDSimulator {

	private Specification specification;
	private World initialWorld;
	
	public Simulator(PetriNet petrinet, Specification specification, World world) {
		super(petrinet);
		this.specification = specification;
		this.initialWorld = world;
	}

	private SimulationView viewer;
	
	private ISMEngine ismEngine;
	
	@Override
	public void initializeContents() {
		initializeSimulator();
		
		HashMap<String, String> transitionTransactionMapping = new HashMap<>();
		for (Transition t: getFlatAccess().getTransitions()) {
			if (t instanceof org.informationsystem.ismsuite.modeler.process.pnid.pnids.Transition) {
				String label = t.getId();
				org.informationsystem.ismsuite.modeler.process.pnid.pnids.Transition trans = (org.informationsystem.ismsuite.modeler.process.pnid.pnids.Transition) t;
				if (trans.getTransaction()!= null && (!trans.getTransaction().getText().isEmpty())) {
					transitionTransactionMapping.put(label, trans.getTransaction().getText());
				} else {
					transitionTransactionMapping.put(label, label);
				}
			}
		}
		
		ismEngine = new ISMEngine(this.initialWorld, this.specification, this.getEngine().getMarkedPetriNet(), transitionTransactionMapping);
		
		try {
			viewer = (SimulationView) PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(SimulationView.ID);
			
			viewer.setSimulator(this);
			addListener(viewer);
		
			if (!ismEngine.initializeWorld()) {
				MessageDialog.openWarning(
						Display.getCurrent().getActiveShell(), 
						"Warning initializing simulator", 
						ismEngine.getError());
			}
			
			if (!ismEngine.initialize()) {
				MessageDialog.openWarning(
						Display.getCurrent().getActiveShell(), 
						"Warning initializing simulator", 
						ismEngine.getError());
			}
			
			notifyFiring(null);
			
			generateCurrentAnnotations();

			
		} catch (PartInitException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Do not execute the generateCurrentAnnotations
	 * if the ismEngine is not set.
	 */
	@Override
	public void generateCurrentAnnotations() {
		if (ismEngine == null) {
			return;
		}
		super.generateCurrentAnnotations();
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
		Set<Transition> result = new HashSet<>();
		for(String t: ismEngine.getEnabledTransitions()) {
			Transition transition = getEngine().getTransition(t);
			result.add(transition);
		}
		return result;
	}
	
	@Override
	public Set<PNIDBinding> getBindings(TransitionNode transitionNode) {
		Transition transition = getFlatAccess().resolve(transitionNode);
		Set<PNIDBinding> result = new HashSet<>();
		
		for(Binding b: ismEngine.getBindings(transition.getId())) {
			result.add(transformToPNIDBinding(b));
		}
		
		return result;
	}
	
	/**
	 * This map keeps the binding mapping between PNID net and MarkedPetriNet.
	 * Note that it is reset after every firing!
	 */
	private Map<PNIDBinding, Binding> currentBindings = new HashMap<>();
	
	/**
	 * Transforms a given Binding in terms of a PNID (of the modeler)
	 * to a Binding in terms of a MarkedPetriNet. Note that we only use
	 * the mapping of currentBindings!
	 * 
	 * @param b
	 * @return
	 */
	private Binding transformToBinding(PNIDBinding b) {
		return currentBindings.get(b);
	}
	
	/**
	 * Transforms a binding in terms of a MarkedPetriNet to a binding in terms of a PNID (modeler)
	 * @param b
	 * @return
	 */
	private PNIDBinding transformToPNIDBinding(Binding b) {
		Transition transition = getEngine().getTransition(b.getTransition());
		
		Map<org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable, org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity> valuation = new HashMap<>();

		for(Entry<String, String> val: b.getValuation().entrySet()) {
			org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable var = PnidsFactory.eINSTANCE.createVariable();
			var.setText(val.getKey());
			org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity entity = PnidsFactory.eINSTANCE.createEntity();
			entity.setText(val.getValue());
			valuation.put(var, entity);
		}
		
		PNIDBinding binding = new PNIDBinding(transition, valuation);
		currentBindings.put(binding, b);
		
		return binding;
	}
	
	@Override
	public void fire(PNIDBinding b) {
		
		if (ismEngine.fire(transformToBinding(b))) {
			this.getEngine().fire(b);
			// Reset the current bindings
			currentBindings = new HashMap<>();
			// Update the annotations
			generateCurrentAnnotations();
			// Inform everyone that the firing succeeded. 
			notifyFiring(b);
		}
	}
	
	public FirstOrderLogicWorld getWorld() {
		return ismEngine.getCurrentWorld();
	}
	
	public Set<Entry<String,Clause>> getConjectures() {
		return initialWorld.getConjectures();
	}
	
	public Set<PNIDBinding> getEnabledBindings() {
		Set<PNIDBinding> result = new HashSet<>();
		
		for(Binding b: ismEngine.getBindings()) {
			result.add(transformToPNIDBinding(b));
		}
		
		return result;
	}
	
	public Map<PNIDBinding, String> getDisabledBindings() {
		Map<PNIDBinding, String> result = new HashMap<>();
		
		for(Entry<Binding, String> b: ismEngine.getDisabledBindings().entrySet()) {
			result.put(transformToPNIDBinding(b.getKey()), b.getValue());
		}
		
		return result;
	}
	
	public Map<PNIDBinding, String> getWarnedBindings() {
		Map<PNIDBinding, String> result = new HashMap<>();
		
		for(Entry<Binding, String> b: ismEngine.getWarnedBindings().entrySet()) {
			result.put(transformToPNIDBinding(b.getKey()), b.getValue());
		}
		
		return result;
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
			f.onBindingFired(binding, ismEngine.getCurrentWorld(), getEnabledBindings(), getDisabledBindings(), getWarnedBindings());
		}
	}
}