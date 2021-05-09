package solver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File(args[1]));
//            Scanner scanner = new Scanner(System.in);
            String[] definitions = scanner.nextLine().split("\\s+");
            int numberOfVariables = Integer.parseInt(definitions[0]);
            int numberOfEquations = Integer.parseInt(definitions[1]);
            List<Row> rows = new ArrayList<>(numberOfEquations);
            for (int i = 0; i < numberOfEquations; i++) {
                String equation = scanner.nextLine();
                Row row = new Row(i, equation);
                rows.add(row);
            }
            Matrix matrix = new Matrix(numberOfVariables, rows);
            matrix.solve();
            String fileName = args[3];
//            String fileName = "out.txt";
            PrintWriter writer = new PrintWriter(new File(fileName));
            writer.println(matrix.getSolution());
            writer.close();
            System.out.println("Saved to file " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
