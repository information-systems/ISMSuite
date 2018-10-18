package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Relation extends Literal {
	
	private List<Literal> parameters;
	
	/**
	 * We directly calculate the isabstract in the Constructor,
	 * and only update it if a parameter is changed, to be more
	 * efficient, as isAbstract is called a lot.
	 */
	private boolean isabstract;
	private String mystring;
	
	public Relation(String label, List<Literal> parameters) {
		super(label);
		this.parameters = parameters;
		
		updateSettings();
	}
	
	private void updateSettings() {
		// Check whether the parameters are all non-abstract.
		isabstract = false;
		StringBuilder sb = new StringBuilder();
		sb.append("REL: ");
		sb.append(getLabel());
		for(Literal param : parameters) {
			if (param.isAbstract()) {
				isabstract = true;
			}
			sb.append("_!!!_");
			sb.append(param.toString());
		}
		
		mystring = sb.toString();
	}
	
	/**
	 * @return true if one of the literals is abstract.
	 */
	@Override
	public boolean isAbstract() {
		return isabstract;
	}
	
	@Override
	public boolean isComplex() {
		return true;
	}
	
	public List<Literal> getParameters() {
		return parameters;
	}
	
	public void setParameter(int index, Literal parameter) {
		if (index > 0 && index < parameters.size()) {
			parameters.set(index, parameter);
			// update isabstract
			updateSettings();
		}
	}
	
	public Object clone() {
		Relation clone = new Relation(this.getLabel(), this.getParameters());
		return clone;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Relation) {
			Relation r = (Relation) o;
			if (!r.getLabel().equals(getLabel())) {
				return false;
			}
			if (r.getParameters().size() != getParameters().size()) {
				return false;
			}
			
			for(int i = 0; i < this.getParameters().size(); i++) {
				if (r.getParameters().get(i) != getParameters().get(i)) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return mystring;
	}
	
}
