// Generated from Specification.g4 by ANTLR 4.7.1
package org.architecturemining.ismodeler.specification.parsing;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SpecificationParser}.
 */
public interface SpecificationListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#specication_file}.
	 * @param ctx the parse tree
	 */
	void enterSpecication_file(SpecificationParser.Specication_fileContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#specication_file}.
	 * @param ctx the parse tree
	 */
	void exitSpecication_file(SpecificationParser.Specication_fileContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#process}.
	 * @param ctx the parse tree
	 */
	void enterProcess(SpecificationParser.ProcessContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#process}.
	 * @param ctx the parse tree
	 */
	void exitProcess(SpecificationParser.ProcessContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#process_content}.
	 * @param ctx the parse tree
	 */
	void enterProcess_content(SpecificationParser.Process_contentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#process_content}.
	 * @param ctx the parse tree
	 */
	void exitProcess_content(SpecificationParser.Process_contentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#place}.
	 * @param ctx the parse tree
	 */
	void enterPlace(SpecificationParser.PlaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#place}.
	 * @param ctx the parse tree
	 */
	void exitPlace(SpecificationParser.PlaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#transition}.
	 * @param ctx the parse tree
	 */
	void enterTransition(SpecificationParser.TransitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#transition}.
	 * @param ctx the parse tree
	 */
	void exitTransition(SpecificationParser.TransitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void enterArgument_list(SpecificationParser.Argument_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void exitArgument_list(SpecificationParser.Argument_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVariable_declaration(SpecificationParser.Variable_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVariable_declaration(SpecificationParser.Variable_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#transaction}.
	 * @param ctx the parse tree
	 */
	void enterTransaction(SpecificationParser.TransactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#transaction}.
	 * @param ctx the parse tree
	 */
	void exitTransaction(SpecificationParser.TransactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#register_operator}.
	 * @param ctx the parse tree
	 */
	void enterRegister_operator(SpecificationParser.Register_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#register_operator}.
	 * @param ctx the parse tree
	 */
	void exitRegister_operator(SpecificationParser.Register_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#insert_operator}.
	 * @param ctx the parse tree
	 */
	void enterInsert_operator(SpecificationParser.Insert_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#insert_operator}.
	 * @param ctx the parse tree
	 */
	void exitInsert_operator(SpecificationParser.Insert_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#remove_operator}.
	 * @param ctx the parse tree
	 */
	void enterRemove_operator(SpecificationParser.Remove_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#remove_operator}.
	 * @param ctx the parse tree
	 */
	void exitRemove_operator(SpecificationParser.Remove_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(SpecificationParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(SpecificationParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#variable_list}.
	 * @param ctx the parse tree
	 */
	void enterVariable_list(SpecificationParser.Variable_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#variable_list}.
	 * @param ctx the parse tree
	 */
	void exitVariable_list(SpecificationParser.Variable_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(SpecificationParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(SpecificationParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(SpecificationParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(SpecificationParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SpecificationParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(SpecificationParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SpecificationParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(SpecificationParser.TypeContext ctx);
}