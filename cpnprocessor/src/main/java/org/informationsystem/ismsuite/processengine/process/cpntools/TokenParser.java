package org.informationsystem.ismsuite.processengine.process.cpntools;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.informationsystem.ismsuite.processengine.process.cpntools.tokenparsing.CPNTokenBaseVisitor;
import org.informationsystem.ismsuite.processengine.process.cpntools.tokenparsing.CPNTokenLexer;
import org.informationsystem.ismsuite.processengine.process.cpntools.tokenparsing.CPNTokenParser;
import org.informationsystem.ismsuite.processengine.process.cpntools.tokenparsing.CPNTokenParser.IdContext;
import org.informationsystem.ismsuite.processengine.process.cpntools.tokenparsing.CPNTokenParser.Token_listContext;

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
			int count = Integer.parseInt(ctx.count().getText());
			Token token = (Token) visitToken_value(ctx.token_value());
			
			list.add(token, count);
			
			return list; 
		}
		
		@Override 
		public Object visitToken_value(CPNTokenParser.Token_valueContext ctx) {	
			List<String> entities = new ArrayList<>();
			
			if (ctx.id() != null) {
				entities.add(ctx.id().getText());
			} else if (ctx.id_list() != null) {
				for(IdContext id: ctx.id_list().id()) {
					entities.add(id.getText());
				}
			}
			
			return new Token(entities); 
		}
		
		
	}
}
