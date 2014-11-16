// Generated from D:/Workspace/Study/ANTLRTest/src\Math.g4 by ANTLR 4.4.1-dev
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MathParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MathVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MathParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(@NotNull MathParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#null_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull_stmt(@NotNull MathParser.Null_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull MathParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull MathParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull MathParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleValue}
	 * labeled alternative in {@link MathParser#assign_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleValue(@NotNull MathParser.SimpleValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(@NotNull MathParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#read_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead_stmt(@NotNull MathParser.Read_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Write}
	 * labeled alternative in {@link MathParser#write_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrite(@NotNull MathParser.WriteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MathParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(@NotNull MathParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Addition}
	 * labeled alternative in {@link MathParser#expr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddition(@NotNull MathParser.AdditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#procedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure(@NotNull MathParser.ProcedureContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Multiplication}
	 * labeled alternative in {@link MathParser#expr3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplication(@NotNull MathParser.MultiplicationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#index}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex(@NotNull MathParser.IndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Value}
	 * labeled alternative in {@link MathParser#expr4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull MathParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#proc_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_stmt(@NotNull MathParser.Proc_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link MathParser#expr4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(@NotNull MathParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#var_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_def(@NotNull MathParser.Var_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#proc_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_def(@NotNull MathParser.Proc_defContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link MathParser#expr5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull MathParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ident}
	 * labeled alternative in {@link MathParser#expr5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(@NotNull MathParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Comprasion}
	 * labeled alternative in {@link MathParser#expr1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComprasion(@NotNull MathParser.ComprasionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(@NotNull MathParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MathParser#for_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(@NotNull MathParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#definitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinitions(@NotNull MathParser.DefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(@NotNull MathParser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(@NotNull MathParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#repeat_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeat_stmt(@NotNull MathParser.Repeat_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#proc_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_args(@NotNull MathParser.Proc_argsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull MathParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Writeln}
	 * labeled alternative in {@link MathParser#writeln_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteln(@NotNull MathParser.WritelnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link MathParser#expr5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis(@NotNull MathParser.ParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(@NotNull MathParser.VariableContext ctx);
}