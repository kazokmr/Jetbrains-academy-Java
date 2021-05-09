package maze;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MazeFileWriter {
    private final File file;

    public MazeFileWriter(String filePath) {
        this.file = new File(filePath);
    }

    public void save(int[][] maze) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(String.format("%d %d", maze.length, maze[0].length));
            writer.newLine();
            for (int[] row : maze) {
                for (int binary : row) {
                    writer.write(binary + " ");
                }
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
