package org.architecturemining.ismodeler.proving;

public class GeneralProverException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5091771393887546485L;

	public GeneralProverException(int code) {
		super("Prover ended unexpectedly with code: " + code);
	}
}
