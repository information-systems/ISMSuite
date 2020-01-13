package org.informationsystem.ismsuite.processengine.process;

import java.util.Collection;
import java.util.Map;

public interface ProcessModel {

	Map<String, MultiSet<Token>> getCurrentMarking();
	
	Collection<String> getPlaces();
	Collection<String> getTransitions();
	Collection<Binding> getEnabledTransitions();
	
	boolean fire(Binding binding);
	
	
	boolean terminate();
}
