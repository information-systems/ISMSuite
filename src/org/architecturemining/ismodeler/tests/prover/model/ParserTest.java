package org.architecturemining.ismodeler.tests.prover.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map.Entry;

import org.antlr.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.architecturemining.ismodeler.proving.model.Clause;
import org.architecturemining.ismodeler.proving.model.ClauseReader;
import org.architecturemining.ismodeler.proving.model.parsing.TFFLexer;
import org.architecturemining.ismodeler.proving.model.parsing.TFFParser;
import org.architecturemining.ismodeler.proving.model.parsing.TFFParser.Tff_fileContext;

public class ParserTest {

	public static void main(String[] args) throws Exception {
        // create a CharStream that reads from standard input

		CodePointCharStream stream = CharStreams.fromString(
					  "tff(a_type, type, a: human)."
					+ "tff(b_type, type, b: human)."
					+ "tff(c_type, type, c: human)."
					+ "tff(d_type, type, d: human)."
					+ "tff( r_a_b, axiom, r(a,b))."
					+ "tff( r_b_c, axiom, r(b,c))."
					+ "tff( r_a_b_and_r_b_c, conjecture, r(a,b) & r(b,c) )."
					+ "tff( r_refl,conjecture, ! [X: human] : ( r(X,X) ) )."
				    + "tff( r_trans, conjecture, ! [X: human, Y: human, Z:human ] : ( ( ( r(X,Y) & r(Y,Z) )  => r(X,Z) ) ) )."
				);
		TFFLexer lexer = new TFFLexer(stream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        TFFParser parser = new TFFParser(tokenStream);
        Tff_fileContext tree = parser.tff_file();

        ClauseReader constr = new ClauseReader();
        constr.visit(tree);
        System.out.println(constr.getWorld().toString());
        
        for(Entry<String, Clause> e: constr.getConjectures().entrySet()) {
        	System.out.println(e.getKey());
        	System.out.println("    " + e.getValue().toString());
        }
    }
	
}
