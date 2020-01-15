package org.informationsystem.ismsuite.modeler.process.util;

import java.util.StringTokenizer;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag;
import org.informationsystem.ismsuite.modeler.process.util.tokenparser.TokenMultiSetLexer;
import org.informationsystem.ismsuite.modeler.process.util.tokenparser.TokenMultiSetParser;
import org.informationsystem.ismsuite.modeler.process.util.tokenparser.TokenMultiSetParser.MultisetContext;

public class TokenParser {

	public static TokenBag parse(String input) {
		CharStream stream = CharStreams.fromString(input);
		SyntaxErrorListener listener = new SyntaxErrorListener();
		
		TokenMultiSetLexer lexer = new TokenMultiSetLexer(stream);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);
		
		TokenMultiSetParser parser = new TokenMultiSetParser(new CommonTokenStream(lexer));
		parser.setErrorHandler(new BailErrorStrategy());
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		
		try {
			MultisetContext multiset = parser.multiset();
			ConstructTokenBagVisitor constructor = new ConstructTokenBagVisitor();
			
			if (listener.succeeded() && parser.isMatchedEOF()) {
				return constructor.visit(multiset);
			}
		} catch (Exception  e) {}

		return null;
	}
}