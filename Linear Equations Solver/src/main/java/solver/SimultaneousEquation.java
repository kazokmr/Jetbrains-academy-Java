package solver;

public class SimultaneousEquation {
    private final Equation equation1;
    private final Equation equation2;

    public SimultaneousEquation(Equation equation1, Equation equation2) {
        this.equation1 = equation1;
        this.equation2 = equation2;
    }

    public void solve() {
        double y = getY();
        double x = (equation1.getC() - equation1.getB() * y) / equation1.getA();
        System.out.printf("%e %e", x, y);
    }

    private double getY() {
        return (equation2.getC() - equation1.getC() * (equation2.getA() / equation1.getA())) /
                (equation2.getB() - equation1.getB() * (equation2.getA() / equation1.getA()));
    }
}

