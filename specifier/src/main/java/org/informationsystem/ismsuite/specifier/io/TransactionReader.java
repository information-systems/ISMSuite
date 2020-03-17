package org.informationsystem.ismsuite.specifier.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.model.Transaction;
import org.informationsystem.ismsuite.specifier.parser.SpecificationLexer;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser.SpecificationContext;

public class TransactionReader {
	
	private Set<ANTLRErrorListener> listeners = new HashSet<>();
	
	public void addErrorListener(ANTLRErrorListener l) {
		listeners.add(l);
	}
	
	public void removeErrorListener(ANTLRErrorListener l) {
		listeners.remove(l);
	}
	
	private SpecificationLexer getLexer(CharStream stream) {
		SpecificationLexer lexer = new SpecificationLexer(stream);
		
		for(ANTLRErrorListener listener: listeners) {
			lexer.addErrorListener(listener);
		}
		lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
		
		return lexer;
	}
	
	private SpecificationParser getParser(SpecificationLexer lexer) {
		SpecificationParser parser = new SpecificationParser(new CommonTokenStream(lexer));
		
		for(ANTLRErrorListener listener: listeners) {
			parser.addErrorListener(listener);
		}
		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		
		return parser;
	}
	
	public Transaction parse(String text) {
		return parse(CharStreams.fromString(text));
	}
	
	public Transaction parse(CharStream stream) {
		SpecificationLexer lexer = getLexer(stream);
		SpecificationParser parser = getParser(lexer);
		
        SpecificationContext spec = parser.specification();
        SpecificationBuilder builder = new SpecificationBuilder();
        
        Specification specification = builder.getSpecification(spec);
        if (!specification.isEmpty()) {
        	for (Transaction t: specification.values()) {
        		return t;
        	}
        }
        
        return null;
	}
	
	
	public static Transaction fromFile(String filename) throws IOException {
		return fromCharStream(CharStreams.fromFileName(filename));
	}
	
	public static Transaction fromString(String s) {
		return fromCharStream(CharStreams.fromString(s));
	}
	
	public static Transaction fromStream(InputStream in) throws IOException {
		return fromCharStream(CharStreams.fromStream(in));
	}
	
	public static Transaction fromCharStream(CharStream stream) {
		TransactionReader r = new TransactionReader();
		return r.parse(stream);
	}

}
