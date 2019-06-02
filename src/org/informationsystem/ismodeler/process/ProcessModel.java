package org.informationsystem.ismodeler.process;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Variable;

public interface ProcessModel {

	Map<String, MultiSet<Token>> getCurrentMarking();
	
	Collection<String> getPlaces();
	Collection<String> getTransitions();
	Collection<BoundTransition> getEnabledTransitions();
	
	boolean fire(BoundTransition transition);
	
	
	boolean terminate();
}
