package org.architecturemining.ismodeler.proving;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Prover {
	
	private Prover() {
		
	}
	
	public boolean proof(String text) throws SyntaxException, GeneralProverException {
		return proof(text, "");
	}
	
	/**
	 * Returns true if a proof has been found. If no proof is found, return 0.
	 * It creates a temporary file that contains both text and constraints.
	 * @param text
	 * @param constraint
	 * @return true if a proof has been found
	 * @throws SyntaxException 
	 * @throws GeneralProverException 
	 */
	public boolean proof(String text, String constraint) throws SyntaxException, GeneralProverException {
		File dir = new File(System.getProperty("user.dir") + "\\tmp-prover");
		dir.mkdirs();
		try {
			
			// first write the file to disk
			
			File proofFile = File.createTempFile("proverfile", ".p", dir);
			
			PrintStream out = new PrintStream(new FileOutputStream(proofFile));
			out.println(text);
			out.println(constraint);
			
			out.close();
			
			// then pass it on to the prover
			String command = ".\\E\\PROVER\\eprover --silent --auto " + proofFile.getAbsolutePath();
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec(command);
			
			
			
			int code = pr.waitFor();
			
			// TODO Uncomment line below
			// proofFile.delete();
			
			// If the code is 1, then no proof has been found.
			switch(code) {
			case 0:
				// A proof is found!
				return true;
			case 1: 
				// No proof could be found!
				return false;
			case 3:
				BufferedReader output = new BufferedReader(new 
					     InputStreamReader(pr.getErrorStream()));
				String s = null;
				StringBuilder error = new StringBuilder();
				while ((s = output.readLine()) != null) {
				    error.append(s);
				}
				throw new SyntaxException(text + "\n" + constraint, error.toString());
			default:
				throw new GeneralProverException(code); 	
			}
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/*
	 * Singleton implementation
	 */
	private static Prover instance;
	
	public static Prover getInstance() {
		if (instance == null) {
			instance = new Prover();
		}
		
		return instance;
	}
	
}
