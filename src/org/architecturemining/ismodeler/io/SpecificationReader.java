package org.architecturemining.ismodeler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.architecturemining.ismodeller.model.Argument;
import org.architecturemining.ismodeller.model.Label;
import org.architecturemining.ismodeller.model.Operation;
import org.architecturemining.ismodeller.model.PredicateDefinition;
import org.architecturemining.ismodeller.model.Specification;

public class SpecificationReader {

	/**
	 * States in which the parser can be
	 */
	private static enum WorkingOn { TYPES, INITIALPOPULATION, LABELS, TRANSACTION }
	
	/***
	 * Parses a Specification file from a file specified by name.
	 * 
	 * @param filename
	 * @return specification parsed from the file
	 * @throws FileNotFoundException
	 */
	public static Specification readFrom(String filename) throws FileNotFoundException {
		return SpecificationReader.readFrom(new FileInputStream(filename)  );
	}
	
	/***
	 * Parses a specification file from a given File object
	 * 
	 * @param f
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Specification readFrom(File f) throws FileNotFoundException {
		return SpecificationReader.readFrom(new FileInputStream(f)  );
	}
	
	/***
	 * Parses a specification file from a given input stream
	 * @param filename
	 * @return
	 */
	public static Specification readFrom(InputStream filename) {
		
		Scanner scanner = new Scanner(filename);
		
		Specification spec = new Specification();
		
		WorkingOn section = WorkingOn.TYPES;
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			// remove everything starting from %
			if (line.indexOf("%") > 0) {
				line = line.substring(0, line.indexOf("%") -1).trim();
			}
			
			if (line.length() == 0) {
				continue;
			}
			
			String label = "";
			// If the line starts with a %, it is considered a comment
			if (line.length() >= 6 && line.substring(0, 6).equalsIgnoreCase("types:")) {
				section = WorkingOn.TYPES;
				// next lines are types we can start adding, until the next line is empty
			} else if (line.length() >= 18 && line.substring(0,18).equalsIgnoreCase("initialpopulation:")) {
				section = WorkingOn.INITIALPOPULATION;
			} else if (line.substring(0, 7).equalsIgnoreCase("Labels:")) {
				section = WorkingOn.LABELS;
			} else if (line.length() >= 11 && line.substring(0,11).equalsIgnoreCase("transition:")) {
				section = WorkingOn.TRANSACTION;
				label = line.substring(12).trim();
				// keep reading until empty line, or an EOF. 
				List<Operation> transaction = parseTransaction(scanner); 
				spec.addTransaction(label, transaction);
			} else {
				switch(section) {
				case TYPES:
					// are of the form
					// Type {p1, p2, p3, p4, ... }
					spec.addValueTypes(parseTypeLine(line));
					break;
				case INITIALPOPULATION:
					// should always be of the form:
					// placeInTheNet: predicate(p1, p2, ...)
					if (line.indexOf(":") > 0) {
						label = line.substring(0, line.indexOf(":")).trim();
						PredicateDefinition definition = parsePredicateDefinition(line.substring(line.indexOf(":")+1));
						spec.addPopulation(label, definition);
					}
					break;
				case LABELS:
					spec.addLabel(parsePredicateLabel(line));
					break;
				}
			}
		}
		
		return spec;
	}

	/***
	 * Parses line of the form TypeName { p1, p2, p3, ... }
	 * 
	 * @param line
	 * @return A map of ValueTypes, mapped on their key.
	 */
	private static Map<String, String> parseTypeLine(String line) {
		HashMap<String, String> values = new HashMap<String,String>();
		if (line.indexOf('{') > 0 && line.indexOf('{') < line.indexOf('}') ) {
			String name = line.substring(0, line.indexOf("{")).trim();
			String keys = line.substring(line.indexOf('{')+1, line.indexOf('}'));
			for(String key: keys.split(",")) {
				values.put(key.trim(), name);
			}
		}
		return values;
	}
	
	private static List<Operation> parseTransaction(Scanner scanner) {
		ArrayList<Operation> transaction = new ArrayList<Operation>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.length() == 0) {
				return transaction;
			}
			
			if (line.indexOf("%") > 0) {
				line = line.substring(0, line.indexOf("%"));
			}
			
			transaction.add(parseOperation(line));
		}
		
		return transaction;
	}
	
	/***
	 * Parses a string of the form: operator(predicate(p1, p2, ...)).
	 * 
	 *    add(PredicateOverOneVar(p)).
	 *    remove(PredicateOverTwoVars(p, t)).
	 *    
	 *    ^\s*(add|remove)\s*(\s*([^\)])\s*\)\s*\.\s*$
	 *    
	 * @param line
	 * @return
	 */
	private static Operation parseOperation(String str) {
		String operator = str.substring(0, str.indexOf('(')).trim();
		String internal = str.substring(str.indexOf('(')+1, str.lastIndexOf(')'));
		
		return new Operation(operator, parsePredicateDefinition(internal));
	}
	
	/***
	 * Parses a string of the form: predicate(p1, p2, ...)
	 * @param line
	 * @return PredicateDefinition
	 */
	private static PredicateDefinition parsePredicateDefinition(String str) {
		String predicate = str.substring(0, str.indexOf('(')).trim();
		String internal = str.substring(str.indexOf('(')+1, str.lastIndexOf(')'));
		
		ArrayList<String> a = new ArrayList<String>();
		for(String param: internal.split(",")) {
			a.add(param.trim());
		}
		return new PredicateDefinition(predicate, a);
	}
	
	/**
	 * Parses a string of the form:
	 *   acceptsOnStudent [P,T,DT,A] {p,t,d,p}.
	 * @param line
	 */
	private static Label parsePredicateLabel(String line) {
		int sqO = line.indexOf('[');
		int sqC = line.indexOf(']');
		int brO = line.indexOf('{');
		int brC = line.indexOf('}');
		
		if (sqO > 0 && sqO < sqC && brO > 0 && brO < brC ) {
			String pred = line.substring(0, sqO).trim();
			String[] vars = line.substring(sqO+1, sqC).split(",");
			String[] types = line.substring(brO+1, brC).split(",");
		
			if (vars.length != types.length) {
				return null;
			}
			
			Label l = new Label(pred);
			for(int i = 0 ; i < vars.length ; i++ ) {
				l.addArgument(vars[i], types[i]);
			}
			
			return l;
		}
		return null;
	}
	
}
