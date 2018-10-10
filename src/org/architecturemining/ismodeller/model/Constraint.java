package org.architecturemining.ismodeller.model;

public class Constraint {

	private String id;
	private String name;

	private String constraint;
	private boolean desiredOutcome = true;
	
	public Constraint(String id, String name, String constraint) {
		this(id, name, constraint, true);
	}
	
	public Constraint(String id, String name, String constraint, boolean desiredOutcome) {
		this.id = id;
		this.name = name;
		this.constraint = constraint;
		this.desiredOutcome = desiredOutcome;
	}
	
	public boolean getDesiredOutcome() {
		return this.desiredOutcome;
	}
	
	/**
	 * 
	 * @param proveToFalse Set to TRUE if you want the constraint to hold if the resulting proof holds
	 */
	public void setDesiredOutcome(boolean proveToFalse) {
		this.desiredOutcome = proveToFalse;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getConstraint() {
		return this.constraint;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(" (");
		sb.append(id);
		sb.append("): ");
		sb.append(constraint);
		
		return sb.toString();
	}
	
	public String toTFF() {
		StringBuilder sb = new StringBuilder();
		sb.append("tff( ");
		sb.append(id);
		sb.append(", conjecture, ");
		sb.append(constraint);
		sb.append(").");
		
		return sb.toString();
	}
}
