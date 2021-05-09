package editor.business.matcher;

import editor.TextPane;

public class MatcherIndex {
    private final int start;
    private final int end;

    public MatcherIndex(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void highLightText(TextPane textPane) {
        textPane.highlightMatchWord(start, end);
    }
}
