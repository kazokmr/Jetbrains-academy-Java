package solver;

public class Row {
    private final int number;
    private final LinearEquation linearEquation;

    public Row(int number, LinearEquation linearEquation) {
        this.number = number;
        this.linearEquation = linearEquation;
    }

    Row(int number, String equation) {
        this.number = number;
        this.linearEquation = new LinearEquation(equation);
    }

    Row makeLeadingEntryOne(int columnNumber) {
        ComplexNumbers complexNumbers = linearEquation.getCoefficientOfColumn(columnNumber);
        if (complexNumbers.equals(ComplexNumbers.ONE) || complexNumbers.equals(ComplexNumbers.ZERO)) {
            return this;
        }
        System.out.println(getName() + " / " + complexNumbers + " -> " + getName());
        LinearEquation calculatedLinearEquation = linearEquation.divide(complexNumbers);
        return new Row(number, calculatedLinearEquation);
    }

    Row makeColumnZero(Row baseRow) {
        ComplexNumbers coefficient = linearEquation.getCoefficientOfColumn(baseRow.number);
        if (coefficient.equals(ComplexNumbers.ZERO)) {
            return this;
        }
        ComplexNumbers coefficientByZero = coefficient.negate();
        System.out.println(coefficientByZero + " * " + baseRow.getName() + " + " + getName() + " -> " + getName());
        LinearEquation baseLinearEquation = baseRow.linearEquation.multiple(coefficientByZero);
        LinearEquation linearEquationBeColumnZero = linearEquation.add(baseLinearEquation);
        return new Row(this.number, linearEquationBeColumnZero);
    }

    Row swapRow(int swapRowNumber) {
        return new Row(swapRowNumber, this.linearEquation);
    }

    Row swapColumns(int columnNumberA, int columnNumberB) {
        LinearEquation modifiedLinearEquation = linearEquation.swapColumns(columnNumberA, columnNumberB);
        return new Row(number, modifiedLinearEquation);
    }

    ComplexNumbers getResult() {
        return linearEquation.getConstant();
    }

    int getNumber() {
        return number;
    }

    boolean isCoefficientNonZero(int columnNumber) {
        return !linearEquation.getCoefficientOfColumn(columnNumber).equals(ComplexNumbers.ZERO);
    }

    boolean isContradiction() {
        return !linearEquation.isSignificant() && !linearEquation.getConstant().equals(ComplexNumbers.ZERO);
    }

    boolean isSignificantEquation() {
        return linearEquation.isSignificant();
    }

    String getName() {
        return String.format("R%d", number + 1);
    }

    @Override
    public String toString() {
        return getName() + " : " + linearEquation.toString();
    }
}
