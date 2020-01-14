package org.informationsystem.ismsuite.modeler.process.util;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

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
	
}
