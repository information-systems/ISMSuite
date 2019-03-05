package org.informationsystem.ismodeler.process;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.proving.model.Element;
import org.informationsystem.proving.model.Variable;

public interface ProcessModel {

	Map<String, MultiSet<Token>> getCurrentMarking();
	
	List<String> getPlaces();
	List<String> getTransitions();
	List<BoundTransition> getEnabledTransitions();
	
	boolean fire(BoundTransition transition);
	
}
