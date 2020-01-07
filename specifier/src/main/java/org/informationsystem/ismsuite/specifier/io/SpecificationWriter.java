package org.informationsystem.ismsuite.specifier.io;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.model.Transaction;

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SpecificationWriter {

	public static void write(Specification spec, OutputStream output) {
		Map<String, List<Transaction>> processPlaces = split(spec.places());
		Map<String, List<Transaction>> processTransitions = split(spec.transitions());
		
		Set<String> processes = new HashSet<>(processPlaces.keySet());
		processes.addAll(processTransitions.keySet());
		
		PrintWriter out = new PrintWriter(output);
		
		for(String process: processes) {
			boolean b = !process.isEmpty();
			String append = "";
			if (b) {
				out.println("process " + process + " {");
				append = "\t";
			}
			if (processPlaces.containsKey(process)) {
				for(Transaction t: processPlaces.get(process)) {
					out.print("\n");
					out.print(append);
					out.print("place ");
					out.print(t.toString(false, append, b));
					out.print("\n");
				}
			}
			if (processTransitions.containsKey(process)) {
				for(Transaction t: processTransitions.get(process)) {
					out.print("\n");
					out.print(append);
					out.print("transition ");
					out.print(t.toString(false, append, b));
				}
			}
			if (b) out.println("}\n\n");
		}
		
		out.flush();
	}
	
	private static Map<String, List<Transaction>> split(Collection<Transaction> col) {
		HashMap<String, List<Transaction>> map = new HashMap<>();
		
		for(Transaction t: col) {
			int p = t.getLabel().indexOf(".");
			String process;
			if (p > 0) {
				process = t.getLabel().substring(0, p);
			} else {
				process = "";
			}
			if (!map.containsKey(process)) {
				map.put(process, new ArrayList<Transaction>());
			}
			map.get(process).add(t);
		}
		
		return map;
	}
	
}
