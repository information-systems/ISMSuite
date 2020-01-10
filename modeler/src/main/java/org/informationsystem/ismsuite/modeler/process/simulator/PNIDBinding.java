package org.informationsystem.ismsuite.modeler.process.simulator;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

public class PNIDBinding {

	private Transition transition;
	private Map<Variable, Entity> valuation;
	
	/**
	 * Note that we make the valuation immutable, so that it cannot be changed
	 * by others.
	 * @param transition
	 * @param valuation
	 */
	public PNIDBinding(Transition transition, Map<Variable, Entity> valuation) {
		this.transition = transition;
		this.valuation = Collections.unmodifiableMap(valuation);
	}
	
	public static PNIDBinding createBinding(Transition transition, Map<String, String> valuations) {
		HashMap<Variable, Entity> mapping = new HashMap<>();
		for(Map.Entry<String, String> entry: valuations.entrySet()) {
			Variable v = PnidsFactory.eINSTANCE.createVariable();
			v.setText(entry.getKey());
			Entity e = PnidsFactory.eINSTANCE.createEntity();
			e.setText(entry.getValue());
			mapping.put(v, e);
		}
		return new PNIDBinding(transition, mapping);
	}
	
	public Transition getTransition() {
		return transition;
	}
	
	public Map<Variable, Entity> getValuation() {
		return valuation;
	}
		
	
	@Override
	public String toString() {
		return toString(true);
	}
	
	public String toString(boolean showTransition) {
		StringBuilder sb = new StringBuilder();
		
		if (showTransition)
		{
			String name;
			if (transition.getName() != null && transition.getName().getText() != null && !transition.getName().getText().isEmpty()) {
				name = transition.getName().getText();
			} else if (transition.getId() != null && !transition.getId().isEmpty() ) {
				name = transition.getId();
			} else {
				name = transition.toString();
			}
			sb.append(name);
			sb.append(" ");
		}
		sb.append("{ ");
		
		boolean first = true;
		for (Map.Entry<Variable, Entity> e: valuation.entrySet()) {
			if (!first) {
				sb.append(", ");
			}
			sb.append(e.getKey().getText());
			sb.append(": ");
			sb.append(e.getValue().getText());
			first = false;
		}
		
		sb.append(" }");
		return sb.toString();
	}
	
}
