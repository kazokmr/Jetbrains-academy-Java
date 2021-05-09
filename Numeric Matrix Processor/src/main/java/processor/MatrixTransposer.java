package processor;

public class MatrixTransposer {
    private final Matrix matrix;

    public MatrixTransposer(Matrix matrix) {
        this.matrix = matrix;
    }

    Matrix toMainDiagonal() {
        Matrix tm = makeMatrixForTranspose(true);
        for (int rowIndex = 0; rowIndex < matrix.getColCount(); rowIndex++) {
            double[] tr = matrix.getColumnByIndex(rowIndex);
            tm.addRowByNumbers(tr);
        }
        return tm;
    }

    Matrix toSideDiagonal() {
        Matrix tm = makeMatrixForTranspose(true);
        for (int rowIndex = 0; rowIndex < matrix.getColCount(); rowIndex++) {
            double[] columns = matrix.getColumnByIndex(matrix.getColCount() - 1 - rowIndex);
            double[] tr = new double[columns.length];
            for (int colIndex = 0; colIndex < columns.length; colIndex++) {
                tr[columns.length - 1 - colIndex] = columns[colIndex];
            }
            tm.addRowByNumbers(tr);
        }
        return tm;
    }

    Matrix toVerticalLine() {
        Matrix tm = makeMatrixForTranspose(false);
        for (int rowIndex = 0; rowIndex < matrix.getRowCount(); rowIndex++) {
            tm.addRow(matrix.getRowByIndex(rowIndex).inverse());
        }
        return tm;
    }

    Matrix toHorizontalLine() {
        Matrix tm = makeMatrixForTranspose(false);
        for (int rowIndex = 0; rowIndex < matrix.getRowCount(); rowIndex++) {
            int newIndex = matrix.getRowCount() - 1 - rowIndex;
            tm.addRow(matrix.getRowByIndex(newIndex));
        }
        return tm;
    }

    private Matrix makeMatrixForTranspose(boolean isInverse) {
        int numOfRows = isInverse ? matrix.getColCount() : matrix.getRowCount();
        int numOfCols = isInverse ? matrix.getRowCount() : matrix.getColCount();
        return new Matrix(numOfRows, numOfCols);
    }
}
