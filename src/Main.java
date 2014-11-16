import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Yuri on 28.10.2014.
 */
public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("src\\test.txt");
            ANTLRInputStream stream = (!file.exists())
                    ? new ANTLRInputStream(System.in)
                    : new ANTLRInputStream(new FileInputStream(file));
            MathLexer lexer = new MathLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MathParser parser = new MathParser(tokens);
            ParseTree tree = parser.program();
            Interpreter interpreter = new Interpreter();
            interpreter.visit(tree);
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
