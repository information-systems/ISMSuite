package org.informationsystem.ismsuite.pnidprocessor.petrinet;


import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import org.informationsystem.ismsuite.processengine.process.MultiSet;


public class IdentitiesBag {

	private Map<Integer, MultiSet<String>> identities = new HashMap<>();

	public void add(int index, String value) {
		Integer i = Integer.valueOf(index);
		if (!identities.containsKey(i)) {
			identities.put(i, new MultiSet<>());
		}
		identities.get(i).add(value);
	}

	public void remove(int index, String value) {
		Integer i = Integer.valueOf(index);
		if (identities.containsKey(i)) {
			identities.get(i).remove(value);
		}
	}

	public Set<String> get(int index) {
		Integer i = Integer.valueOf(index);
		if (identities.containsKey(i)) {
			return identities.get(i).getUnique();
		} else {
			return null;
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for(Map.Entry<Integer, MultiSet<String>> e: identities.entrySet()) {
			sb.append(" ");
			sb.append(e.getKey().toString());
			sb.append(": ");
			for(String s: e.getValue().getUnique()) {
				sb.append(s);
				sb.append(" (");
				sb.append(e.getValue().size(s));
				sb.append(") ");
			}
		}

		return sb.toString();
	}
}
