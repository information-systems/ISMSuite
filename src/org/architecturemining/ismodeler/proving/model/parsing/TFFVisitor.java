// Generated from TFF.g4 by ANTLR 4.7.1
package org.architecturemining.ismodeler.proving.model.parsing;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TFFParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TFFVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TFFParser#fof_quantifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFof_quantifier(TFFParser.Fof_quantifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#binary_connective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary_connective(TFFParser.Binary_connectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#assoc_connective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssoc_connective(TFFParser.Assoc_connectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#unary_connective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_connective(TFFParser.Unary_connectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_file(TFFParser.Tff_fileContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_line(TFFParser.Tff_lineContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#formula_role}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormula_role(TFFParser.Formula_roleContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_formula(TFFParser.Tff_formulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_logic_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_logic_formula(TFFParser.Tff_logic_formulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_binary_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_binary_formula(TFFParser.Tff_binary_formulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_binary_nonassoc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_binary_nonassoc(TFFParser.Tff_binary_nonassocContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_binary_assoc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_binary_assoc(TFFParser.Tff_binary_assocContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_or_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_or_formula(TFFParser.Tff_or_formulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_and_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_and_formula(TFFParser.Tff_and_formulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_unitary_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_unitary_formula(TFFParser.Tff_unitary_formulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_quantified_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_quantified_formula(TFFParser.Tff_quantified_formulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_unary_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_unary_formula(TFFParser.Tff_unary_formulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_atomic_formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_atomic_formula(TFFParser.Tff_atomic_formulaContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#fof_infix_unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFof_infix_unary(TFFParser.Fof_infix_unaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#fof_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFof_term(TFFParser.Fof_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#argument_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument_list(TFFParser.Argument_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(TFFParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#tff_typed_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTff_typed_atom(TFFParser.Tff_typed_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(TFFParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#variable_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_list(TFFParser.Variable_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(TFFParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#atomic_word_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic_word_list(TFFParser.Atomic_word_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#atomic_word}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic_word(TFFParser.Atomic_wordContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#atomic_defined_word}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic_defined_word(TFFParser.Atomic_defined_wordContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#atomic_system_word}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic_system_word(TFFParser.Atomic_system_wordContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(TFFParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link TFFParser#file_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile_name(TFFParser.File_nameContext ctx);
}