package org.informationsystem.ismsuite.modeler.process.util;

import java.util.BitSet;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public class SyntaxErrorListener extends BaseErrorListener {

	private boolean successfull = true;
	private String errorMessage = "";
	
	public boolean succeeded() {
		return successfull;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		successfull = false;
		errorMessage = msg;
	}
	
	@Override
	public void reportAttemptingFullContext(Parser recognizer,
			DFA dfa,
			int startIndex,
			int stopIndex,
			BitSet conflictingAlts,
			ATNConfigSet configs)
	{
		System.out.println("hier");
	}
	
	@Override
	public void reportContextSensitivity(Parser recognizer,
										 DFA dfa,
										 int startIndex,
										 int stopIndex,
										 int prediction,
										 ATNConfigSet configs)
	{
		System.out.println("contetxt");
	}
}
