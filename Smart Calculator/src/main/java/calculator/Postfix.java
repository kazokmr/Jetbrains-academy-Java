package calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Postfix {
    private final String[] terms;
    private final Deque<String> stackOfOperators;
    private final List<String> result;

    public Postfix(String infix) {
        this.terms = infix.split("\\s+");
        this.stackOfOperators = new ArrayDeque<>();
        this.result = new ArrayList<>(terms.length);
    }

    public List<String> convert() {

        for (String term : terms) {
            if (term.isBlank()) {
                continue;
            }
            // 1. Any operands to the result.
            if (term.matches("\\w+")) {
                result.add(term);
                continue;
            }
            // 2. if the stack is empty or a left parentheses on top, push term ont the stack.
            if (stackOfOperators.isEmpty()) {
                stackOfOperators.offerFirst(term);
                continue;
            }
            String topOfStack = stackOfOperators.peekFirst();
            if ("(".equals(topOfStack)) {
                stackOfOperators.offerFirst(term);
                continue;
            }
            // 3. if the term has higher precedence than the top of the stack, push it on the stack.
            if ("^".equals(term)) {
                stackOfOperators.offerFirst(term);
                continue;
            }
            if (("*".equals(term) || "/".equals(term)) && ("+".equals(topOfStack) || "-".equals(topOfStack))) {
                stackOfOperators.offerFirst(term);
                continue;
            }
            // 4. if the term has lower of equal precedence than the top of the stack
            if ("*".equals(term) || "/".equals(term) || "+".equals(term) || "-".equals(term)) {
                addHigherPrecedenceOpeToResult(term);
                continue;
            }
            // 5. if the term is a left parenthesis, push it on the stack.
            if ("(".equals(term)) {
                stackOfOperators.offerFirst(term);
                continue;
            }
            // 6. if the term is a right parenthesis, pop the stack and add to the result until a left parenthesis.
            if (")".equals(term)) {
                // Expression invalid explicitly.
                if (!stackOfOperators.contains("(")) {
                    result.add(")");
                    return result;
                }
                while (!stackOfOperators.isEmpty()) {
                    String operator = stackOfOperators.pollFirst();
                    if ("(".equals(operator)) {
                        break;
                    }
                    result.add(operator);
                }
            }
        }
        // 7. At the end of the method, pop the stack and add all operators to the end.
        while (!stackOfOperators.isEmpty()) {
            result.add(stackOfOperators.pollFirst());
        }
        return result;
    }

    private void addHigherPrecedenceOpeToResult(String operators) {
        while (!stackOfOperators.isEmpty()) {
            String topOfStack = stackOfOperators.peekFirst();
            if ((operators.equals("+") && !topOfStack.equals("("))
                    || (operators.equals("-") && !topOfStack.equals("("))
                    || (operators.equals("*") && !topOfStack.equals("(") && !topOfStack.equals("+") && !topOfStack.equals("-"))
                    || (operators.equals("/") && !topOfStack.equals("(") && !topOfStack.equals("+") && !topOfStack.equals("-"))
            ) {
                result.add(stackOfOperators.pollFirst());
                continue;
            }
            break;
        }
        stackOfOperators.offerFirst(operators);
    }
}
