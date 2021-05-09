package processor;

import java.util.Scanner;

public class MatrixCalculator {

    public void start() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.println("Your choice:");
        String select = new Scanner(System.in).nextLine();
        System.out.println();
        boolean terminate = false;
        switch (select) {
            case "1":
                addMatrices();
                break;
            case "2":
                multiplyMatrixByConstant();
                break;
            case "3":
                multiplyMatrices();
                break;
            case "4":
                transposeMatrix();
                break;
            case "5":
                calculateDeterminant();
                break;
            case "6":
                invertMatrix();
                break;
            default:
                terminate = true;
                break;
        }
        if (!terminate) {
            start();
        }
    }

    private void addMatrices() {
        Matrix[] matrices = getMultiMatrix();
        System.out.println("The addition result is");
        Matrix result = matrices[0].add(matrices[1]);
        outResultOf(result);
    }

    private void multiplyMatrixByConstant() {
        Matrix matrix = getSingleMatrix();
        System.out.println("Enter constant for multiplication: ");
        Scanner scanner = new Scanner(System.in);
        int constant = Integer.parseInt(scanner.nextLine());
        System.out.println("The multiplication result is");
        Matrix result = matrix.multiply(constant);
        outResultOf(result);
    }

    private void multiplyMatrices() {
        Matrix[] matrices = getMultiMatrix();
        System.out.println("The multiplication result is");
        Matrix result = matrices[0].multiply(matrices[1]);
        outResultOf(result);
    }

    private void transposeMatrix() {

        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.println("Your choice:");
        Scanner scanner = new Scanner(System.in);
        String select = scanner.nextLine();
        Matrix matrix = getSingleMatrix();
        MatrixTransposer mt = new MatrixTransposer(matrix);
        Matrix result;
        switch (select) {
            case "1":
                result = mt.toMainDiagonal();
                break;
            case "2":
                result = mt.toSideDiagonal();
                break;
            case "3":
                result = mt.toVerticalLine();
                break;
            case "4":
                result = mt.toHorizontalLine();
                break;
            default:
                result = null;
                break;
        }
        outResultOf(result);
    }

    private void calculateDeterminant() {
        Matrix matrix = getSingleMatrix();
        DeterminantOfMatrix dom = new DeterminantOfMatrix();
        double result = dom.determine(matrix);
        System.out.println("The result is:");
        System.out.println(result);
    }

    private void invertMatrix() {
        Matrix matrix = getSingleMatrix();
        InverseMatrix im = new InverseMatrix(matrix);
        Matrix result = im.invert();
        outResultOf(result);
    }

    private void outResultOf(Matrix matrix) {
        if (matrix == null) {
            System.out.println("ERROR");
        } else {
            System.out.println("The result is:");
            System.out.println(matrix.toString());
        }
        System.out.println();
    }

    private Matrix getSingleMatrix() {
        System.out.println("Enter matrix size:");
        Matrix matrix = createMatrix();
        System.out.println("Enter matrix:");
        addRowsToMatrix(matrix);
        return matrix;
    }

    private Matrix[] getMultiMatrix() {
        System.out.println("Enter size of first matrix:");
        Matrix matrix1 = createMatrix();
        System.out.println("Enter first matrix:");
        addRowsToMatrix(matrix1);
        System.out.println("Enter size of second matrix:");
        Matrix matrix2 = createMatrix();
        System.out.println("Enter second matrix:");
        addRowsToMatrix(matrix2);
        return new Matrix[]{matrix1, matrix2};
    }

    private Matrix createMatrix() {
        Scanner scanner = new Scanner(System.in);
        int rowCount = scanner.nextInt();
        int columnCount = scanner.nextInt();
        scanner.nextLine();
        return new Matrix(rowCount, columnCount);
    }

    private void addRowsToMatrix(Matrix matrix) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < matrix.getRowCount(); i++) {
            String[] inputArray = scanner.nextLine().split("\\s+");
            double[] numbers = new double[inputArray.length];
            for (int j = 0; j < inputArray.length; j++) {
                numbers[j] = Double.parseDouble(inputArray[j]);
            }
            matrix.addRowByNumbers(numbers);
        }
    }
}
