// Generated from .\TFF.g4 by ANTLR 4.7.1
package org.architecturemining.ismodeler.proving.model.parsing;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TFFParser}.
 */
public interface TFFListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TFFParser#fof_quantifier}.
	 * @param ctx the parse tree
	 */
	void enterFof_quantifier(TFFParser.Fof_quantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#fof_quantifier}.
	 * @param ctx the parse tree
	 */
	void exitFof_quantifier(TFFParser.Fof_quantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#binary_connective}.
	 * @param ctx the parse tree
	 */
	void enterBinary_connective(TFFParser.Binary_connectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#binary_connective}.
	 * @param ctx the parse tree
	 */
	void exitBinary_connective(TFFParser.Binary_connectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#assoc_connective}.
	 * @param ctx the parse tree
	 */
	void enterAssoc_connective(TFFParser.Assoc_connectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#assoc_connective}.
	 * @param ctx the parse tree
	 */
	void exitAssoc_connective(TFFParser.Assoc_connectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#unary_connective}.
	 * @param ctx the parse tree
	 */
	void enterUnary_connective(TFFParser.Unary_connectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#unary_connective}.
	 * @param ctx the parse tree
	 */
	void exitUnary_connective(TFFParser.Unary_connectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_file}.
	 * @param ctx the parse tree
	 */
	void enterTff_file(TFFParser.Tff_fileContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_file}.
	 * @param ctx the parse tree
	 */
	void exitTff_file(TFFParser.Tff_fileContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_line}.
	 * @param ctx the parse tree
	 */
	void enterTff_line(TFFParser.Tff_lineContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_line}.
	 * @param ctx the parse tree
	 */
	void exitTff_line(TFFParser.Tff_lineContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#formula_role}.
	 * @param ctx the parse tree
	 */
	void enterFormula_role(TFFParser.Formula_roleContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#formula_role}.
	 * @param ctx the parse tree
	 */
	void exitFormula_role(TFFParser.Formula_roleContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_formula(TFFParser.Tff_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_formula(TFFParser.Tff_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_logic_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_logic_formula(TFFParser.Tff_logic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_logic_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_logic_formula(TFFParser.Tff_logic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_binary_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_binary_formula(TFFParser.Tff_binary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_binary_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_binary_formula(TFFParser.Tff_binary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_binary_nonassoc}.
	 * @param ctx the parse tree
	 */
	void enterTff_binary_nonassoc(TFFParser.Tff_binary_nonassocContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_binary_nonassoc}.
	 * @param ctx the parse tree
	 */
	void exitTff_binary_nonassoc(TFFParser.Tff_binary_nonassocContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_binary_assoc}.
	 * @param ctx the parse tree
	 */
	void enterTff_binary_assoc(TFFParser.Tff_binary_assocContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_binary_assoc}.
	 * @param ctx the parse tree
	 */
	void exitTff_binary_assoc(TFFParser.Tff_binary_assocContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_or_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_or_formula(TFFParser.Tff_or_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_or_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_or_formula(TFFParser.Tff_or_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_and_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_and_formula(TFFParser.Tff_and_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_and_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_and_formula(TFFParser.Tff_and_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_unitary_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_unitary_formula(TFFParser.Tff_unitary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_unitary_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_unitary_formula(TFFParser.Tff_unitary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_quantified_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_quantified_formula(TFFParser.Tff_quantified_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_quantified_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_quantified_formula(TFFParser.Tff_quantified_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_unary_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_unary_formula(TFFParser.Tff_unary_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_unary_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_unary_formula(TFFParser.Tff_unary_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void enterTff_atomic_formula(TFFParser.Tff_atomic_formulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_atomic_formula}.
	 * @param ctx the parse tree
	 */
	void exitTff_atomic_formula(TFFParser.Tff_atomic_formulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#fof_infix_unary}.
	 * @param ctx the parse tree
	 */
	void enterFof_infix_unary(TFFParser.Fof_infix_unaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#fof_infix_unary}.
	 * @param ctx the parse tree
	 */
	void exitFof_infix_unary(TFFParser.Fof_infix_unaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#fof_term}.
	 * @param ctx the parse tree
	 */
	void enterFof_term(TFFParser.Fof_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#fof_term}.
	 * @param ctx the parse tree
	 */
	void exitFof_term(TFFParser.Fof_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void enterArgument_list(TFFParser.Argument_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void exitArgument_list(TFFParser.Argument_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(TFFParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(TFFParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#tff_typed_atom}.
	 * @param ctx the parse tree
	 */
	void enterTff_typed_atom(TFFParser.Tff_typed_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#tff_typed_atom}.
	 * @param ctx the parse tree
	 */
	void exitTff_typed_atom(TFFParser.Tff_typed_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(TFFParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(TFFParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#variable_list}.
	 * @param ctx the parse tree
	 */
	void enterVariable_list(TFFParser.Variable_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#variable_list}.
	 * @param ctx the parse tree
	 */
	void exitVariable_list(TFFParser.Variable_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(TFFParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(TFFParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#atomic_word_list}.
	 * @param ctx the parse tree
	 */
	void enterAtomic_word_list(TFFParser.Atomic_word_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#atomic_word_list}.
	 * @param ctx the parse tree
	 */
	void exitAtomic_word_list(TFFParser.Atomic_word_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#atomic_word}.
	 * @param ctx the parse tree
	 */
	void enterAtomic_word(TFFParser.Atomic_wordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#atomic_word}.
	 * @param ctx the parse tree
	 */
	void exitAtomic_word(TFFParser.Atomic_wordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#atomic_defined_word}.
	 * @param ctx the parse tree
	 */
	void enterAtomic_defined_word(TFFParser.Atomic_defined_wordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#atomic_defined_word}.
	 * @param ctx the parse tree
	 */
	void exitAtomic_defined_word(TFFParser.Atomic_defined_wordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#atomic_system_word}.
	 * @param ctx the parse tree
	 */
	void enterAtomic_system_word(TFFParser.Atomic_system_wordContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#atomic_system_word}.
	 * @param ctx the parse tree
	 */
	void exitAtomic_system_word(TFFParser.Atomic_system_wordContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(TFFParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(TFFParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link TFFParser#file_name}.
	 * @param ctx the parse tree
	 */
	void enterFile_name(TFFParser.File_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TFFParser#file_name}.
	 * @param ctx the parse tree
	 */
	void exitFile_name(TFFParser.File_nameContext ctx);
}