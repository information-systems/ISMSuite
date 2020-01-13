package org.informationsystem.ismsuite.prover.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.prover.parser.tff.TFFLexer;
import org.informationsystem.ismsuite.prover.parser.tff.TFFParser;
import org.informationsystem.ismsuite.prover.parser.tff.TFFParser.Tff_fileContext;
import org.informationsystem.ismsuite.prover.parser.tff.TFFParser.Tff_formulaContext;

/**
 * Reads and parses Strings in TFF-format to Clauses and Worlds.
 * 
 * @author jmw
 *
 */
public class ClauseReader {

	public static FirstOrderLogicWorld buildWorldFrom(String tff) {
		Tff_fileContext tree = parseStream(CharStreams.fromString(tff));
		
		WorldBuilder b = new WorldBuilder();
		b.visit(tree);
		
		return b.getWorld();
	}
	
	public static FirstOrderLogicWorld buildWorldFromFile(String filename) throws IOException {
		Tff_fileContext tree = parseStream(CharStreams.fromFileName(filename));
		
		WorldBuilder b = new WorldBuilder();
		b.visit(tree);
		
		return b.getWorld();
	}
	
	public static World buildWorldFrom(InputStream in) throws IOException {
		Tff_fileContext tree = parseStream(CharStreams.fromStream(in));
		
		WorldBuilder b = new WorldBuilder();
		b.visit(tree);
	
		return b.getWorld();
	}
	
	
	public static Map<String,Clause> buildClausesFrom(String tff) {
		Tff_fileContext tree = parseStream(CharStreams.fromString(tff));
		
		WorldBuilder b = new WorldBuilder();
		b.visit(tree);
		
		return b.getClauses();
	}
	
	public static Map<String,Clause> buildClausesFromFile(String filename) throws IOException {
		Tff_fileContext tree = parseStream(CharStreams.fromFileName(filename));
		
		WorldBuilder b = new WorldBuilder();
		b.visit(tree);
		
		return b.getClauses();
	}
	
	public static Map<String,Clause> buildClausesFrom(InputStream in) throws IOException {
		Tff_fileContext tree = parseStream(CharStreams.fromStream(in));
		
		WorldBuilder b = new WorldBuilder();
		b.visit(tree);
		
		return b.getClauses();
	}
	
	public static Clause parseClause(String tff) {
		TFFLexer lexer = new TFFLexer(CharStreams.fromString(tff));
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        TFFParser parser = new TFFParser(tokenStream);
        Tff_formulaContext tree = parser.tff_formula();
        
        WorldBuilder b = new WorldBuilder();
        
        return b.visit(tree);
	}
	
	private static Tff_fileContext parseStream(CharStream stream) {
		
		TFFLexer lexer = new TFFLexer(stream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        TFFParser parser = new TFFParser(tokenStream);
        Tff_fileContext tree = parser.tff_file();
        
        return tree;
	}

	private static class WorldBuilder extends TFFClauseVisitor {

		public WorldBuilder() {
			super(new World());
		}

		private World world;
		private Map<String, Clause> clauses;
		
		@Override
		public World getWorld() {
			return world;
		}

		public Map<String,Clause> getClauses() {
			return clauses;
		}

		@Override
		public Clause visit(ParseTree tree) {
			world = new World();
			clauses = new HashMap<>();
			
			return super.visit(tree);
		}

		@Override
		public Clause visitTff_line(TFFParser.Tff_lineContext txc) {
			Clause clause = visitTff_formula(txc.tff_formula());

			if (clause == null) {
				return null;
			}

			String role = txc.formula_role().getText().toLowerCase();

			if (role.equals("axiom")) {
				if (clause instanceof Relation) {
					world.addRelation((Relation) clause);
				}
			} else if (role.equals("type")) {
				if (clause instanceof Element) {
					world.addElement((Element) clause);
				}
			} else if (role.equals("conjecture")) {
				world.addConjecture(txc.name().getText(), clause);
			}
			
			clauses.put(txc.name().getText(), clause);

			return clause;
		}

		
	}
}