package org.informationsystem.ismsuite.processengine.process;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.informationsystem.ismsuite.processengine.process.Token;

import java.util.List;


public class Binding {

	private String transition;
	private Map<String, String> valuation;

	public Binding(String transition, Map<String, String> valuation) {
		this.transition = transition;
		this.valuation = Collections.unmodifiableMap(valuation);
	}

	public String getTransition() {
		return transition;
	}

	public Map<String, String> getValuation() {
		return valuation;
	}

	public Token instantiate(List<String> variables) {
		String[] varArray = new String[variables.size()];
		return instantiate(variables.toArray(varArray));
	}

	public Token instantiate(String... variables) {
		String[] identities = new String[variables.length];
		for(int i = 0 ; i < variables.length ; i++) {
			if (!valuation.containsKey(variables[i])) {
				return null;
			}
			identities[i] = valuation.get(variables[i]);
		}
		return new Token(identities);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getTransition());
		sb.append(" [");
		boolean notfirst = false;
		for(Map.Entry<String, String> v: valuation.entrySet()) {
			if (notfirst) {
				sb.append(", ");
			}
			sb.append(v.getKey());
			sb.append(": ");
			sb.append(v.getValue());
			notfirst = true;
		}
		sb.append("]");
		return sb.toString();
	}

}
