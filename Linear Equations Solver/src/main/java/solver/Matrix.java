package solver;

import java.util.List;

public class Matrix {

    private final int numberOfVariables;
    private final List<Row> rows;
    private final StringBuilder solution;

    public Matrix(int numberOfVariables, List<Row> rows) {
        this.numberOfVariables = numberOfVariables;
        this.rows = rows;
        this.solution = new StringBuilder();
    }

    public void solve() {
        System.out.println("Start solving the equation.");
        manipulateRows();
        firstAlgorithm();
        if (hasNoSolutions()) {
            return;
        }
        if (hasManySolution()) {
            return;
        }
        secondAlgorithm();
        printOutSolution();
    }

    private void manipulateRows() {
        System.out.println("Rows manipulation:");
        for (int columnNumber = 0; columnNumber < numberOfVariables; columnNumber++) {
            if (columnNumber >= rows.size()) {
                return;
            }
            Row currentRow = rows.get(columnNumber);
            if (currentRow.isCoefficientNonZero(columnNumber)) {
                continue;
            }
            int rowNumber = currentRow.getNumber();
            int swappableRowNumber = getSwappableRowNumber(currentRow);
            if (swappableRowNumber >= 0) {
                swapRows(rowNumber, swappableRowNumber);
                continue;
            }
            int swappableColumnNumber = getSwappableColumnNumber(currentRow, columnNumber);
            if (swappableColumnNumber >= 0) {
                swapColumnsInAllRows(columnNumber, swappableColumnNumber);
                continue;
            }
            swapRowAndColumn(rowNumber, columnNumber);
        }
    }

    private void firstAlgorithm() {
        for (int rowNumber = 0; rowNumber < numberOfVariables; rowNumber++) {
            if (rowNumber >= rows.size()) {
                return;
            }
            Row baseRow = rows.get(rowNumber);
            Row modifiedRow = baseRow.makeLeadingEntryOne(rowNumber);
            rows.set(baseRow.getNumber(), modifiedRow);
            makeColumnOfUpperRowZero(rowNumber + 1, modifiedRow);
        }
    }

    private void secondAlgorithm() {
        for (int rowNumber = numberOfVariables - 1; rowNumber >= 0; rowNumber--) {
            makeColumnOfLowerRowZero(rowNumber - 1, rows.get(rowNumber));
        }
    }

    private int getSwappableRowNumber(Row currentRow) {
        int startIndex = currentRow.getNumber() + 1;
        for (int rowNumber = startIndex; rowNumber < rows.size(); rowNumber++) {
            Row row = rows.get(rowNumber);
            if (row.isCoefficientNonZero(currentRow.getNumber())) {
                return rowNumber;
            }
        }
        return -1;
    }

    private int getSwappableColumnNumber(Row currentRow, int currentColumnNumber) {
        for (int columnNumber = currentColumnNumber + 1; columnNumber < numberOfVariables; columnNumber++) {
            if (currentRow.isCoefficientNonZero(columnNumber)) {
                return columnNumber;
            }
        }
        return -1;
    }

    private void swapRows(int rowNumberA, int rowNumberB) {
        Row rowA = rows.get(rowNumberA);
        Row rowB = rows.get(rowNumberB);
        System.out.println(rowA.getName() + " <-> " + rowB.getName());
        rows.set(rowNumberA, rowB.swapRow(rowNumberA));
        rows.set(rowNumberB, rowA.swapRow(rowNumberB));
    }

    private void swapColumnsInAllRows(int columnNumberA, int columnNumberB) {
        for (int i = 0; i < rows.size(); i++) {
            Row row = rows.get(i).swapColumns(columnNumberA, columnNumberB);
            rows.set(i, row);
        }
    }

    private void swapRowAndColumn(int curRowNumber, int curColumnNumber) {
        for (int rowNumber = curRowNumber + 1; rowNumber < rows.size(); rowNumber++) {
            Row row = rows.get(rowNumber);
            for (int columnNumber = curColumnNumber + 1; columnNumber < numberOfVariables; columnNumber++) {
                if (row.isCoefficientNonZero(columnNumber)) {
                    swapRows(curRowNumber, rowNumber);
                    swapColumnsInAllRows(curColumnNumber, columnNumber);
                    return;
                }
            }
        }
    }

    private void makeColumnOfUpperRowZero(int startRowNumber, Row baseRow) {
        for (int rowNumber = startRowNumber; rowNumber < rows.size(); rowNumber++) {
            Row row = rows.get(rowNumber).makeColumnZero(baseRow);
            rows.set(rowNumber, row);
        }
    }

    private void makeColumnOfLowerRowZero(int startRowNumber, Row baseRow) {
        for (int rowNumber = startRowNumber; rowNumber >= 0; rowNumber--) {
            Row row = rows.get(rowNumber).makeColumnZero(baseRow);
            rows.set(rowNumber, row);
        }
    }

    private boolean hasNoSolutions() {
        for (Row row : rows) {
            if (row.isContradiction()) {
                System.out.println("No solutions");
                solution.append("No solutions");
                return true;
            }
        }
        return false;
    }

    private boolean hasManySolution() {
        int numOfSignificantEquations = 0;
        for (Row row : rows) {
            if (row.isSignificantEquation()) {
                numOfSignificantEquations++;
            }
        }
        if (numOfSignificantEquations >= numberOfVariables) {
            return false;
        }
        System.out.println("Infinitely many solutions");
        solution.append("Infinitely many solutions");
        return true;
    }

    private void printOutSolution() {
        StringBuilder printOut = new StringBuilder("The solution is: (");
        for (int rowNumber = 0; rowNumber < numberOfVariables; rowNumber++) {
            Row row = rows.get(rowNumber);
            printOut.append(row.getResult()).append(", ");
            solution.append(row.getResult().toString()).append("\n");
        }
        printOut.append(")");
        System.out.println(printOut.toString().replaceAll(", \\)", ")"));
    }

    public String getSolution() {
        return solution.toString();
    }
}
