package analyzer.service;

import java.util.ArrayList;
import java.util.List;

public class KMPSearch {
    private final String pattern;
    private final int[] prefixFunction;

    public KMPSearch(String pattern) {
        this.pattern = pattern;
        this.prefixFunction = prefixFunction(pattern);
    }

    public int indexOf(String text) {
        return indexOf(text, 0);
    }

    private int indexOf(String text, int offset) {
        int j = 0;
        for (int i = offset; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = prefixFunction[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public List<Integer> getOccurrences(String text) {
        List<Integer> occurrences = new ArrayList<>();
        int occurrence = 0;
        do {
            occurrence = indexOf(text, occurrence);
            if (occurrence > -1) {
                occurrences.add(occurrence);
                occurrence++;
            }
        } while (occurrence > 0);
        return occurrences;
    }

    private int[] prefixFunction(String pattern) {
        int[] prefixFunction = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); i++) {
            int j = prefixFunction[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefixFunction[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            prefixFunction[i] = j;
        }
        return prefixFunction;
    }
}
