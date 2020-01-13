package org.informationsystem.ismsuite.specifier.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.informationsystem.ismsuite.prover.model.Literal;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.parser.SpecificationBaseVisitor;
import org.informationsystem.ismsuite.specifier.parser.SpecificationLexer;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser.Specication_fileContext;
import org.informationsystem.ismsuite.specifier.parser.SpecificationParser.Variable_declarationContext;
import org.informationsystem.ismsuite.specifier.model.DeregisterOperation;
import org.informationsystem.ismsuite.specifier.model.InsertOperation;
import org.informationsystem.ismsuite.specifier.model.Operation;
import org.informationsystem.ismsuite.specifier.model.RegisterOperation;
import org.informationsystem.ismsuite.specifier.model.RemoveOperation;
import org.informationsystem.ismsuite.specifier.model.Transaction;

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
        Specication_fileContext spec = parser.specication_file();
        
        SpecificationBuilder builder = new SpecificationBuilder();
        
        builder.visit(spec);
        
        return builder.getSpecification();
	}

}