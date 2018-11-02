package org.architecturemining.ismodeler.tests.specification;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.architecturemining.ismodeler.specification.SpecificationBuilder;
import org.architecturemining.ismodeler.specification.parsing.SpecificationLexer;
import org.architecturemining.ismodeler.specification.parsing.SpecificationParser;
import org.architecturemining.ismodeler.specification.parsing.SpecificationParser.Process_contentContext;
import org.architecturemining.ismodeler.specification.parsing.SpecificationParser.Specication_fileContext;

public class ParserTest {

	public static void main(String[] args) {
		try {
			CharStream stream = CharStreams.fromFileName("/home/jmw/git/DPS/example/students2.2.spec");
			// CharStream stream = CharStreams.fromString("process Test { }");
			
			SpecificationLexer lexer = new SpecificationLexer(stream);
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
	        SpecificationParser parser = new SpecificationParser(tokenStream);
	        Specication_fileContext spec = parser.specication_file();
	        
	        SpecificationBuilder builder = new SpecificationBuilder();
	        
	        builder.visit(spec);
	        
	        System.out.println(builder.getSpecification().toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
