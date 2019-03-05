package org.informationsystem.ismodeler.process;

import java.util.HashMap;
import java.util.Iterator;

class Binding implements Iterable<Binding.BindingElement> {
	
	class BindingElement {
		private String key;
		private long value;
		
		public BindingElement(String key, long value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}
		
		public long getValue() {
			return value;
		}
	}
	
	private HashMap<String, BindingElement> variables = new HashMap<>();
	
	public boolean contains(Object o) {
		return variables.containsKey(o);
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
	
}
