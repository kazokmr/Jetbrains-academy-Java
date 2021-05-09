package analyzer;

import analyzer.model.FileWrapper;
import analyzer.service.FileTypeAnalyzer;
import analyzer.service.KMPSearch;

import java.io.File;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
//        searchFileType(args);
//        new FileTypeAnalyzer(args[0],args[1], args[2]).analyze();
        new FileTypeAnalyzer(args[0], args[1]).analyze();
    }

    private static void searchFileType(String[] args) {
        String searchType = args[0];
        String text = new FileWrapper(new File(args[1])).readText();
        String pattern = args[2];
        String correctFileType = args[3];

        long startTime = System.nanoTime();
        boolean fileTypeIsCorrect;
        switch (searchType) {
            case "--KMP":
                fileTypeIsCorrect = new KMPSearch(pattern).indexOf(text) >= 0;
                break;
            case "--naive":
                fileTypeIsCorrect = text.contains(pattern);
                break;
            default:
                fileTypeIsCorrect = false;
        }
        System.out.println(fileTypeIsCorrect ? correctFileType : "Unknown file type");
        LocalTime localTime = LocalTime.ofNanoOfDay(System.nanoTime() - startTime);
        System.out.printf("It took %d.%d seconds\n", localTime.getSecond(), localTime.getNano());
    }
}
