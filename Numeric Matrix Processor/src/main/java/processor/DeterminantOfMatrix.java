package processor;

public class DeterminantOfMatrix {

    double determine(Matrix matrix) {

        if (matrix == null || matrix.getColCount() == 0) {
            return 0d;
        }
        if (matrix.getColCount() == 1) {
            return matrix.getValue(0, 0);
        }
        if (matrix.getColCount() == 2) {
            return determineTwoOrder(matrix);
        }

        double determinate = 0d;

        for (int colIndex = 0; colIndex < matrix.getColCount(); colIndex++) {
            double coefficient = matrix.getValue(0, colIndex) * Math.pow(-1, colIndex);
            Matrix subMatrix = matrix.decompose(0, colIndex);
            determinate += coefficient * determine(subMatrix);
        }
        return determinate;
    }

    private double determineTwoOrder(Matrix matrix) {
        double mainDiagonal = matrix.getValue(0, 0) * matrix.getValue(1, 1);
        double sideDiagonal = matrix.getValue(1, 0) * matrix.getValue(0, 1);
        return mainDiagonal - sideDiagonal;
    }
}
