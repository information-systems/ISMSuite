package org.informationsystem.ismodeler.process.cpntools;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.informationsystem.ismodeler.process.MultiSet;
import org.informationsystem.ismodeler.process.Token;
import org.informationsystem.ismodeler.process.cpntools.tokenparsing.CPNTokenBaseVisitor;
import org.informationsystem.ismodeler.process.cpntools.tokenparsing.CPNTokenLexer;
import org.informationsystem.ismodeler.process.cpntools.tokenparsing.CPNTokenParser;
import org.informationsystem.ismodeler.process.cpntools.tokenparsing.CPNTokenParser.Token_listContext;
import org.informationsystem.ismodeler.process.cpntools.tokenparsing.CPNTokenParser.Token_valueContext;
import org.informationsystem.ismodeler.specification.parsing.SpecificationLexer;
import org.informationsystem.ismodeler.specification.parsing.SpecificationParser;
import org.informationsystem.ismodeler.specification.parsing.SpecificationParser.Specication_fileContext;

public class TokenParser {

	
	public static MultiSet<Token> parse(String str) {
		CPNTokenLexer lexer = new CPNTokenLexer(CharStreams.fromString(str));
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CPNTokenParser parser = new CPNTokenParser(tokenStream);
        // Specication_fileContext spec = parser.specication_file();
        Token_listContext token = parser.token_list();
        
		return (new TokenVisitor()).getTokenList(token);
	}
	
	private static class TokenVisitor extends CPNTokenBaseVisitor<Object> {
		
		MultiSet<Token> list;
		
		public MultiSet<Token> getTokenList(Token_listContext token) {
			list = new MultiSet<>();
			
			this.visit(token);
			
			return list;
		}
		
		@Override 
		public Object visitToken(CPNTokenParser.TokenContext ctx) {
			// A token consists of count and a token value
			System.out.println("I got a count of: " + ctx.count().getText());
			return visitChildren(ctx); 
		}
		
		
	}
}
