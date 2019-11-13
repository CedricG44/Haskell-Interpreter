// Generated from D:/FIL/A3/Programmation basée sur un article de recherche/Haskell-Interpreter/src/main/java/fr/imt/haskell/interpreter/parser/antlr\Haskell.g4 by ANTLR 4.7.2
package fr.imt.haskell.interpreter.parser.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HaskellParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HaskellVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HaskellParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(HaskellParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link HaskellParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(HaskellParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryExpression}
	 * labeled alternative in {@link HaskellParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpression(HaskellParser.BinaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link HaskellParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(HaskellParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Const}
	 * labeled alternative in {@link HaskellParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst(HaskellParser.ConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConditionalExpression}
	 * labeled alternative in {@link HaskellParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpression(HaskellParser.ConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpression}
	 * labeled alternative in {@link HaskellParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(HaskellParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Application}
	 * labeled alternative in {@link HaskellParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplication(HaskellParser.ApplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Lambda}
	 * labeled alternative in {@link HaskellParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambda(HaskellParser.LambdaContext ctx);
	/**
	 * Visit a parse tree produced by {@link HaskellParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(HaskellParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link HaskellParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(HaskellParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HaskellParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(HaskellParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link HaskellParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(HaskellParser.BoolContext ctx);
}