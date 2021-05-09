package solver;

public class LinearEquation {

    private final ComplexNumbers[] coefficients;
    private final ComplexNumbers constant;

    public LinearEquation(String equation) {
        String[] columns = equation.split("\\s+");
        this.coefficients = new ComplexNumbers[columns.length - 1];
        for (int i = 0; i < columns.length - 1; i++) {
            this.coefficients[i] = new ComplexNumbers(columns[i]);
        }
        this.constant = new ComplexNumbers(columns[columns.length - 1]);
    }

    LinearEquation(ComplexNumbers[] coefficients, ComplexNumbers constant) {
        this.coefficients = coefficients;
        this.constant = constant;
    }

    LinearEquation add(LinearEquation addend) {
        ComplexNumbers[] addedCoefficients = new ComplexNumbers[this.coefficients.length];
        for (int i = 0; i < addedCoefficients.length; i++) {
            addedCoefficients[i] = this.coefficients[i].add(addend.coefficients[i]);
        }
        ComplexNumbers addedConstant = this.constant.add(addend.constant);
        return new LinearEquation(addedCoefficients, addedConstant);
    }

    LinearEquation multiple(ComplexNumbers complexNumbers) {
        ComplexNumbers[] multipliedCoefficients = new ComplexNumbers[this.coefficients.length];
        for (int i = 0; i < multipliedCoefficients.length; i++) {
            multipliedCoefficients[i] = this.coefficients[i].multiply(complexNumbers);
        }
        ComplexNumbers multipliedConstant = this.constant.multiply(complexNumbers);
        return new LinearEquation(multipliedCoefficients, multipliedConstant);
    }

    LinearEquation divide(ComplexNumbers complexNumbers) {
        ComplexNumbers[] dividedCoefficients = new ComplexNumbers[this.coefficients.length];
        for (int i = 0; i < dividedCoefficients.length; i++) {
            dividedCoefficients[i] = this.coefficients[i].divide(complexNumbers);
        }
        ComplexNumbers multipliedConstant = this.constant.divide(complexNumbers);
        return new LinearEquation(dividedCoefficients, multipliedConstant);
    }

    ComplexNumbers getCoefficientOfColumn(int columnNumber) {
        return coefficients[columnNumber];
    }

    ComplexNumbers getConstant() {
        return this.constant;
    }

    LinearEquation swapColumns(int columnNumberA, int columnNumberB) {

        ComplexNumbers[] modifiedCoefficients = new ComplexNumbers[coefficients.length];

        for (int i = 0; i < modifiedCoefficients.length; i++) {
            ComplexNumbers afterCoefficient;
            if (i == columnNumberA) {
                afterCoefficient = coefficients[columnNumberB];
            } else if (i == columnNumberB) {
                afterCoefficient = coefficients[columnNumberA];
            } else {
                afterCoefficient = coefficients[i];
            }
            modifiedCoefficients[i] = afterCoefficient;
        }

        return new LinearEquation(modifiedCoefficients, this.constant);
    }

    boolean isSignificant() {
        for (ComplexNumbers coefficient : coefficients) {
            if (!coefficient.equals(ComplexNumbers.ZERO)) {
                return true;
            }
        }
        return false;
    }
}
