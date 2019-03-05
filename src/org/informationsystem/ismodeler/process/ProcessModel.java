package org.informationsystem.ismodeler.process;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.ismodeler.proving.model.Element;
import org.informationsystem.ismodeler.proving.model.Variable;

public interface ProcessModel {

	Map<String, MultiSet<Token>> getCurrentMarking();
	
	List<String> getPlaces();
	List<String> getTransitions();
	List<Entry<String, Binding>> getEnabledTransitions();
	
	boolean fire(String transition);
	
}
