package processor;

public class Matrix {

    private final int rowCount;
    private final int colCount;
    private final Row[] rows;

    public Matrix(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.rows = new Row[rowCount];
    }

    int getRowCount() {
        return rowCount;
    }

    int getColCount() {
        return colCount;
    }

    double getValue(int rowNumber, int colNumber) {
        return rows[rowNumber].valueOf(colNumber);
    }

    void addRowByNumbers(double[] numbers) {
        addRow(new Row(numbers));
    }

    void addRow(Row row) {
        for (int index = 0; index < rows.length; index++) {
            if (rows[index] == null) {
                rows[index] = row;
                return;
            }
        }
    }

    Row getRowByIndex(int indexOfRows) {
        return rows[indexOfRows];
    }

    double[] getColumnByIndex(int indexOfColumn) {
        double[] columnValues = new double[rows.length];
        for (int i = 0; i < rows.length; i++) {
            columnValues[i] = getValue(i, indexOfColumn);
        }
        return columnValues;
    }

    Matrix add(Matrix added) {
        if (rowCount != added.rowCount || colCount != added.colCount) {
            return null;
        }
        Matrix result = new Matrix(rowCount, colCount);
        for (int i = 0; i < rowCount; i++) {
            result.addRow(rows[i].add(added.rows[i]));
        }
        return result;
    }

    Matrix multiply(double constant) {
        Matrix result = new Matrix(rowCount, colCount);
        for (int i = 0; i < rowCount; i++) {
            result.addRow(rows[i].multiply(constant));
        }
        return result;
    }

    Matrix multiply(Matrix mc) {
        if (colCount != mc.rowCount) {
            return null;
        }
        Matrix result = new Matrix(rowCount, mc.colCount);
        for (int indexOfRow = 0; indexOfRow < rowCount; indexOfRow++) {
            double[] valueOfRow = new double[mc.colCount];
            Row row = rows[indexOfRow];
            for (int indexOfColumn = 0; indexOfColumn < mc.colCount; indexOfColumn++) {
                valueOfRow[indexOfColumn] = row.multiply(mc.getColumnByIndex(indexOfColumn));
            }
            result.addRow(new Row(valueOfRow));
        }
        return result;
    }

    Matrix decompose(int rowNum, int colNum) {
        Matrix subMatrix = new Matrix(rowCount - 1, colCount - 1);
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            if (rowIndex == rowNum) {
                continue;
            }
            double[] rowData = new double[colCount - 1];
            for (int colIndex = 0; colIndex < colCount; colIndex++) {
                if (colIndex == colNum) {
                    continue;
                }
                int subIndex = colIndex >= colNum ? colIndex - 1 : colIndex;
                rowData[subIndex] = getValue(rowIndex, colIndex);
            }
            subMatrix.addRowByNumbers(rowData);
        }
        return subMatrix;
    }

    @Override
    public String toString() {
        StringBuilder matrix = new StringBuilder();
        for (Row row : rows) {
            matrix.append(row.toString()).append("\n");
        }
        return matrix.toString().replaceAll("\\n$", "");
    }
}
