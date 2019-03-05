package test.org.informationsystem.proving.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map.Entry;

import org.antlr.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.informationsystem.proving.model.Clause;
import org.informationsystem.proving.model.ClauseReader;
import org.informationsystem.proving.model.World;
import org.informationsystem.proving.model.parsing.TFFLexer;
import org.informationsystem.proving.model.parsing.TFFParser;
import org.informationsystem.proving.model.parsing.TFFParser.Tff_fileContext;

public class ParserTest {

	public static void main(String[] args) throws Exception {
        // create a CharStream that reads from standard input

		World world = ClauseReader.buildWorldFrom(
					  "tff(a_type, type, a: human)."
					+ "tff(b_type, type, b: human)."
					+ "tff(c_type, type, c: human)."
					+ "tff(d_type, type, d: human)."
					+ "tff( r_a_b, axiom, r(a,b))."
					+ "tff( r_b_c, axiom, r(b,c))."
					+ "tff( r_a_b_and_r_b_c, conjecture, r(X,b) & r(b,c) )."
					+ "tff( r_a_b_and_r_b_c_and_r_a_d, conjecture, r(X,b) & r(b,c) & r(a,d) )."
					+ "tff( r_a_b_or_r_b_c, conjecture, r(X,b) | r(b,c) )."
					+ "tff( r_a_b_or_r_b_c_or_r_a_d, conjecture, r(X,b) | r(b,c) | r(a,d) )."
					+ "tff( r_a_b_and_r_b_c_or_r_a_d, conjecture, (r(X,b) & r(b,c)) | r(a,d) )."
					+ "tff( r_refl, conjecture, ! [X: human] : ( r(X,X) ) )."
				    + "tff( r_trans, conjecture, ! [X: human, Y: human, Z: human ] : ( ( ( r(X,Y) & r(Y,Z) )  => r(X,Z) ) ) )."
				    + "tff( r_symm, conjecture,  ! [X: human, Y: human, Z: human ] : ( X!=Y ) )."
				);
		
        System.out.println(world.toString());
    }
	
}
