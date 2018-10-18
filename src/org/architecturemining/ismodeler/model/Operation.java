package org.architecturemining.ismodeler.model;

public class Operation {

	private enum OperatorEnum { ADD, REMOVE };
	private OperatorEnum operator;
	
	private PredicateDefinition definition;
	
	public Operation(String operation, PredicateDefinition definition) {
		switch (operation.toLowerCase()) {
		case "add":
			this.operator = OperatorEnum.ADD;
			break;
		case "remove":
			this.operator = OperatorEnum.REMOVE;
		}
		this.definition = definition;
	}
	
	public Operation(OperatorEnum operation, PredicateDefinition definition) {
		this.operator = operation;
		this.definition = definition;
	}
	
	public OperatorEnum getOperator() {
		return this.operator;
	}
	
	public PredicateDefinition getDefinition() {
		return this.definition;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder(); 
		switch (this.operator) {
		case ADD:
			b.append("add");
			break;
		case REMOVE:
			b.append("remove");
			break;
		}
		b.append("( ");
		b.append(this.definition.toString());
		b.append(" ).");
		
		return b.toString();
	}
	
}
