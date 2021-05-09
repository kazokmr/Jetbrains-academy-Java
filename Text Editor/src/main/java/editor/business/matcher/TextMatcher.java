package editor.business.matcher;

import editor.TextPane;

import java.util.LinkedList;
import java.util.List;

public abstract class TextMatcher {

    protected final TextPane textPane;
    protected final String pattern;
    protected final List<MatcherIndex> occurrences;
    protected int currentIndex;

    protected TextMatcher(TextPane textPane, String pattern) {
        this.textPane = textPane;
        this.pattern = pattern;
        this.occurrences = new LinkedList<>();
        this.currentIndex = -1;
    }

    protected abstract void search();

    public void nextMatch() {
        if (occurrences.isEmpty()) {
            return;
        }
        currentIndex = currentIndex + 1 >= occurrences.size() ? currentIndex = 0 : currentIndex + 1;
        occurrences.get(currentIndex).highLightText(textPane);
    }

    public void prevMatch() {
        if (occurrences.isEmpty()) {
            return;
        }
        currentIndex = currentIndex <= 0 ? occurrences.size() - 1 : currentIndex - 1;
        occurrences.get(currentIndex).highLightText(textPane);
    }
}
