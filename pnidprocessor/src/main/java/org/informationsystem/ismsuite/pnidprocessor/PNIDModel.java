package org.informationsystem.ismsuite.pnidprocessor;

import java.util.Collection;
import java.util.Map;

import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.pnidprocessor.petrinet.MarkedPetriNet;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.ProcessModel;
import org.informationsystem.ismsuite.processengine.process.Token;

public class PNIDModel implements ProcessModel {
	
	private MarkedPetriNet net;
	
	public MarkedPetriNet getPetriNet() {
		return net;
	}
	
	public PNIDModel(MarkedPetriNet petriNet) {
		this.net = petriNet;
	}

	@Override
	public Map<String, MultiSet<Token>> getCurrentMarking() {
		return net.getMarking().map();
	}

	@Override
	public Collection<String> getPlaces() {
		return net.places();
	}

	@Override
	public Collection<String> getTransitions() {
		return net.transitions();
	}

	@Override
	public Collection<Binding> getEnabledTransitions() {
		return net.getBindings();
	}

	@Override
	public boolean fire(Binding binding) {
		return net.fire(binding);
	}

	@Override
	public boolean terminate() {
		return true;
	}

}
