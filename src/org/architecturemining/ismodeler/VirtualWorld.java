package org.architecturemining.ismodeler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.architecturemining.ismodeler.proving.GeneralProverException;
import org.architecturemining.ismodeler.proving.Prover;
import org.architecturemining.ismodeler.proving.SyntaxException;
import org.architecturemining.ismodeler.model.Constraint;
import org.architecturemining.ismodeler.model.Label;
import org.architecturemining.ismodeler.model.Population;
import org.architecturemining.ismodeler.model.Specification;

public class VirtualWorld {

	private HashMap<String, Constraint> constraints;
	
	private Specification specification;
	
	private String tffHeader;
	
	public VirtualWorld(Specification spec, List<Constraint> constraints) {
		this.specification = spec;
		this.constraints = new HashMap<String, Constraint>();

		for(Constraint c: constraints) {
			this.constraints.put(c.getId(), c);
		}
		StringBuilder sb = new StringBuilder();
		for(Label l : this.specification.getLabels()) {
			sb.append(l.toTFF());
			sb.append("\n");
		}
		for(String v: this.specification.getValueTypes()) {
			sb.append("tff( ");
			sb.append(v);
			sb.append("_type, type, ");
			sb.append(v);
			sb.append(": $tType).\n");
		}
		tffHeader = sb.toString();
	}
	
	public Constraint getConstraint(String id) {
		return constraints.get(id);
	}
	
	/**
	 * Returns the IDs of all constraints that *FAILED* on the population 
	 * @param population
	 * @return the IDs of the failed constraints
	 * @throws SyntaxException 
	 */
	public List<String> validate(Population population) throws SyntaxException {
		ArrayList<String> results = new ArrayList<String>();
		
		if (population.isEmpty()) {
			return results;
		}
		
		return results;
	}
}
