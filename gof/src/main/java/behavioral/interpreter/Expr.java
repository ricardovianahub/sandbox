package behavioral.interpreter;

import java.util.Map;

public interface Expr {
    int interpret(Map<String, Integer> context);

    static Expr plus(Expr left, Expr right) {
        return context -> left.interpret(context) + right.interpret(context);
    }

    static Expr minus(Expr left, Expr right) {
        return context -> left.interpret(context) - right.interpret(context);
    }

    static Expr variable(String name) {
        return context -> context.getOrDefault(name, 0);
    }
}
