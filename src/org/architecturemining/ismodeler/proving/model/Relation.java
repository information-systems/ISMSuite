package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Relation extends Literal {
	
	private List<Literal> parameters;
	private boolean mIsAbstract;
	
	public Relation(String label, List<Literal> params) {
		super(label);
		parameters = params;
		
		calculateProperties();
	}
	
	public Relation(String label, Literal ... params) {
		super(label);
		parameters = new ArrayList<>();
		for(Literal p : params) {
			parameters.add(p);
		}
		
		calculateProperties();
	}

	@Override
	protected void calculateProperties() {
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
	
	/**
	 * @return true if one of the literals is abstract.
	 */
	@Override
	public boolean isAbstract() {
		return mIsAbstract;
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
	
	@Override
	public void instantiate(Variable x, Element a) {
		for(int i = 0 ; i < size() ; i++) {
			if (parameters.get(i).equals(x)) {
				parameters.set(i, a);
			} else {
				parameters.get(i).instantiate(x, a);
			}
		}
		calculateProperties();
	}
	
}
