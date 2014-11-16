// Generated from D:/Workspace/Study/ANTLRTest/src\Math.g4 by ANTLR 4.4.1-dev
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MathParser}.
 */
public interface MathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MathParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(@NotNull MathParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(@NotNull MathParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#null_stmt}.
	 * @param ctx the parse tree
	 */
	void enterNull_stmt(@NotNull MathParser.Null_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#null_stmt}.
	 * @param ctx the parse tree
	 */
	void exitNull_stmt(@NotNull MathParser.Null_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull MathParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull MathParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull MathParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull MathParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull MathParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull MathParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleValue}
	 * labeled alternative in {@link MathParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimpleValue(@NotNull MathParser.SimpleValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleValue}
	 * labeled alternative in {@link MathParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimpleValue(@NotNull MathParser.SimpleValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(@NotNull MathParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(@NotNull MathParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#read_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRead_stmt(@NotNull MathParser.Read_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#read_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRead_stmt(@NotNull MathParser.Read_stmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Write}
	 * labeled alternative in {@link MathParser#write_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWrite(@NotNull MathParser.WriteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Write}
	 * labeled alternative in {@link MathParser#write_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWrite(@NotNull MathParser.WriteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MathParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(@NotNull MathParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MathParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(@NotNull MathParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Addition}
	 * labeled alternative in {@link MathParser#expr2}.
	 * @param ctx the parse tree
	 */
	void enterAddition(@NotNull MathParser.AdditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Addition}
	 * labeled alternative in {@link MathParser#expr2}.
	 * @param ctx the parse tree
	 */
	void exitAddition(@NotNull MathParser.AdditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#procedure}.
	 * @param ctx the parse tree
	 */
	void enterProcedure(@NotNull MathParser.ProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#procedure}.
	 * @param ctx the parse tree
	 */
	void exitProcedure(@NotNull MathParser.ProcedureContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Multiplication}
	 * labeled alternative in {@link MathParser#expr3}.
	 * @param ctx the parse tree
	 */
	void enterMultiplication(@NotNull MathParser.MultiplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Multiplication}
	 * labeled alternative in {@link MathParser#expr3}.
	 * @param ctx the parse tree
	 */
	void exitMultiplication(@NotNull MathParser.MultiplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(@NotNull MathParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(@NotNull MathParser.IndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Value}
	 * labeled alternative in {@link MathParser#expr4}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull MathParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Value}
	 * labeled alternative in {@link MathParser#expr4}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull MathParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#proc_stmt}.
	 * @param ctx the parse tree
	 */
	void enterProc_stmt(@NotNull MathParser.Proc_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#proc_stmt}.
	 * @param ctx the parse tree
	 */
	void exitProc_stmt(@NotNull MathParser.Proc_stmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link MathParser#expr4}.
	 * @param ctx the parse tree
	 */
	void enterMinus(@NotNull MathParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link MathParser#expr4}.
	 * @param ctx the parse tree
	 */
	void exitMinus(@NotNull MathParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#var_def}.
	 * @param ctx the parse tree
	 */
	void enterVar_def(@NotNull MathParser.Var_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#var_def}.
	 * @param ctx the parse tree
	 */
	void exitVar_def(@NotNull MathParser.Var_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#proc_def}.
	 * @param ctx the parse tree
	 */
	void enterProc_def(@NotNull MathParser.Proc_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#proc_def}.
	 * @param ctx the parse tree
	 */
	void exitProc_def(@NotNull MathParser.Proc_defContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link MathParser#expr5}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull MathParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link MathParser#expr5}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull MathParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Ident}
	 * labeled alternative in {@link MathParser#expr5}.
	 * @param ctx the parse tree
	 */
	void enterIdent(@NotNull MathParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Ident}
	 * labeled alternative in {@link MathParser#expr5}.
	 * @param ctx the parse tree
	 */
	void exitIdent(@NotNull MathParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Comprasion}
	 * labeled alternative in {@link MathParser#expr1}.
	 * @param ctx the parse tree
	 */
	void enterComprasion(@NotNull MathParser.ComprasionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Comprasion}
	 * labeled alternative in {@link MathParser#expr1}.
	 * @param ctx the parse tree
	 */
	void exitComprasion(@NotNull MathParser.ComprasionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(@NotNull MathParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(@NotNull MathParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MathParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(@NotNull MathParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MathParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(@NotNull MathParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#definitions}.
	 * @param ctx the parse tree
	 */
	void enterDefinitions(@NotNull MathParser.DefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#definitions}.
	 * @param ctx the parse tree
	 */
	void exitDefinitions(@NotNull MathParser.DefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stmt(@NotNull MathParser.While_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stmt(@NotNull MathParser.While_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(@NotNull MathParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(@NotNull MathParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#repeat_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRepeat_stmt(@NotNull MathParser.Repeat_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#repeat_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRepeat_stmt(@NotNull MathParser.Repeat_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#proc_args}.
	 * @param ctx the parse tree
	 */
	void enterProc_args(@NotNull MathParser.Proc_argsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#proc_args}.
	 * @param ctx the parse tree
	 */
	void exitProc_args(@NotNull MathParser.Proc_argsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull MathParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull MathParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Writeln}
	 * labeled alternative in {@link MathParser#writeln_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWriteln(@NotNull MathParser.WritelnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Writeln}
	 * labeled alternative in {@link MathParser#writeln_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWriteln(@NotNull MathParser.WritelnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link MathParser#expr5}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis(@NotNull MathParser.ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link MathParser#expr5}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis(@NotNull MathParser.ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link MathParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull MathParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MathParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull MathParser.VariableContext ctx);
}