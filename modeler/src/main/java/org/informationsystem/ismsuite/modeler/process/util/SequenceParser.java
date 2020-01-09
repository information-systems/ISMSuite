package org.informationsystem.ismsuite.modeler.process.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SequenceParser {

	public List<String> parse(String input) {
		List<String> result = new ArrayList<>();
		
		String toParse = input.replaceAll("\\s+","");
	    StringTokenizer tokenizer = new StringTokenizer(toParse, ",");
	    
	    while (tokenizer.hasMoreElements()) {
	    	result.add(tokenizer.nextToken());
	    }
	    
		return result;
	}
	
	
	// Singleton implementation
	private static SequenceParser instance = null;
	
	public static SequenceParser getInstance() {
		if (instance == null) {
			instance = new SequenceParser();
		}
		return instance;
	}
	
}