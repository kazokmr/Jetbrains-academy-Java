package editor.business.matcher;

import editor.TextPane;

import java.util.Collections;

public class PlainTextMatcher extends TextMatcher {

    public PlainTextMatcher(TextPane textPane, String pattern) {
        super(textPane, pattern);
    }

    @Override
    public void search() {
        rabinKarp(textPane.getText(), pattern);
    }

    private void rabinKarp(String text, String pattern) {

        int a = 53;
        long m = 1_000_000_000 + 9;

        long patternHash = 0;
        long currSubstrHash = 0;
        long pow = 1;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash += charToLong(pattern.charAt(i)) * pow;
            patternHash %= m;

            currSubstrHash += charToLong(text.charAt(text.length() - pattern.length() + i)) * pow;
            currSubstrHash %= m;

            if (i != pattern.length() - 1) {
                pow = pow * a % m;
            }
        }

        for (int i = text.length(); i >= pattern.length(); i--) {
            if (patternHash == currSubstrHash) {
                boolean patternIsFound = true;

                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i - pattern.length() + j) != pattern.charAt(j)) {
                        patternIsFound = false;
                        break;
                    }
                }
                if (patternIsFound) {
                    occurrences.add(new MatcherIndex(i - pattern.length(), i));
                }
            }
            if (i > pattern.length()) {
                currSubstrHash = (currSubstrHash - charToLong(text.charAt(i - 1)) * pow % m + m) * a % m;
                currSubstrHash = (currSubstrHash + charToLong(text.charAt(i - pattern.length() - 1))) % m;
            }
        }
        Collections.reverse(occurrences);
    }

    private long charToLong(char ch) {
        return ch - 'A' + 1;
    }
}
