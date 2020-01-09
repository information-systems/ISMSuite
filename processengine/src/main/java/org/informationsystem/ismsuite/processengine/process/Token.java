package org.informationsystem.ismsuite.processengine.process;

import java.util.List;

public class Token {
	private String[] entities;
	private String token;
	
	public String[] getEntities() {
		return entities;
	}
	
	public Token(List<String> entities) {
		this(entities.toArray(new String[0]));
	}
	
	public int size() {
		return entities.length;
	}
	
	public String get(int i) {
		if (i >= 0 && i < size()) {
			return entities[i];
		} else {
			return "";
		}
	}
	
	public Token(String...entities) {
		StringBuilder sb = new StringBuilder();
		sb.append("token");
		for(int i = 0 ; i < entities.length ; i++) {
			sb.append("+++");
			sb.append(entities[i]);
		}
		this.token = sb.toString();
		this.entities = entities;
	}
	
	@Override
    public int hashCode() {
		return token.hashCode();
	}
	
	@Override
	public String toString() {
		return token;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o == null) return false;
		
		if (o instanceof Token) {
			return ((Token) o).toString().equals(toString());
		} else {
			return false;
		}
	}
}
