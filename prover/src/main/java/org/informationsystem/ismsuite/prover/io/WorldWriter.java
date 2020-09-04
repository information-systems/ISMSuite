package org.informationsystem.ismsuite.prover.io;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map.Entry;

import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;

public class WorldWriter {

	public static void write(FirstOrderLogicWorld world, OutputStream output) {
		PrintWriter out = new PrintWriter(output);
		
		// Write all elements with their types
		out.println("%%%%%");
		out.println("% Element types");
		out.println("%%%%%");
		out.println();
		for (String typ: world.getElementTypes()) {
			Iterator<Element> itElem = world.getElementsIn(typ);
			while(itElem.hasNext()) {
				Element next = itElem.next();
				out.print("tff( type_of_" + next.getLabel());
				out.print(", type, ");
				out.print(next.getLabel() + ": " + next.getType());
				out.println(").");
				out.println();
			}
		}

		// Write all relations as axioms
		out.println();
		out.println("%%%%%");
		out.println("% Relations");
		out.println("%%%%%");
		out.println();
		
		Iterator<Relation> itRel = world.getRelations();
		while(itRel.hasNext()) {
			Relation next = itRel.next();
			out.print("tff( rel_" + next.getId() );
			out.println(", axiom, ");
			out.print("\t");
			out.println(next.toTFF());
			out.println(").");
			out.println();
		}
		
		// Write all conjectures
		out.println();
		out.println("%%%%%");
		out.println("% Conjectures");
		out.println("%%%%%");
		out.println();
		
		for(Entry<String, Clause> con: world.getConjectures()) {
			out.print("tff( ");
			out.print(con.getKey());
			out.println(", conjecture, ");
			out.print("\t");
			out.println(con.getValue().toTFF());
			out.println(").");
			out.println();
		}
		
		out.flush();
	}
}
