package org.informationsystem.ismsuite.processengine.process;

import java.util.Collection;
import java.util.Map;

public interface ProcessModel {

	Map<String, MultiSet<Token>> getCurrentMarking();
	
	Collection<String> getPlaces();
	Collection<String> getTransitions();
	Collection<BoundTransition> getEnabledTransitions();
	
	boolean fire(BoundTransition transition);
	
	
	boolean terminate();
}
