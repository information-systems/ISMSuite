package org.informationsystem.ismsuite.processengine.process.cpntools;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
import org.cpntools.accesscpn.engine.highlevel.instance.Marking;
import org.cpntools.accesscpn.engine.highlevel.instance.State;
import org.cpntools.accesscpn.engine.highlevel.instance.ValueAssignment;
import org.cpntools.accesscpn.engine.proxy.ProxyDaemon;
import org.cpntools.accesscpn.engine.proxy.ProxySimulator;
import org.cpntools.accesscpn.model.PlaceNode;
import org.cpntools.accesscpn.model.Transition;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.ProcessModel;
import org.informationsystem.ismsuite.processengine.process.Token;

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
	
	private Map<Binding, org.cpntools.accesscpn.engine.highlevel.instance.Binding> enabledTransitions; 

	@Override
	public Collection<Binding> getEnabledTransitions() {
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
				try {
					List<org.cpntools.accesscpn.engine.highlevel.instance.Binding> bindings = simulator.getBindings(t.getValue());
				
					for(org.cpntools.accesscpn.engine.highlevel.instance.Binding b: bindings) {
						Map<String, String> valuation = new HashMap<>();
						for(ValueAssignment a: b.getAllAssignments()) {
							valuation.put(a.getName(), a.getValue());
						}
						Binding binding = new Binding(b.getTransitionInstance().toString(), valuation );
						
						enabledTransitions.put(binding, b);
					}
				} catch(NoSuchElementException e) {
					// just ignore the error, apparently, the 
					// transition does not exist...
					System.out.println("Could not find transition: " + t.getKey());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	@Override
	public boolean fire(Binding binding) {
		// Get the binding from the enabled transitions
		if (!enabledTransitions.containsKey(binding)) {
			return false;
		}
				
		try {
			simulator.execute(enabledTransitions.get(binding));
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
		return true;
	}
}
