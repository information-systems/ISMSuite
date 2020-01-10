package org.informationsystem.ismsuite.modeler.process.util;

import java.util.StringTokenizer;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag;

public class TokenParser {

	public TokenBag parse(String input) {
		TokenBag bag = PnidsFactory.eINSTANCE.createTokenBag();
		
		String toParse = input.replaceAll("\\s+","");
	    StringTokenizer tokenizer = new StringTokenizer(toParse, "++");
	    while(tokenizer.hasMoreElements()) {
	    	bag.getToken().add(parseToken(tokenizer.nextToken()));
	    }
	    
	    return bag;
	}
	
	public Token parseToken(String input) {
		String toParse = input.replaceAll("\\s+","");
		int start = 0;
		int end = toParse.length();
		if (toParse.charAt(0) == '(') {
			start = 1;
		}
		if (toParse.charAt(toParse.length()-1) == ')') {
			end--;
		}
	    StringTokenizer tokenizer = new StringTokenizer(toParse.substring(start,end), ",");
	    Token token = PnidsFactory.eINSTANCE.createToken();
	    while(tokenizer.hasMoreElements()) {
	    	Entity e = PnidsFactory.eINSTANCE.createEntity();
	    	e.setText(tokenizer.nextToken());
	    	token.getEntity().add(e);
	    }
	    
	    return token;
	}
	
	
	private static TokenParser instance;
	
	public static TokenParser getInstance() {
		if (instance == null) {
			instance = new TokenParser();
		}
		
		return instance;
	}
}