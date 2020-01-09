package org.informationsystem.ismsuite.pnidprocessor.petrinet;

public class Token {
	private String[] entities;
	private String token;

	public String[] getEntities() {
		return entities;
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
