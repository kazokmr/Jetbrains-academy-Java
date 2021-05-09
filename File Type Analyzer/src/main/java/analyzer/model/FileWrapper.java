package analyzer.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileWrapper {
    private final File file;

    public FileWrapper(File file) {
        this.file = file;
    }

    public String getName() {
        return file.getName();
    }

    public String readText() {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            return new String(bis.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
