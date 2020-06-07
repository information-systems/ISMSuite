package org.informationsystem.ismsuite.modeler.process.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID;
import org.pnml.tools.epnk.pnmlcoremodel.ID;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class UniqueIDChecker {

	public UniqueIDChecker() {
	}
	
	public static boolean checkPetrinet(PetriNet petrinet) {
		if (!(petrinet.getType() instanceof PNID)) {
			return false;
		}
		
		return giveErrorsFor(petrinet).isEmpty();
	}
	
	public static List<SyntaxError> giveErrorsFor(PetriNet petrinet) {
		List<SyntaxError> result = new ArrayList<>();
		
		TreeIterator<EObject> iterator = petrinet.eAllContents();
		
		HashMap<String, Set<ID>> elements = new HashMap<>();
		
		while (iterator.hasNext()) {
			EObject object = iterator.next();
		
			if (object instanceof ID) {
				addElement(((ID) object).getId(), (ID) object, elements);
			}
		}
		
		for(Entry<String, Set<ID>> entry : elements.entrySet() ) {
			if (entry.getValue().size() > 1) {
				result.add(new IDNotUnique(entry.getValue()));
			}
		}
		
		return result;
	}
	
	public static void addElement(String id, ID object, HashMap<String, Set<ID>> elements) {
		if (!elements.containsKey(id)) {
			elements.put(id, new HashSet<>());
		}
		elements.get(id).add(object);
	}

}
