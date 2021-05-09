package maze;

import java.util.Arrays;
import java.util.Random;

public class Maze {
    private final int[][] maze;
    private final Graph graph;

    public Maze(int rowSize, int colSize) {
        this.maze = new int[rowSize][colSize];
        this.graph = new Graph(rowSize - 2, colSize - 2);
    }

    public Maze(int[][] maze) {
        this.maze = maze;
        this.graph = null;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void create() {
        graph.runMinimumSpanningTree();
        for (int rowIndex = 0; rowIndex < maze.length - 2; rowIndex++) {
            for (int colIndex = 0; colIndex < maze[rowIndex].length - 2; colIndex++) {
                maze[rowIndex + 1][colIndex + 1] = makePath(rowIndex, colIndex) ? 0 : 1;
            }
        }
        makeWall();
        makeEntranceExit();
    }

    public void printOut() {
        MazePrinter printer = new MazePrinter(maze);
        printer.printOut();
    }

    private boolean makePath(int row, int col) {
        // Vertex and Edge
        if (row % 2 == 0) {
            // Vertex is always mst.
            if (col % 2 == 0) {
                return true;
            }
            int edgeRow = row / 2;
            int leftCol = (int) Math.floor((double) col / 2);
            int rightCol = (int) Math.ceil((double) col / 2);
            return graph.isEdgeOfMst(edgeRow, leftCol, edgeRow, rightCol);
        } else {
            // Not Edge is wall
            if (col % 2 != 0) {
                return false;
            }
            int upRow = (int) Math.floor((double) row / 2);
            int downRow = (int) Math.ceil((double) row / 2);
            int edgeCol = col / 2;
            return graph.isEdgeOfMst(upRow, edgeCol, downRow, edgeCol);
        }
    }

    private void makeWall() {
        int[] wall = new int[maze[0].length];
        Arrays.fill(wall, 1);
        maze[0] = wall;
        maze[maze.length - 1] = wall;
        for (int[] row : maze) {
            row[0] = 1;
            row[row.length - 1] = 1;
        }
    }

    private void makeEntranceExit() {
        Random random = new Random();
        int height = maze.length - 2;
        int rowIndex = random.nextInt(height) + 1;
        while (true) {
            if (maze[rowIndex][1] == 0) {
                maze[rowIndex][0] = 0;
                break;
            }
            rowIndex = random.nextInt(height) + 1;
        }
        int mazeColLength = maze[0].length;
        rowIndex = random.nextInt(height) + 1;
        while (true) {
            if (maze[rowIndex][mazeColLength - 2] == 0) {
                maze[rowIndex][mazeColLength - 1] = 0;
                break;
            }
            rowIndex = random.nextInt(height) + 1;
        }
    }
}
