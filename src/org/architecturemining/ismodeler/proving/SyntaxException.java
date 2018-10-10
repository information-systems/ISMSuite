package org.architecturemining.ismodeler.proving;

public class SyntaxException extends Exception {

	private static final long serialVersionUID = 7403808239597318471L;
	
	private String text;
	
	public String getProofText() {
		return text;
	}
	
	public SyntaxException(String text, String message) {
		// If the line contains a colon, then the first signifies the 
		// file, in which we are not interested.
		super( message );
		this.text = text;
	}
}
