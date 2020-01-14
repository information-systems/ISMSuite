package org.informationsystem.ismsuite.modeler.process.util;

import java.util.BitSet;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence;
import org.informationsystem.ismsuite.modeler.process.util.variableparser.VariableDescriptionLexer;
import org.informationsystem.ismsuite.modeler.process.util.variableparser.VariableDescriptionParser;
import org.informationsystem.ismsuite.modeler.process.util.variableparser.VariableDescriptionParser.SequenceContext;

public class SequenceParser {


	public static VariableSequence parse(String input) {
		/*
		if (input.isBlank()) {
			VariableSequence sequence = PnidsFactory.eINSTANCE.createVariableSequence();
			sequence.setMultiplicity(1);
			return sequence;
		}
		*/
		CharStream stream = CharStreams.fromString(input);
		VariableDescriptionLexer lexer = new VariableDescriptionLexer(stream);
		SyntaxErrorListener listener = new SyntaxErrorListener();
		
		lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
		lexer.addErrorListener(listener);
		VariableDescriptionParser parser = new VariableDescriptionParser(new CommonTokenStream(lexer));
		
		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		
		parser.addErrorListener(listener);
		
		SequenceContext sequence = parser.sequence();
		ConstructSequenceLabel constructor = new ConstructSequenceLabel();
		
        if (listener.succeeded()) {
        	return constructor.visit(sequence);
        } /* else {
        	System.out.println(listener.getErrorMessage());
        }*/
		
        return null;
	}
	
}