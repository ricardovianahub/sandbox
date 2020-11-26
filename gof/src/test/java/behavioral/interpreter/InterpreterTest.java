package behavioral.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class InterpreterTest {

    private static Expr parseToken(String token, ArrayDeque<Expr> stack) {
        Expr left, right;
        switch(token) {
            case "+":
                right = stack.pop();
                left = stack.pop();
                return Expr.plus(left, right);
            case "-":
                right = stack.pop();
                left = stack.pop();
                return Expr.minus(left, right);
            default:
                return Expr.variable(token);
        }
    }
    public static Expr parse(String expression) {
        ArrayDeque<Expr> stack = new ArrayDeque<>();
        for (String token : expression.split(" ")) {
            stack.push(parseToken(token, stack));
        }
        return stack.pop();
    }

    @Test
    void interpret() {
        Expr expr = parse("w x z - +");
        Map<String, Integer> context = new HashMap<String, Integer>() {{
            put("w", 5);
            put("x", 10);
            put("z", 42);
        }};
        int result = expr.interpret(context);
        assertEquals(-27, result);
    }

    private interface Lambda1 {
        String execute(String text);
    }

    @Test
    void lambdaExample() {
        Lambda1 lambda1 = assembleLambda1();

        System.out.println(lambda1.execute("one"));
        System.out.println(lambda1.execute("two"));
        System.out.println(lambda1.execute("three"));
    }

    private Lambda1 assembleLambda1() {
        String prefix1 = "prefix1";
        String separator = " - ";
        return text -> prefix1 + separator + text;
    }
}
