package calculator;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Calculator {

    private final Scanner scanner;
    private final Variables variables;

    Calculator() {
        this.scanner = new Scanner(System.in);
        this.variables = new Variables();
    }

    void run() {
        do {
            String expression = scanner.nextLine();
            Command command = new Command(expression);
            if (command.isExit()) {
                return;
            }
            if (command.isCommand()) {
                continue;
            }
            if (variables.isVariable(expression)) {
                variables.assignment(expression);
                continue;
            }
            if (isInvalidExpresion(expression)) {
                continue;
            }
            expression = format(expression);
            List<String> postfix = new Postfix(expression).convert();
            if (isRemainParentheses(postfix)) {
                continue;
            }
            calculate(postfix);

        } while (true);
    }

    private void calculate(List<String> postfix) {
        Deque<BigInteger> stackOfNumber = new ArrayDeque<>();
        for (String term : postfix) {
            if (term.matches("[a-zA-Z]+")) {
                stackOfNumber.offerFirst(variables.valueOf(term));
                continue;
            }
            if (term.matches("\\d+")) {
                stackOfNumber.offerFirst(new BigInteger(term));
                continue;
            }
            BigInteger number1 = stackOfNumber.pollFirst();
            BigInteger number2 = stackOfNumber.pollFirst();
            BigInteger result = sumUp(term, number1, number2);
            stackOfNumber.offerFirst(result);
        }
        System.out.println(stackOfNumber.pollFirst());
    }

    private BigInteger sumUp(String operator, BigInteger number1, BigInteger number2) {
        switch (operator) {
            case "+":
                return number2.add(number1);
            case "-":
                return number2.subtract(number1);
            case "*":
                return number2.multiply(number1);
            case "/":
                return number2.divide(number1);
            case "^":
                return number2.pow(number1.intValue());
            default:
                return BigInteger.ZERO;
        }
    }

    private boolean isInvalidExpresion(String exp) {
        if (exp.matches(".*([*]{2,}|[/]{2,}|[\\^]{2,}).*")) {
            System.out.println("Invalid command");
            return true;
        }
        if (exp.matches("[^\\w\\s()+\\-*/^]")) {
            System.out.println("Invalid expression");
            return true;
        }
        if (exp.matches("(^[)*/^].+|.+[(+\\-*/^]$)")) {
            System.out.println("Invalid expression");
            return true;
        }
        Matcher matcher = Pattern.compile("[a-zA-Z]").matcher(exp);
        if (matcher.find()) {
            String variable = matcher.group();
            if (!variables.isKnown(variable)) {
                System.out.println("Unknown variable");
                return true;
            }
        }
        return false;
    }

    private String format(String exp) {
        return exp.replaceAll("--", "+")
                .replaceAll("\\++", "+")
                .replaceAll("(\\+-|-\\+)", "-")
                .replaceAll("\\+", " + ")
                .replaceAll("-", " - ")
                .replaceAll("\\*", " * ")
                .replaceAll("/", " / ")
                .replaceAll("\\(", " ( ")
                .replaceAll("\\)", " ) ");
    }

    private boolean isRemainParentheses(List<String> postfix) {
        for (String term : postfix) {
            if ("(".equals(term) || ")".equals(term)) {
                System.out.println("Invalid command");
                return true;
            }
        }
        return false;
    }
}
