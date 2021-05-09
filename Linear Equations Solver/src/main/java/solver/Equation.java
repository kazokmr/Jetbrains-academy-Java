package solver;

public class Equation {
    private final double a;
    private final double b;
    private final double c;

    public Equation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    @Override
    public String toString() {
        return String.format("Equation{ %dx + %dy = %d }", a, b, c);
    }
}
