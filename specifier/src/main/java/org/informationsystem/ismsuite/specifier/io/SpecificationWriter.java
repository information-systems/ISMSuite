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
			
		PrintWriter out = new PrintWriter(output);
		
		for(Transaction t: spec.values()) {
			out.println(t.toString());
			out.println();
		}
		
		out.flush();
	}
}
