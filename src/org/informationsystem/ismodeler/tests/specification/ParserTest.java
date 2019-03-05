package org.informationsystem.ismodeler.tests.specification;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.informationsystem.ismodeler.specification.SpecificationReader;
import org.informationsystem.ismodeler.specification.parsing.SpecificationLexer;
import org.informationsystem.ismodeler.specification.parsing.SpecificationParser;
import org.informationsystem.ismodeler.specification.parsing.SpecificationParser.Process_contentContext;
import org.informationsystem.ismodeler.specification.parsing.SpecificationParser.Specication_fileContext;

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
