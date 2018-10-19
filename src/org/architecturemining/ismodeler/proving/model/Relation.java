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
	private boolean mIsAbstract;
	private String mString;
	
	public Relation(String label, List<Literal> parameters) {
		super(label);
		this.parameters = parameters;
		
		updateSettings();
	}
	
	private void updateSettings() {
		// Check whether the parameters are all non-abstract.
		mIsAbstract = false;
		StringBuilder sb = new StringBuilder();
		sb.append("REL: ");
		sb.append(getLabel());
		sb.append("(");
		for(Literal param : parameters) {
			if (param.isAbstract()) {
				mIsAbstract = true;
			}
			sb.append("[");
			sb.append(param.toString());
			sb.append("] ");
		}
		sb.append(")");
		mString = sb.toString();
	}
	
	/**
	 * @return true if one of the literals is abstract.
	 */
	@Override
	public boolean isAbstract() {
		return mIsAbstract;
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
	
	public int getParameterCount() {
		return parameters.size();
	}
	
	public Object clone() {
		// Relation clone = new Relation(this.getLabel(), parameters.clone());
		// return clone;
		return null;
	}
	
	/*
	@Override
	public boolean equals(Object o) {
		if (o instanceof Relation) {
			Relation r = (Relation) o;
			if (!r.getLabel().equals(getLabel())) {
				return false;
			}
			if (r.getParameterCount() != parameters.size()) {
				return false;
			}
			
			for(int i = 0; i < this.getParameters().size(); i++) {
				if (!r.getParameters().get(i).equals(getParameters().get(i))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	} */
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Relation) {
			return o.toString().equals(this.toString());
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return mString;
	}
	
}
