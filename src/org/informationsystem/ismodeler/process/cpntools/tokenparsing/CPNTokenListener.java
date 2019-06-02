// Generated from CPNToken.g4 by ANTLR 4.7.1
package org.informationsystem.ismodeler.process.cpntools.tokenparsing;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CPNTokenParser}.
 */
public interface CPNTokenListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CPNTokenParser#token_list}.
	 * @param ctx the parse tree
	 */
	void enterToken_list(CPNTokenParser.Token_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPNTokenParser#token_list}.
	 * @param ctx the parse tree
	 */
	void exitToken_list(CPNTokenParser.Token_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPNTokenParser#token}.
	 * @param ctx the parse tree
	 */
	void enterToken(CPNTokenParser.TokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPNTokenParser#token}.
	 * @param ctx the parse tree
	 */
	void exitToken(CPNTokenParser.TokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPNTokenParser#count}.
	 * @param ctx the parse tree
	 */
	void enterCount(CPNTokenParser.CountContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPNTokenParser#count}.
	 * @param ctx the parse tree
	 */
	void exitCount(CPNTokenParser.CountContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPNTokenParser#token_value}.
	 * @param ctx the parse tree
	 */
	void enterToken_value(CPNTokenParser.Token_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPNTokenParser#token_value}.
	 * @param ctx the parse tree
	 */
	void exitToken_value(CPNTokenParser.Token_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPNTokenParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(CPNTokenParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPNTokenParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(CPNTokenParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPNTokenParser#id_list}.
	 * @param ctx the parse tree
	 */
	void enterId_list(CPNTokenParser.Id_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPNTokenParser#id_list}.
	 * @param ctx the parse tree
	 */
	void exitId_list(CPNTokenParser.Id_listContext ctx);
}