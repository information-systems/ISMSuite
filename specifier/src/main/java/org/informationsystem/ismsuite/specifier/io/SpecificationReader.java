package org.informationsystem.ismsuite.specifier.io;

import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.parser.SpecificationLexer;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser.SpecificationContext;

public class SpecificationReader { 
	
	
	public static Specification fromFileName(String filename) throws IOException {
		return fromCharStream(CharStreams.fromFileName(filename));
	}
	
	public static Specification fromString(String s) {
		return fromCharStream(CharStreams.fromString(s));
	}
	
	public static Specification fromStream(InputStream in) throws IOException {
		return fromCharStream(CharStreams.fromStream(in));
	}
	
	public static Specification fromCharStream(CharStream stream) {
		SpecificationLexer lexer = new SpecificationLexer(stream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SpecificationParser parser = new SpecificationParser(tokenStream);
        SpecificationContext spec = parser.specification();
        
        SpecificationBuilder builder = new SpecificationBuilder();
        
        return builder.getSpecification(spec);
	}

}