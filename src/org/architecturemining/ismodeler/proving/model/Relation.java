package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Relation extends Literal {
	
	private List<Literal> parameters;
	private boolean mIsAbstract;
	private String mString;
	
	public Relation(String label, List<Literal> params) {
		super(label);
		parameters = params;
		
		updateState();
	}
	
	public Relation(String label, Literal ... params) {
		super(label);
		parameters = new ArrayList<>();
		for(Literal p : params) {
			parameters.add(p);
		}
		
		updateState();
	}


	private void updateState() {
		StringBuilder sb = new StringBuilder();
		mIsAbstract = false;
		sb.append("REL: ");
		sb.append(getLabel());
		sb.append("(");
		for(Literal p : parameters) {
			sb.append("[");
			sb.append(p.toString());
			sb.append("] ");
			if (p.isAbstract()) {
				mIsAbstract = true;
			}
		}
		sb.append(" )");
		mString = sb.toString();
	}
	
	public boolean isInstantiateable(int index) {
		if (index < 0 || index >= size() ) {
			return false;
		}
		return (parameters.get(index) instanceof Variable);
	}
	
	/**
	 * This method updates a parameter. The update is only allowed if
	 * the Literal to be changed is a Variable.
	 * @param index
	 * @param l
	 * @return
	 * @throws Exception 
	 */
	public Relation instantiateParameter(int index, Literal replace) throws Exception {
		if (!isInstantiateable(index)) {
			throw new Exception("Parameter cannot be replaced");
		}
		List<Literal> params = new ArrayList<>();
		for(int i = 0 ; i < parameters.size(); i++) {
			if (i == index) {
				params.add(replace);
			} else {
				params.add((Literal) parameters.get(i).clone());
			}
		}
		return new Relation(getLabel(), params);
	}
	
	/**
	 * @return true if one of the literals is abstract.
	 */
	@Override
	public boolean isAbstract() {
		return mIsAbstract;
	}
	
	@Override
	public String toString() {
		return mString;
	}
	
	public Iterator<Literal> iterator() {
		return parameters.iterator();
	}
	
	public int size() {
		return parameters.size();
	}
	
	public Literal get(int index) throws IndexOutOfBoundsException {
		return parameters.get(index);
	}
	
	@Override
	public Object clone() {
		ArrayList<Literal> params = new ArrayList<>();
		for(Literal p: parameters) {
			params.add((Literal) p.clone());
		}
		return new Relation(getLabel(), params);
	}
	
}
