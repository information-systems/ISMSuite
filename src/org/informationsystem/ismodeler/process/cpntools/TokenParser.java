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

	
	public MultiSet<Token> parse(String str) {
		CPNTokenLexer lexer = new CPNTokenLexer(CharStreams.fromString(str));
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CPNTokenParser parser = new CPNTokenParser(tokenStream);
        // Specication_fileContext spec = parser.specication_file();
        Token_listContext token = parser.token_list();
        
        TokenVisitor builder = new TokenVisitor();
        builder.visit(token);
		
		return builder.getTokenList();
	}
	
	private static class TokenVisitor extends CPNTokenBaseVisitor<Object> {
		
		MultiSet<Token> list;
		
		public MultiSet<Token> getTokenList() {
			return list;
		}
		
		@Override
		public Object visitToken_list(CPNTokenParser.Token_listContext ctx) {
			list = new MultiSet<>();
			
			visitChildren(ctx);
			return null;
		}
		
		@Override
		public Object visitToken(CPNTokenParser.TokenContext ctx) {
			// Number of tokens in : ctx.count()
			// Value in: ctx.token_value();
			int count = Integer.parseInt(ctx.count().getText());
			Token token = parseToken(ctx.token_value());
			
			list.add(token, count);
			
			return null;
		}
		
		private Token parseToken(Token_valueContext ctx) {
			if (ctx.id() != null) { 
				// it is a single value!
				Token t = new Token(1);
				t.set(0, Long.parseLong(ctx.id().getText()));
				return t;
			} else if (ctx.id_list() != null) {
				// it is a list!
				// ctx.id_list().
			}
			return null;
		}
		
	}
}
