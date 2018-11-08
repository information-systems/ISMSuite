package org.architecturemining.ismodeler.process;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.architecturemining.ismodeler.proving.model.Element;
import org.architecturemining.ismodeler.proving.model.Variable;

public interface ProcessModel {

	Map<String, MultiSet<Element>> initialMarking();
	
	List<String> places();
	List<String> transitions();
	List<Entry<String, Map<Variable, Element>>> enabledTransitions();
	
	boolean fire(String transition);
	
}
