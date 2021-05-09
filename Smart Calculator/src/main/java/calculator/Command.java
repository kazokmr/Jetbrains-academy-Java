package calculator;

class Command {
    private final String expression;

    Command(String expression) {
        this.expression = expression;
    }

    boolean isExit() {
        if (expression.matches("^/exit$")) {
            System.out.println("Bye!");
            return true;
        }
        return false;
    }

    boolean isCommand() {
        if (expression.isEmpty()) {
            return true;
        }
        if (expression.matches("^/help$")) {
            System.out.println("The program calculates the sum of numbers");
            return true;
        }
        if (expression.matches("/[\\w]+$")) {
            System.out.println("Unknown command");
            return true;
        }
        return false;
    }
}