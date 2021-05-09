package processor;

import java.util.Arrays;

public class Row {

    private final double[] values;

    Row(double[] values) {
        this.values = values;
    }

    double valueOf(int index) {
        return values[index];
    }

    Row add(Row added) {
        double[] results = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = values[i] + added.values[i];
        }
        return new Row(results);
    }

    Row multiply(double constant) {
        double[] results = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = constant * values[i];
        }
        return new Row(results);
    }

    double multiply(double[] columnValues) {
        double result = 0;
        for (int i = 0; i < values.length; i++) {
            result += values[i] * columnValues[i];
        }
        return result;
    }

    Row inverse() {
        double[] inverse = new double[values.length];
        for (int i = values.length - 1; i >= 0; i--) {
            inverse[values.length - 1 - i] = values[i];
        }
        return new Row(inverse);
    }

    @Override
    public String toString() {
        return Arrays.toString(values)
                .replaceAll(",", "")
                .replaceAll("\\[", "")
                .replaceAll("]", "");
    }
}
