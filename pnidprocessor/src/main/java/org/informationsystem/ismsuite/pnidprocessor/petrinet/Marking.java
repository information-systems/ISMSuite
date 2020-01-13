package org.informationsystem.ismsuite.pnidprocessor.petrinet;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.Token;

public class Marking {

	private Map<String, MultiSet<Token>> tokenBag = new HashMap<>();

	private int counter;

	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * Any fresh identity should be larget than this value
	 * @return the latest counter used.
	 */
	public int getCounter() {
		return counter;
	}

	public Map<String, MultiSet<Token>> map() {
		return Collections.unmodifiableMap(tokenBag);
	}

	
	public Set<Entry<String, MultiSet<Token>>> tokens() {
		return tokenBag.entrySet();
	}

	public void increaseCounter(int size) {
		counter += size;
	}

	public MultiSet<Token> getTokens(String place) {
		if (tokenBag.containsKey(place)) {
			return tokenBag.get(place);
		} else {
			return new MultiSet<>();
		}
	}

	public Set<String> getIdentities(String place, int index) {
		if (index < 0 || tokenBag.containsKey(place)) {
			HashSet<String> result = new HashSet<>();
			
			for(Token t: tokenBag.get(place)) {
				if (t.size() < index ) {
					return Collections.emptySet();
				}
				result.add(t.get(index));
			}
			
			return result;
		} else {
			return Collections.emptySet();
		}
	}

	public boolean remove(String place, String... identities) {
		return remove(place, new Token(identities));
	}

	public boolean remove(String place, Token token) {
		if (tokenBag.containsKey(place)) {
			tokenBag.get(place).remove(token);
			if (tokenBag.get(place).isEmpty()) {
				tokenBag.remove(place);
			}
			return true;
		} else {
			return false;
		}
	}

	public void add(String place, String... identities) {
		add(place, new Token(identities));
	}

	public void add(String place, Token token) {
		if (!tokenBag.containsKey(place)) {
			tokenBag.put(place, new MultiSet<>());
		}
		tokenBag.get(place).add(token);
	}

	@Override
	public String toString() {
		return toString(false);
	}

	public String toString(boolean full) {
		StringBuilder sb = new StringBuilder();
		sb.append("MARKING:\n");
		sb.append("\tcounter: ");
		sb.append(counter);
		sb.append("\n");
		for(Map.Entry<String, MultiSet<Token>> e: tokenBag.entrySet()) {
			sb.append("\t");
			sb.append(e.getKey());
			sb.append(": [");
			Iterator<Token> it = e.getValue().iterator();
			while(it.hasNext()) {
				Token token = it.next();
				sb.append("  (");
				if (token.getEntities().length > 0) {
					sb.append(token.getEntities()[0]);
				}
				for(int i = 1 ; i < token.getEntities().length ; i++) {
					sb.append(", ");
					sb.append(token.getEntities()[i]);
				}
				sb.append("): ");
				sb.append(e.getValue().size(token));
			}
			sb.append("]");
			sb.append("\n");
		}


		return sb.toString();
	}
}
