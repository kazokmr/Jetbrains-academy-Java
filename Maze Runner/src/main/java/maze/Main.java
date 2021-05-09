package maze;

import java.util.Scanner;

public class Main {
    private static Maze maze;

    public static void main(String[] args) {

        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Generate a new maze");
            System.out.println("2. Load a maze");
            if (maze != null) {
                System.out.println("3. Save the maze");
                System.out.println("4. Display the maze");
                System.out.println("5. Find the escape");
            }
            System.out.println("0. Exit");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    generateMaze();
                    break;
                case "2":
                    loadMaze();
                    break;
                case "3":
                    saveMaze();
                    break;
                case "4":
                    displayMaze();
                    break;
                case "5":
                    findEscape();
                    break;
                case "0":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Incorrect option. Please try again");
                    break;
            }
            System.out.println();
        }
    }

    private static void findEscape() {
        if (maze == null) {
            System.out.println("Incorrect option. Please try again");
            return;
        }
        SolveMaze solveMaze = new SolveMaze(maze.getMaze());
        MazePrinter printer = new MazePrinter(solveMaze.getPath());
        printer.printOut();
    }

    private static void generateMaze() {
        System.out.println("Enter the size of a new maze");
        String size = new Scanner(System.in).nextLine();
        maze = new Maze(Integer.parseInt(size), Integer.parseInt(size));
        maze.create();
        maze.printOut();
    }

    private static void loadMaze() {
        System.out.println("Enter the file path");
        String path = new Scanner(System.in).nextLine();
        maze = new MazeFileReader(path).load();
    }

    private static void saveMaze() {
        if (maze == null) {
            System.out.println("Incorrect option. Please try again");
            return;
        }
        System.out.println("Enter the file path");
        String path = new Scanner(System.in).nextLine();
        new MazeFileWriter(path).save(maze.getMaze());
    }

    private static void displayMaze() {
        if (maze == null) {
            System.out.println("Incorrect option. Please try again");
            return;
        }
        maze.printOut();
    }
}
