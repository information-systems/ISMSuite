// Generated from CPNToken.g4 by ANTLR 4.7.1
package org.informationsystem.ismodeler.process.cpntools.tokenparsing;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CPNTokenParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CPNTokenVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CPNTokenParser#token_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToken_list(CPNTokenParser.Token_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPNTokenParser#token}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToken(CPNTokenParser.TokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPNTokenParser#count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCount(CPNTokenParser.CountContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPNTokenParser#token_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToken_value(CPNTokenParser.Token_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPNTokenParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(CPNTokenParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPNTokenParser#id_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_list(CPNTokenParser.Id_listContext ctx);
}