package editor.business.matcher;

import javax.swing.SwingWorker;

public class TextSearchTask extends SwingWorker<TextMatcher, Integer> {

    private final TextMatcher textMatcher;

    public TextSearchTask(TextMatcher textMatcher) {
        this.textMatcher = textMatcher;
    }

    @Override
    protected TextMatcher doInBackground() throws Exception {
        textMatcher.search();
        return textMatcher;
    }

    @Override
    protected void done() {
        textMatcher.nextMatch();
    }
}
