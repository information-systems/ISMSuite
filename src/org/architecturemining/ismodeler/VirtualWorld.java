package org.architecturemining.ismodeler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.architecturemining.ismodeler.proving.GeneralProverException;
import org.architecturemining.ismodeler.proving.Prover;
import org.architecturemining.ismodeler.proving.SyntaxException;
import org.architecturemining.ismodeller.model.Constraint;
import org.architecturemining.ismodeller.model.Label;
import org.architecturemining.ismodeller.model.Population;
import org.architecturemining.ismodeller.model.Specification;

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
		
		Prover p = Prover.getInstance();
		String proofText = tffHeader + population.toTFF();
		
		try {
			
			for(Constraint c: constraints.values()) {
				boolean proofResult;
				proofResult = p.proof(proofText, c.toTFF());
				System.out.println(proofText);
				System.out.println(c.toTFF());
				System.out.println("I got proof: " + proofResult);
				System.out.println("Constraint : " + c.getDesiredOutcome());
				if (proofResult != c.getDesiredOutcome()) {
					results.add(c.getId());
				}
			}
		
		} catch (GeneralProverException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		return results;
	}
}
