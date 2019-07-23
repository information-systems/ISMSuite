package org.informationsystem.ismsuite.processengine.process;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Binding implements Iterable<Binding.BindingElement> {
	
	public class BindingElement {
		private String variable;
		private long value;
		
		public BindingElement(String key, long value) {
			this.variable = key;
			this.value = value;
		}
		
		public String getVariable() {
			return variable;
		}
		
		public long getValue() {
			return value;
		}
	}
	
	private Map<String, BindingElement> variables = new HashMap<>();

	
	public boolean contains(Object o) {
		return variables.containsKey(o);
	}
	
	public int size() {
		return variables.size();
	}
	
	public void set(String key, long value) {
		if (variables.containsKey(key)) {
			variables.get(key).value = value;
		} else {
			variables.put(key, new BindingElement(key, value));
		}
		
	}
	
	public long get(String key) {
		BindingElement item = variables.get(key);
		if (item == null) {
			return -1;
		} else {
			return item.value;
		}
	}

	@Override
	public Iterator<BindingElement> iterator() {
		return variables.values().iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for(BindingElement e: this) {
			b.append(", ");
			b.append(e.variable);
			b.append(" = ");
			b.append(e.value);
		}
		
		return b.substring(2);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o.getClass() != this.getClass()) {
			return false;
		}
		Binding b = (Binding) o;
		if (b.size() != this.size()) {
			return false;
		}
		for(BindingElement var: variables.values()) {
			if (!(b.contains(var.getVariable()) && b.get(var.getVariable()) == var.getValue())) {
				return false;
			}
		}
		return true;
	}
	
}
