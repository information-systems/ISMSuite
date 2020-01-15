package org.informationsystem.ismsuite.modeler.process.util;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeSequence;
import org.informationsystem.ismsuite.modeler.process.util.entitytypeparser.EntityTypesLexer;
import org.informationsystem.ismsuite.modeler.process.util.entitytypeparser.EntityTypesParser;
import org.informationsystem.ismsuite.modeler.process.util.entitytypeparser.EntityTypesParser.Entity_vectorContext;

public class EntityTypeParser {

	public static EntityTypeSequence parse(String input) {
		CharStream stream = CharStreams.fromString(input);
		SyntaxErrorListener listener = new SyntaxErrorListener();
		EntityTypesLexer lexer = new EntityTypesLexer(stream);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);
		
		EntityTypesParser parser = new EntityTypesParser(new CommonTokenStream(lexer));
		parser.removeErrorListeners();
		parser.setErrorHandler(new BailErrorStrategy());
		parser.addErrorListener(listener);
		
		try {
			Entity_vectorContext sequence = parser.entity_vector();
			
			if (listener.succeeded() && parser.isMatchedEOF()) {
				ConstructEntityTypeVisitor constructor = new ConstructEntityTypeVisitor();
	        	return constructor.visit(sequence);
	        } else {
	        	System.out.println(listener.getErrorMessage());
	        } // */
			
		} catch(Exception e) {
			return null;
		}
			
		
		return null;
		
	}
}
