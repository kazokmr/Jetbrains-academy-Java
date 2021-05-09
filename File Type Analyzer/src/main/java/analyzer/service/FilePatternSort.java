package analyzer.service;

import analyzer.model.FilePattern;

public class FilePatternSort {

    public static void sort(FilePattern[] filePatterns) {
        mergeSort(filePatterns, 0, filePatterns.length);
    }

    private static void mergeSort(FilePattern[] filePatterns, int left, int right) {
        if (right - left <= 1) {
            return;
        }
        int middle = (right - left) / 2 + left;
        mergeSort(filePatterns, left, middle);
        mergeSort(filePatterns, middle, right);
        merge(filePatterns, left, middle, right);
    }

    private static void merge(FilePattern[] filePatterns, int left, int middle, int right) {
        int i = left;
        int j = middle;
        int k = 0;
        FilePattern[] temp = new FilePattern[right - left];
        while (i < middle && j < right) {
            // A higher value means higher priority
            if (filePatterns[i].getPriority() >= filePatterns[j].getPriority()) {
                temp[k] = filePatterns[i];
                i++;
            } else {
                temp[k] = filePatterns[j];
                j++;
            }
            k++;
        }
        System.arraycopy(filePatterns, i, temp, k, middle - i);
        System.arraycopy(filePatterns, j, temp, k, right - j);
        System.arraycopy(temp, 0, filePatterns, left, temp.length);
    }
}
