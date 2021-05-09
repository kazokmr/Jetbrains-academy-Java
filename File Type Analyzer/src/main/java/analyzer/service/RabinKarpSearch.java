package analyzer.service;

public class RabinKarpSearch {
    private static final int a = 3;
    private static final long m = 1_000_000_000 + 9;
    private final String pattern;
    private final long patternHash;
    private long pow;

    public RabinKarpSearch(String pattern) {
        this.pattern = pattern;
        this.patternHash = hashing(pattern);
    }

    public int indexOf(String text) {
        if (pattern.length() > text.length()) {
            return -1;
        }
        StringBuilder substring = new StringBuilder();
        for (int i = 0; i < pattern.length(); i++) {
            substring.append(text.charAt(text.length() - pattern.length() + i));
        }
        long substringHash = hashing(substring.toString());

        for (int i = text.length(); i >= pattern.length(); i--) {
            if (patternHash == substringHash) {
                boolean patternIsFound = true;
                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i - pattern.length() + j) != pattern.charAt(j)) {
                        patternIsFound = false;
                        break;
                    }
                }
                if (patternIsFound) {
                    return i - pattern.length();
                }
            }
            if (i > pattern.length()) {
                substringHash = (substringHash - (long) text.charAt(i - 1) * pow % m + m) * a % m;
                substringHash = (substringHash + (long) text.charAt(i - pattern.length() - 1)) % m;
            }
        }
        return -1;
    }

    private long hashing(String text) {
        long hash = 0;
        pow = 1;
        for (int i = 0; i < text.length(); i++) {
            hash += (long) text.charAt(i) * pow;
            hash %= m;
            if (i < text.length() - 1) {
                pow = pow * a % m;
            }
        }
        return hash;
    }
}
