package editor.business.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TextFileReader {

    private final File file;

    public TextFileReader(File file) {
        this.file = file;
    }

    public String read() {
        if (!file.exists()) {
            return "";
        }
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
