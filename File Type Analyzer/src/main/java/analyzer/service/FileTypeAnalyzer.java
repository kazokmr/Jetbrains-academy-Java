package analyzer.service;

import analyzer.model.FilePattern;
import analyzer.model.FileWrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileTypeAnalyzer {
    private final FilePattern[] filePatterns;
    private final File[] targetFiles;

    public FileTypeAnalyzer(String targetDirPath, String dbFilePath) {
        this.filePatterns = readDatabaseFile(dbFilePath);
        this.targetFiles = new File(targetDirPath).listFiles();
    }

    public FileTypeAnalyzer(String targetDirPath, String pattern, String fileType) {
        this.filePatterns = new FilePattern[]{new FilePattern(0, pattern, fileType)};
        this.targetFiles = new File(targetDirPath).listFiles();
    }

    public void analyze() {
        if (targetFiles == null) {
            return;
        }
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            for (File file : targetFiles) {
                executor.submit(() -> searchFileTypeByPriority(filePatterns, file));
            }
            executor.shutdown();
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchFileTypeByPriority(FilePattern[] filePatterns, File file) {
        FileWrapper fileWrapper = new FileWrapper(file);
        Arrays.stream(filePatterns)
                .parallel()
                .filter(pattern -> pattern.doesTextOfFileFind(fileWrapper.readText()))
                .findFirst()
                .ifPresentOrElse(
                        filePattern -> System.out.printf("%s: %s\n", fileWrapper.getName(), filePattern.getFileType()),
                        () -> System.out.printf("%s: Unknown file type\n", fileWrapper.getName())
                );
    }

    private FilePattern[] readDatabaseFile(String dbFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFile))) {
            List<FilePattern> list = new LinkedList<>();
            String line = reader.readLine();
            while (line != null) {
                list.add(new FilePattern(line));
                line = reader.readLine();
            }
            FilePattern[] filePatterns = list.toArray(FilePattern[]::new);
            FilePatternSort.sort(filePatterns);
            return filePatterns;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
