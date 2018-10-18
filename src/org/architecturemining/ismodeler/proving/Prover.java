package org.architecturemining.ismodeler.proving;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Properties;

public class Prover {
	
	private String proofcommand;
	
	private Prover() {
		try {
			Properties prop = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
			 
			if (in != null) {
				prop.load(in);
				if (prop.containsKey("prover")) {
					proofcommand = prop.getProperty("prover");
				}
			}
		} catch(Exception e) {
		}

		if (proofcommand == null || proofcommand.length() == 0) {
			proofcommand = "./tool/E/PROVER/eprover";
		}
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
		File dir = new File(System.getProperty("user.dir") + File.separator + "tmp-prover");
		dir.mkdirs();
		try {
			
			// first write the file to disk
			
			File proofFile = File.createTempFile("proverfile", ".p", dir);
			
			PrintStream out = new PrintStream(new FileOutputStream(proofFile));
			out.println(text);
			out.println(constraint);
			
			out.close();
			
			// then pass it on to the prover
			//String command = "./tool/E/PROVER/eprover --silent --auto " + proofFile.getAbsolutePath();
			
			String command = proofcommand + " " + proofFile.getAbsolutePath();
			System.out.println(command);
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec(command);
			
			BufferedReader outr = new BufferedReader(new 
				     InputStreamReader(pr.getInputStream()));
			String s = null;
			while ((s = outr.readLine()) != null) {
			    System.out.println(s);
			}
			
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
				String st = null;
				StringBuilder error = new StringBuilder();
				while ((st = output.readLine()) != null) {
				    error.append(st);
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
