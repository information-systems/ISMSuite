package org.informationsystem.ismodeler.process;

public class BoundTransition implements Comparable<BoundTransition> {

	private String name;
	private Binding binding;
	
	public BoundTransition(String name, Binding binding) {
		this.name = name;
		this.binding = binding;
	}
	
	public String getName() {
		return name;
	}
	
	public Binding getBinding() {
		return binding;
	}
	
	@Override
	public String toString() {
		return this.name + " (" + this.binding + ")";
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o.getClass() != this.getClass()) {
			return false;
		}
		BoundTransition b = (BoundTransition) o;
		return (b.getName().equals(this.getName()) 
				&& (b.getBinding().equals(this.getBinding())));
	}

	@Override
	public int compareTo(BoundTransition o) {
		if (o == null) {
			return 0;
		}
		return this.toString().compareTo(o.toString());
	}
	
	
}
