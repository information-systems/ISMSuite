package org.architecturemining.ismodeler.tests.specification;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.architecturemining.ismodeler.specification.SpecificationReader;
import org.architecturemining.ismodeler.specification.parsing.SpecificationLexer;
import org.architecturemining.ismodeler.specification.parsing.SpecificationParser;
import org.architecturemining.ismodeler.specification.parsing.SpecificationParser.Process_contentContext;
import org.architecturemining.ismodeler.specification.parsing.SpecificationParser.Specication_fileContext;

public class ParserTest {

	public static void main(String[] args) {
		try {
	        
	        String output = SpecificationReader.fromFileName("/home/jmw/git/DPS/example/students2.2.spec").toString();
	        System.out.println(output); 
	        //System.out.println(SpecificationReader.fromString(output).toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
