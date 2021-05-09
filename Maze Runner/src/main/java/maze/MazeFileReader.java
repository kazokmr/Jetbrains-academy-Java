package maze;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MazeFileReader {
    private final File file;

    public MazeFileReader(String filePath) {
        this.file = new File(filePath);
    }

    public Maze load() {
        if (!file.exists()) {
            System.out.println("The file ... does not exist");
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String[] size = reader.readLine().split("\\s+");
            int[][] maze = new int[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
            for (int rowIndex = 0; rowIndex < maze.length; rowIndex++) {
                String[] data = reader.readLine().split("\\s+");
                for (int colIndex = 0; colIndex < data.length; colIndex++) {
                    int binary = Integer.parseInt(data[colIndex]);
                    if (binary != 0 && binary != 1) {
                        System.out.println("Cannot load the maze. It has an invalid format");
                        return null;
                    }
                    maze[rowIndex][colIndex] = binary;
                }
            }
            return new Maze(maze);
        } catch (Exception e) {
            System.out.println("Cannot load the maze. It has an invalid format");
            e.printStackTrace();
        }
        return null;
    }
}
