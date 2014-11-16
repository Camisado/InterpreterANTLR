import java.util.*;

import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Created by Костя on 28.10.2014.
 */

public class Interpreter extends MathBaseVisitor<Integer> {
    Map<String,Integer> map = new HashMap<String, Integer>();
    Map<String,
            HashMap<MathParser.ProcedureContext,
                    HashMap<String,
                            Integer>>> procMap = new HashMap<String, HashMap<MathParser.ProcedureContext, HashMap<String, Integer>>>();

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

    @Override
    public Integer visitVariable(@NotNull MathParser.VariableContext ctx) {
        //System.out.println("variable " + ctx.getText());
        //System.out.println(ctx.type().getText());
        for (int i = 0; i < ctx.IDENT().size(); i++) {
            //System.out.println(ctx.IDENT(i).getText());
            if(ctx.type().getText().equals("integer")) {
                if(!map.containsKey(ctx.IDENT(i).getText())) {
                    map.put(ctx.IDENT(i).getText(), new Integer(0));
                }
            }
        }
        return null;
    }

    @Override
    public Integer visitIdent(@NotNull MathParser.IdentContext ctx) {
        String name = ctx.getText();
        Integer result = 0;
        System.out.println("ident " + ctx.getParent().getClass());
        if (map.containsKey(name)) {
            result = map.get(name);
        }
        return result;
    }

    @Override
    public Integer visitNumber(@NotNull MathParser.NumberContext ctx) {
        String text = ctx.getText();
        return Integer.parseInt(text);
    }

    @Override
    public Integer visitMinus(@NotNull MathParser.MinusContext ctx) {
        int result = visit(ctx.expr4());
        return -result;
    }

