package org.informationsystem.ismsuite.modeler.simulator;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.informationsystem.ismsuite.ismsuite.model.Controller;
import org.informationsystem.ismsuite.ismsuite.model.Model;
import org.informationsystem.ismsuite.ismsuite.model.StateChangedListener;
import org.informationsystem.ismsuite.modeler.process.simulator.BasicPNIDSimulator;
import org.informationsystem.ismsuite.modeler.process.simulator.PNIDBinding;
import org.informationsystem.ismsuite.pnidprocessor.PNIDModel;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;
import org.pnml.tools.epnk.pnmlcoremodel.TransitionNode;

public class Simulator extends BasicPNIDSimulator implements StateChangedListener {

	private Specification specification;
	private FirstOrderLogicWorld world;
	
	public Simulator(PetriNet petrinet, Specification specification, FirstOrderLogicWorld world) {
		super(petrinet);
		this.specification = specification;
		this.world = world;
	}
	
	private Controller controller;

	private SimulationView viewer;
	
	
	@Override
	public void initializeContents() {
		initializeSimulator();
				
		controller = new Controller(
				new PNIDModel(getEngine().getMarkedPetriNet()),
				specification,
				world
				);
		controller.getModel().addListener(this);
		
		try {
			viewer = (SimulationView) PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(SimulationView.ID);
			viewer.setController(controller);
			
			update(controller.getModel());
			
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
		Transition t = getFlatAccess().resolve(transitionNode);
		if (enabledTransitions.containsKey(t)) {
			return enabledTransitions.get(t);
		} else {
			return Collections.emptySet();
		}
	}
	
	@Override
	public void fire(PNIDBinding b) {
		if(enabledBindings.containsKey(b)) {
			System.out.println("Firing: " + b.getTransition().getId());
			getEngine().fire(b);
			controller.fire(enabledBindings.get(b));
			generateCurrentAnnotations();
		}
	}
	
	private Map<PNIDBinding, Binding> enabledBindings = new HashMap<>();
	private Map<Transition, Set<PNIDBinding>> enabledTransitions = new HashMap<>();
	
	@Override
	public void update(Model model) {
		enabledTransitions = new HashMap<>();
		enabledBindings = new HashMap<>();
		
		for(Binding b: model.enabledTransitions()) {
			Transition transition = getEngine().getTransition(b.getTransition());
			PNIDBinding binding = PNIDBinding.createBinding(transition, b.getValuation());
			if (!enabledTransitions.containsKey(transition)) {
				enabledTransitions.put(transition, new HashSet<>());
			}
			enabledTransitions.get(transition).add(binding);
			enabledBindings.put(binding, b);
		}
		
		generateCurrentAnnotations();
	}
}
