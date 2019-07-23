// Generated from org\informationsystem\ismsuite\specifier\parser\Specification.g4 by ANTLR 4.7.2
package org.informationsystem.ismsuite.specifier.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SpecificationParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SpecificationVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#specication_file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecication_file(SpecificationParser.Specication_fileContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#process}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcess(SpecificationParser.ProcessContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#process_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcess_content(SpecificationParser.Process_contentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#place}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlace(SpecificationParser.PlaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#transition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransition(SpecificationParser.TransitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#argument_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument_list(SpecificationParser.Argument_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#variable_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_declaration(SpecificationParser.Variable_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#transaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransaction(SpecificationParser.TransactionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#register_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegister_operator(SpecificationParser.Register_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#deregister_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeregister_operator(SpecificationParser.Deregister_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#insert_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_operator(SpecificationParser.Insert_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#remove_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemove_operator(SpecificationParser.Remove_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(SpecificationParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#variable_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_list(SpecificationParser.Variable_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(SpecificationParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(SpecificationParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SpecificationParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(SpecificationParser.TypeContext ctx);
}