    @Override
    public Integer visitParenthesis(@NotNull MathParser.ParenthesisContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitMultiplication(@NotNull MathParser.MultiplicationContext ctx) {
        Integer left = null;
        Integer right = null;
        Integer result = 1;
        int pos = 0;
        String line = ctx.getText();
        char op = '*';
        pos += ctx.expr4(0).getText().length();
        //System.out.println(ctx.getText() + " " + ctx.expr4(0).getText());
        if (!ctx.getText().equals(ctx.expr4(0).getText())) {
            op = line.charAt(pos);
        }
        pos++;
        left = visit(ctx.expr4(0));
        result *= left;
        for (int i = 1; i < ctx.expr4().size(); i++) {
            right = visit(ctx.expr4(i));
            if(right != null) {
                switch (op) {
                    case '*':
                        result *= right;
                        break;
                    case '/':
                        result /= right;
                        break;
                }
                if(i < ctx.expr4().size() - 1) {
                    pos += ctx.expr4(i).getText().length();
                    op = line.charAt(pos);
                    pos++;
                }
            }
        }
        return result;
    }

    @Override
    public Integer visitAddition(@NotNull MathParser.AdditionContext ctx) {
        Integer left = null;
        Integer right = null;
        Integer result = 0;
        int pos = 0;
        String line = ctx.getText();
        char op = ' ';
        if (!ctx.getText().equals(ctx.expr3(0).getText())){
            pos += ctx.expr3(0).getText().length();
            op = line.charAt(pos);
            pos++;
        }

        if (tryParseInt(ctx.expr3(0).getText()) || ctx.expr3(0).getText().contains("/") || ctx.expr3(0).getText().contains("*")) {
            left = visit(ctx.expr3(0));
        } else {
            left = map.get(ctx.expr3(0).getText());
        }
        //System.out.println(ctx.expr3(0).getText());
        result += left;
        for (int i = 1; i < ctx.expr3().size(); i++) {
            //System.out.println(op + " " + ctx.expr3(i).getText());
            if (tryParseInt(ctx.expr3(i).getText()) || ctx.expr3(i).getText().contains("/") || ctx.expr3(i).getText().contains("*")) {
                right = visit(ctx.expr3(i));
            } else {
                right = map.get(ctx.expr3(i).getText());
            }
            if(right != null) {
                switch (op) {
                    case '+':
                        result += right;
                        break;
                    case '-':
                        result -= right;
                        break;
                }
                if(i < ctx.expr3().size() - 1) {
                    pos += ctx.expr3(i).getText().length();
                    op = line.charAt(pos);
                    pos++;
                }
            }
        }
        return result;
    }

    @Override
    public Integer visitSimpleValue(@NotNull MathParser.SimpleValueContext ctx) {
        Integer result = visit(ctx.expr());
        if(ctx.getParent().getParent().getParent().getClass().getCanonicalName().equals("MathParser.BodyContext")) {
            MathParser.ProcedureContext ctxProc = (MathParser.ProcedureContext) ctx.getParent().getParent().getParent().getParent();
            String procName = ctxProc.header().IDENT().getText();
            HashMap<MathParser.ProcedureContext, HashMap<String, Integer>> innerMap = procMap.get(procName);
            HashMap<String, Integer> localMap = innerMap.get(ctxProc);
            TerminalNode id = ctx.IDENT();
            if (id != null) {
                String name = id.getText();
                if (localMap.containsKey(name)) {
                    localMap.put(name, result);
                } else {
                    System.err.println("Undefined variable: " + name);
                    System.exit(0);
                    return null;
                }
            }
        } else {
            TerminalNode id = ctx.IDENT();
            if (id != null) {
                String name = id.getText();
                if (map.containsKey(name)) {
                    map.put(name, result);
                } else {
                    System.err.println("Undefined variable: " + name);
                    System.exit(0);
                    return null;
                }
            }
        }
        //System.out.println(ctx.getText() + " => " + result);
        return result;
    }

    @Override
    public Integer visitValue(@NotNull MathParser.ValueContext ctx) {
        return visit(ctx.expr5());
    }

    @Override
    public Integer visitWrite(@NotNull MathParser.WriteContext ctx) {
        return visit(ctx.proc_args());
    }

    @Override
    public Integer visitProc_args(@NotNull MathParser.Proc_argsContext ctx) {
        String res="=> ";
        if (ctx.getParent().getParent().getParent().getParent().getClass().getCanonicalName().equals("MathParser.BodyContext")) {
            MathParser.ProcedureContext ctxProc = (MathParser.ProcedureContext) ctx.getParent().getParent().getParent().getParent().getParent();
            String procName = ctxProc.header().IDENT().getText();
            HashMap<MathParser.ProcedureContext, HashMap<String, Integer>> innerMap = procMap.get(procName);
            HashMap<String, Integer> localMap = innerMap.get(ctxProc);
            for (int i = 0; i < ctx.expr().size(); i++) {
                if (localMap.containsKey(ctx.expr(i).getText())) {
                    if (i >= 1) {
                        res += ",";
                    }
                    res += localMap.get(ctx.expr(i).getText()).toString();
                } else {
                    System.err.println("Undefined variable: " + ctx.expr(i).getText());
                    System.exit(0);
                    return null;
                }
            }
            if (ctx.getParent().getClass().getCanonicalName().equals("WritelnContext")) {
                res += "\n";
            }
        } else {
            for (int i = 0; i < ctx.expr().size(); i++) {
                if (map.containsKey(ctx.expr(i).getText())) {
                    if (i >= 1) {
                        res += ",";
                    }
                    res += map.get(ctx.expr(i).getText()).toString();
                } else {
                    System.err.println("Undefined variable: " + ctx.expr(i).getText());
                    System.exit(0);
                    return null;
                }
            }
            if (ctx.getParent().getClass().getCanonicalName().equals("WritelnContext")) {
                res += "\n";
            }
        }
        System.out.println(res);
        return null;
    }

    @Override
    public Integer visitRead_stmt(@NotNull MathParser.Read_stmtContext ctx) {
        System.out.println(ctx.IDENT(0));
        Scanner sc = new Scanner(System.in); // создаём объект класса Scanner
        for (int i = 0; i < ctx.IDENT().size(); i++) {
            System.out.print("Input " + ctx.IDENT(i) + ": ");
            if (map.containsKey(ctx.IDENT(i).getText())) {
                if (sc.hasNextInt()) {
                    int res = sc.nextInt();
                    map.put(ctx.IDENT(i).getText(), res);
                }
            } else {
                System.err.println("Undefined variable: " + ctx.IDENT(i).getText());
                System.exit(0);
            }
        }
        return null;
    }

    @Override
    public Integer visitProcedure(@NotNull MathParser.ProcedureContext ctx) {
        HashMap<String, Integer> localMap = new HashMap<String, Integer>();
        if(ctx.body().var_def() != null) {
            for (int i = 0; i < ctx.body().var_def().variable().size(); i++) {
                for (int j = 0; j < ctx.body().var_def().variable(i).IDENT().size(); j++) {
                    localMap.put(ctx.body().var_def().variable(i).IDENT(j).getText(), new Integer(0));
                }
            }
        }

        HashMap<MathParser.ProcedureContext, HashMap<String, Integer>> innerMap = new HashMap<MathParser.ProcedureContext, HashMap<String, Integer>>();
        innerMap.put(ctx,localMap);
        procMap.put(ctx.header().IDENT().getText(), innerMap);
        return null;
    }

    @Override
    public
    Integer visitProc_stmt(@NotNull MathParser.Proc_stmtContext ctx) {
        MathParser.ProcedureContext procCtx;
        if (procMap.containsKey(ctx.IDENT().getText())) {
            int c = 0;
            procCtx = procMap.get(ctx.IDENT().getText()).keySet().iterator().next();
            //System.out.println(procCtx.body().var_def());
            for (int i = 0; i < procCtx.header().variable().size(); i++) {
                for (int j = 0; j < procCtx.header().variable(i).IDENT().size(); j++) {
                    //System.out.println("put - " + procCtx.header().variable(i).IDENT(j) + " - " + ctx.proc_args().expr(i+j+c).getText());
                    Integer res = null;
                    if (map.containsKey(ctx.proc_args().expr(i+j+c).getText())) {
                        res = map.get(ctx.proc_args().expr(i+j+c).getText());
                    } else {
                        if(tryParseInt(ctx.proc_args().expr(i+j+c).getText())) {
                            res = Integer.parseInt(ctx.proc_args().expr(i+j+c).getText());
                        } else {
                            System.err.println("Undefined variable: " + ctx.proc_args().expr(i+j+c).getText());
                            System.exit(0);
                        }
                    }
                    map.put(procCtx.header().variable(i).IDENT(j).getText(), res);
                }
                c++;
            }
            visit(procCtx.body());
            c = 0;
            for (int i = 0; i < procCtx.header().variable().size(); i++) {
                for (int j = 0; j < procCtx.header().variable(i).IDENT().size(); j++) {
                    //System.out.println("remove - " + procCtx.header().variable(i).IDENT(j) + " - " + ctx.proc_args().expr(i+j+c).getText());
                    Integer res = null;
                    if (map.containsKey(procCtx.header().variable(i).IDENT(j).getText())) {
                        map.remove(procCtx.header().variable(i).IDENT(j).getText());
                    }
                }
                c++;
            }
            if (procCtx.body().var_def() != null) {
                c = 0;
                for (int i = 0; i < procCtx.body().var_def().variable().size(); i++) {
                    for (int j = 0; j < procCtx.body().var_def().variable(i).IDENT().size(); j++) {
                        //System.out.println(procCtx.body().var_def().variable(i + j + c).IDENT(i).getText());
                        if (map.containsKey(procCtx.body().var_def().variable(i + j + c).IDENT(i).getText())) {
                            //System.out.println("remove var_def - " + procCtx.body().var_def().variable(i).IDENT(j) + " " + procCtx.body().var_def().variable(i + j + c).getText());
                            map.remove(procCtx.body().var_def().variable(i + j + c).IDENT(i).getText());
                        }
                    }
                    c++;
                }
            }
        } else {
            System.err.println("Undefined procedure: " + ctx.IDENT().getText());
            System.exit(0);
        }
        return null;
    }

    @Override
    public Integer visitIfStmt(@NotNull MathParser.IfStmtContext ctx){
        // System.out.println(ctx.getText());
        // System.out.println("Expr: "+ctx.expr().getText());
        // System.out.println("Stmt: "+ctx.stmt(0).getText());
        visit(ctx.expr());
        return null;
    }

    @Override
    public Integer visitComprasion(@NotNull MathParser.ComprasionContext ctx){
        //   System.out.println("size: " + ctx.expr2().size());
//        System.out.println("ctx : "+ctx.getClass());
//        System.out.println("ctx : "+ctx.getText());
        //    System.out.println("ctx Par : " + ctx.getParent().getParent().getClass());
        //    System.out.println(ctx.expr2(0).getText());
        boolean flag = false;
        if(ctx.expr2().size()>1){
            if(ctx.getParent().getParent().getClass().getCanonicalName().equals("MathParser.IfStmtContext")) {
                MathParser.IfStmtContext ctxIfStmt = (MathParser.IfStmtContext) ctx.expr2(0).getParent().getParent().getParent();
                switch (ctx.op.getType()) {
                    case MathParser.EQUALS: {
                        //System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int == res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int == res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int == res_visit) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.NOT_EQUAL: {
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int != res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int != res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int != res_visit) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.LESS: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int < res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int < res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int < res_visit) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.MORE: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int > res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int > res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int > res_visit) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.LESS_EQUAL: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int <= res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int <= res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int <= res_visit) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.MORE_EQUAL: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int >= res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int >= res_right) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int >= res_visit) {
                                    return visit(ctxIfStmt.stmt(0));
                                } else {
                                    if (ctxIfStmt.stmt().size() > 1) {
                                        return visit(ctxIfStmt.stmt(1));
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            } else if ((ctx.getParent().getParent().getClass().getCanonicalName().equals("MathParser.While_stmtContext"))){
                MathParser.While_stmtContext ctxWhile_stmt = (MathParser.While_stmtContext) ctx.expr2(0).getParent().getParent().getParent();
                switch (ctx.op.getType()) {
                    case MathParser.EQUALS: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                while (res_int == res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                while (res_int == res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                while (res_int == res_visit) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.NOT_EQUAL: {
                        //System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                while (res_int != res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                while (res_int != res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                while (res_int != res_visit) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.LESS: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                while (res_int < res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                while (res_int < res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                while (res_int < res_visit) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.LESS_EQUAL: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                while (res_int <= res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                while (res_int <= res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                while (res_int <= res_visit) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.MORE: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                while (res_int > res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                while (res_int > res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                while (res_int > res_visit) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.MORE_EQUAL: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                while (res_int >= res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                while (res_int >= res_right) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                while (res_int >= res_visit) {
                                    visit(ctxWhile_stmt.stmt());
                                    res = map.get(ctx.expr2(0).getText());
                                    res_int = res.intValue();
                                }
                            }
                        }
                        break;
                    }
                }
            } else if ((ctx.getParent().getParent().getClass().getCanonicalName().equals("MathParser.Repeat_stmtContext"))){
                MathParser.Repeat_stmtContext ctxRepeat_stmt = (MathParser.Repeat_stmtContext) ctx.expr2(0).getParent().getParent().getParent();
                switch (ctx.op.getType()) {
                    case MathParser.EQUALS: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int == res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int == res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int == res_visit) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.NOT_EQUAL: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int != res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int != res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int != res_visit) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.LESS: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int < res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int < res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int < res_visit) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.MORE: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int > res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int > res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int > res_visit) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.LESS_EQUAL: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int <= res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int <= res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int <= res_visit) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.MORE_EQUAL: {
                        //             System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            res = map.get(ctx.expr2(0).getText());
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            if (tryParseInt(ctx.expr2(1).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(1).getText());
                                if (res_int >= res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else if (map.containsKey(ctx.expr2(1).getText())) {
                                int res_right = visit(ctx.expr2(1));
                                if (res_int >= res_right) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(1));
                                if (res_int >= res_visit) {
                                    return null;
                                } else {
                                    visit(ctxRepeat_stmt);
                                }
                            }
                        }
                        break;
                    }
                }
            }
        } else {
            if ((ctx.getParent().getParent().getClass().getCanonicalName().equals("MathParser.ForStmtContext"))){
                MathParser.ForStmtContext ctxForStmt = (MathParser.ForStmtContext) ctx.expr2(0).getParent().getParent().getParent();
                switch (ctxForStmt.op.getType()) {
                    case MathParser.TO: {
                        MathParser.SimpleValueContext ctxForAssignStmt = (MathParser.SimpleValueContext) ctxForStmt.assign_stmt();
                        // System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        res = map.get(ctx.expr2(0).getText());
                        int res_assign = visit(ctxForStmt.assign_stmt());
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            for(int i = res_assign; i < res_int; ++i){
                                map.put(ctxForAssignStmt.IDENT().getText(), i);
                                visit(ctxForStmt.stmt());
                            }
                        } else {
                            if (tryParseInt(ctx.expr2(0).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(0).getText());
                                for(int i = res_assign; i < res_right; ++i){
                                    map.put(ctxForAssignStmt.IDENT().getText(), i);
                                    visit(ctxForStmt.stmt());
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(0));
                                for(int i = res_assign; i < res_visit; ++i){
                                    map.put(ctxForAssignStmt.IDENT().getText(), i);
                                    visit(ctxForStmt.stmt());
                                }
                            }
                        }
                        break;
                    }
                    case MathParser.DOWNTO: {
                        MathParser.SimpleValueContext ctxForAssignStmt = (MathParser.SimpleValueContext) ctxForStmt.assign_stmt();
                        // System.out.println(ctx.expr2(0).getText());
                        Integer res = null;
                        int res_int = 0;
                        res = map.get(ctx.expr2(0).getText());
                        int res_assign = visit(ctxForStmt.assign_stmt());
                        if (map.containsKey(ctx.expr2(0).getText())) {
                            if (res != null) {
                                res_int = res.intValue();
                            }
                            for(int i = res_assign; i > res_int; --i){
                                map.put(ctxForAssignStmt.IDENT().getText(), i);
                                visit(ctxForStmt.stmt());
                            }
                        } else {
                            if (tryParseInt(ctx.expr2(0).getText())) {
                                int res_right = Integer.parseInt(ctx.expr2(0).getText());
                                for(int i = res_assign; i > res_right; --i){
                                    map.put(ctxForAssignStmt.IDENT().getText(), i);
                                    visit(ctxForStmt.stmt());
                                }
                            } else {
                                Integer res_visit = visit(ctx.expr2(0));
                                for(int i = res_assign; i > res_visit; --i){
                                    map.put(ctxForAssignStmt.IDENT().getText(), i);
                                    visit(ctxForStmt.stmt());
                                }
                            }
                        }
                        break;
                    }
                }
            } else {
                if (map.containsKey(ctx.expr2(0).getText())) {
                    if ((ctx.getParent().getParent().getClass().getCanonicalName().equals("MathParser.IfStmtContext"))) {
                        MathParser.IfStmtContext ctxIfStmt = (MathParser.IfStmtContext) ctx.expr2(0).getParent().getParent().getParent();
                        Integer res_ident = visit(ctx.expr2(0));
                        if (res_ident.intValue() > 0) {
                            return visit(ctxIfStmt.stmt(0));
                        } else {
                            if (ctxIfStmt.stmt().size() > 1) {
                                return visit(ctxIfStmt.stmt(1));
                            }
                        }
                    } else if ((ctx.getParent().getParent().getClass().getCanonicalName().equals("MathParser.Repeat_stmtContext"))){
                        MathParser.Repeat_stmtContext ctxRepeat_stmt = (MathParser.Repeat_stmtContext) ctx.expr2(0).getParent().getParent().getParent();
                        Integer res_ident = visit(ctx.expr2(0));
                        if (res_ident.intValue() > 0) {
                            return visit(ctxRepeat_stmt);
                        } else {
                            return null;
                        }
                    }
                }
            }
        }
        return visit(ctx.expr2(0));
    }

    @Override
    public Integer visitWhile_stmt(@NotNull MathParser.While_stmtContext ctx){
//        System.out.println(ctx.getText());
//        System.out.println("Expr: "+ctx.expr().getText());
//        System.out.println("Stmt: "+ctx.stmt().getText());
        visit(ctx.expr());
        return null;
    }

    @Override
    public Integer visitForStmt(@NotNull MathParser.ForStmtContext ctx){
//        System.out.println(ctx.getText());
//        System.out.println("Assign: " + ctx.assign_stmt().getText());
//        System.out.println("Expr: "+ctx.expr().getText());
//        System.out.println("Stmt: "+ctx.stmt().getText());
        visit(ctx.expr());
        return null;
    }

    @Override
    public Integer visitRepeat_stmt(@NotNull MathParser.Repeat_stmtContext ctx){
//        System.out.println(ctx.getText());
        //   System.out.println("Expr: "+ctx.expr().getText());
        //  System.out.println("Stmt: "+ctx.stmt(0).getText());
        for(int i=0; i<ctx.stmt().size();i++){
            visit(ctx.stmt(i));
        }
        visit(ctx.expr());
        return null;
    }
}