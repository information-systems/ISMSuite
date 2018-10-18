package org.architecturemining.ismodeler.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Specification {

	private HashMap<String,String> types;
	private ArrayList<Label> labels;
	private HashMap<String,PredicateDefinition> initialPopulation;
	
	private HashMap<String,List<Operation>> transactions;
	
	public Specification() {
		this.types = new HashMap<String,String>();
		this.labels = new ArrayList<Label>();
		this.transactions = new HashMap<String,List<Operation>>();
		this.initialPopulation = new HashMap<String, PredicateDefinition>();
	}
	
	public void addTransaction(String key, List<Operation> transaction) {
		this.transactions.put(key, transaction);
	}
	
	public List<Operation> getTransactionFor(String key) {
		return transactions.get(key);
	}
	
	public Map<String,List<Operation>> getTransactions() {
		return this.transactions;
	}
	
	public Set<String> getValueTypes() {
		return this.types.keySet();
	}
	
	public void addValueType(String key, String value) {
		types.put(key, value);
	}
	
	public void addValueTypes(Map<String,String> values) {
		types.putAll(values);
	}
	
	public String getValueTypeOf(String object) {
		return types.get(object);
	}
	
	public List<Label> getLabels() {
		return this.labels;
	}
	
	public void addLabel(Label l) {
		this.labels.add(l);
	}
	
	public void addPopulation(String key, PredicateDefinition p) {
		this.initialPopulation.put(key, p);
	}
	
	public PredicateDefinition getInitialPopulationTypeFor(String place) {
		return initialPopulation.get(place);
	}
	
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		// Print types:
		b.append("types:\n");
		for(String key: this.types.keySet()) {
			b.append(types.get(key));
			b.append(" { ");
			b.append(key);
			b.append(" }\n");
		}
		b.append("\n");
		
		// Print initial Population
		b.append("initialpopulation:\n");
		for(String key: this.initialPopulation.keySet()) {
			b.append(key);
			b.append(": ");
			b.append(initialPopulation.get(key));
			b.append("\n");
		}
		b.append("\n");
		// Print Labels
		b.append("labels:\n");
		for(Label l : this.labels) {
			b.append(l.toString());
			b.append("\n");
		}
		b.append("\n");
		
		// Print transitions
		b.append("\n");
		for(String key: this.getTransactions().keySet()) {
			List<Operation> trans = this.getTransactionFor(key);
			b.append("Transition: " + key);
			b.append("\n");
			for(Operation op: trans) {
				b.append(op.toString());
				b.append("\n");
			}
			b.append("\n");
		}
		
		return b.toString();
	}
}
