package org.informationsystem.ismodeler.process.cpntools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.instance.Binding;
import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
import org.cpntools.accesscpn.engine.highlevel.instance.Marking;
import org.cpntools.accesscpn.engine.highlevel.instance.State;
import org.cpntools.accesscpn.engine.highlevel.instance.ValueAssignment;
import org.cpntools.accesscpn.engine.proxy.ProxyDaemon;
import org.cpntools.accesscpn.engine.proxy.ProxySimulator;
import org.cpntools.accesscpn.model.PetriNet;
import org.cpntools.accesscpn.model.Place;
import org.cpntools.accesscpn.model.PlaceNode;
import org.cpntools.accesscpn.model.Transition;
import org.informationsystem.ismodeler.process.BoundTransition;
import org.informationsystem.ismodeler.process.MultiSet;
import org.informationsystem.ismodeler.process.ProcessModel;
import org.informationsystem.ismodeler.process.Token;

public class CPNModel implements ProcessModel {

	private static CPNModel model;
	
	// Singleton pattern!
	public static CPNModel getInstance() throws Exception {
		if (model == null) {
			
			System.out.println("Starting proxy");
			final ProxyDaemon pd = ProxyDaemon.getDefaultInstance();
			
			// Wait for a CPN Tools client to start and a model to be loaded.
			System.out.println("Start up CPN Tools with the specific net to continue.");
			final ProxySimulator ps = pd.getNext();
			
			// Check the model's syntax
			System.out.println("Waiting for syntax check");
			while (ps.getPetriNet() == null)
				Thread.sleep(500);
			
			final PetriNet petriNet = ps.getPetriNet();
			
			// Check the model's syntax
			System.out.println("Get Simulator");
			while (ps.getSimulator() == null)
				Thread.sleep(500);
			
			model = new CPNModel(ps.getSimulator());
		}
		
		return model;
	}
	
	private final HighLevelSimulator simulator;
	private Map<String, Instance<Transition>> transitions;
	private Map<String, Instance<PlaceNode>> places;
	
	private CPNModel(HighLevelSimulator simulator) {
		
		this.simulator = simulator;
		this.transitions = new HashMap<>();
		this.places = new HashMap<>();
		
		try {
			List<Instance<Transition>> transitionInstances= simulator.getAllTransitionInstances();
			for(Instance<Transition> t: transitionInstances) {
				transitions.put(t.toString(), t);
			}
			
			List<Instance<PlaceNode>> placeInstances = simulator.getAllPlaceInstances();
			for(Instance<PlaceNode> p: placeInstances) {
				places.put(p.toString(), p);
			}
		} catch (Exception e) {
		}
	}
	
	@Override
	public Map<String, MultiSet<Token>> getCurrentMarking() {
		Map<String, MultiSet<Token>> marking = new HashMap<>();
		try {
			State state = simulator.getMarking();
			for( Marking m : state.getAllMarkings()) {
				marking.put(m.getPlaceInstance().toString(), TokenParser.parse(m.getMarking()));
			}
		} catch (Exception e) {
		}		
		return marking;
	}

	@Override
	public Collection<String> getPlaces() {
		return places.keySet();
	}

	@Override
	public Collection<String> getTransitions() {
		return transitions.keySet();
	}
	
	private Map<BoundTransition, Binding> enabledTransitions; 

	@Override
	public Collection<BoundTransition> getEnabledTransitions() {
		if (enabledTransitions == null) {
			updateEnabledTransitions();
		}
		return enabledTransitions.keySet();
	}
	

	/**
	 * Should be called after every execute()
	 */
	private void updateEnabledTransitions() {
		enabledTransitions = new HashMap<>();
		try {
			for(Entry<String, Instance<Transition>> t : transitions.entrySet()) {
				List<Binding> bindings = simulator.getBindings(t.getValue());
			
				for(Binding b: bindings) {
					org.informationsystem.ismodeler.process.Binding binding = new org.informationsystem.ismodeler.process.Binding();
					for(ValueAssignment a: b.getAllAssignments()) {
						binding.set(a.getName(), Long.parseLong(a.getValue()));
					}
					enabledTransitions.put(new BoundTransition(b.getTransitionInstance().toString(), binding), b);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	@Override
	public boolean fire(BoundTransition transition) {
		// Get the binding from the enabled transitions
		if (!enabledTransitions.containsKey(transition)) {
			return false;
		}
		
		Binding b = enabledTransitions.get(transition);
		
		try {
			simulator.execute(b);
			// Update the CPN Tools view
			simulator.refreshViews();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		updateEnabledTransitions();
		return true;
	}

	@Override
	public boolean terminate() {
		simulator.destroy();
		this.model = null;
		return true;
	}
}
