package org.informationsystem.ismodeler.process.cpntools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getPlaces() {
		return places.keySet();
	}

	@Override
	public Collection<String> getTransitions() {
		return transitions.keySet();
	}

	@Override
	public Collection<BoundTransition> getEnabledTransitions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean fire(BoundTransition transition) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
