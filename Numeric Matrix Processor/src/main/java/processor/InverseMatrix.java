package processor;

public class InverseMatrix {
    private final Matrix matrix;

    public InverseMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Matrix invert() {
        double determinate = new DeterminantOfMatrix().determine(matrix);
        Matrix ct = cofactorMatrixByMainDiagonal();
        return ct.multiply(1 / determinate);
    }

    private Matrix cofactorMatrixByMainDiagonal() {

        Matrix cofactorMatrix = new Matrix(matrix.getRowCount(), matrix.getColCount());
        for (int rowIndex = 0; rowIndex < matrix.getRowCount(); rowIndex++) {
            double[] rowData = new double[matrix.getColCount()];
            for (int colIndex = 0; colIndex < matrix.getColCount(); colIndex++) {
                rowData[colIndex] = calcCofactor(rowIndex, colIndex);
            }
            cofactorMatrix.addRowByNumbers(rowData);
        }
        MatrixTransposer mt = new MatrixTransposer(cofactorMatrix);
        return mt.toMainDiagonal();
    }

    private double calcCofactor(int rowIndex, int colIndex) {
        Matrix subMatrix = matrix.decompose(rowIndex, colIndex);
        double determinate = new DeterminantOfMatrix().determine(subMatrix);
        return determinate * Math.pow(-1, (rowIndex + colIndex));
    }
}
