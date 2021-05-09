package carsharing;

import carsharing.dao.init.TableDefinitions;
import carsharing.view.Menu;

public class Main {

    private static String fileName = "";

    public static void main(String[] args) {
        initDatabase(args);
        new Menu().prompt();
    }

    private static void initDatabase(String[] args) {
        if (args.length >= 2 && args[0].equals("-databaseFileName")) {
            fileName = args[1];
        } else {
            fileName = "default";
        }
        new TableDefinitions().createTable();
    }

    public static String getFileName() {
        return fileName;
    }
}