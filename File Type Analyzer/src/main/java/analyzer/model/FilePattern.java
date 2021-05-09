package analyzer.model;

import analyzer.service.RabinKarpSearch;

public class FilePattern {
    private final int priority;
    //    private final KMPSearch pattern;
    private final RabinKarpSearch pattern;
    private final String fileType;

    public FilePattern(int priority, String pattern, String fileType) {
        this.priority = priority;
        this.pattern = new RabinKarpSearch(pattern.replace("\"", ""));
//        this.pattern = new KMPSearch(pattern.replace("\"", ""));
        this.fileType = fileType.replace("\"", "");
    }

    public FilePattern(String line) {
        String[] array = line.split(";");
        this.priority = Integer.parseInt(array[0]);
        this.pattern = new RabinKarpSearch(array[1].replace("\"", ""));
//        this.pattern = new KMPSearch(array[1].replace("\"", ""));
        this.fileType = array[2].replace("\"", "");
    }

    public int getPriority() {
        return priority;
    }

    public boolean doesTextOfFileFind(String text) {
        return pattern.indexOf(text) >= 0;
    }

    public String getFileType() {
        return fileType;
    }
}
