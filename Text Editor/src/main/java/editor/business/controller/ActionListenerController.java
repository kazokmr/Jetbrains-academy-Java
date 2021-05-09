package editor.business.controller;

import editor.TextEditor;
import editor.business.matcher.PlainTextMatcher;
import editor.business.matcher.RegExpMatcher;
import editor.business.matcher.TextMatcher;
import editor.business.matcher.TextSearchTask;

import java.util.concurrent.ExecutionException;

public class ActionListenerController {

    private final TextEditor textEditor;

    public ActionListenerController(TextEditor textEditor) {
        this.textEditor = textEditor;
    }

    public void openFile() {
        textEditor.openFile();
    }

    public void saveFile() {
        textEditor.saveFile();
    }

    public void closeFrame() {
        textEditor.dispose();
    }

    public void startSearch() {
        TextMatcher textMatcher = textEditor.isRegexp() ?
                new RegExpMatcher(textEditor.getTextPane(), textEditor.getPattern()) :
                new PlainTextMatcher(textEditor.getTextPane(), textEditor.getPattern());
        TextSearchTask task = new TextSearchTask(textMatcher);
        task.execute();
        try {
            textEditor.setTextMather(task.get());
        } catch (InterruptedException | ExecutionException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void nextMatch() {
        TextMatcher textMatcher = textEditor.getTextMatcher();
        if (textMatcher != null) {
            textMatcher.nextMatch();
        }
    }

    public void prevMath() {
        TextMatcher textMatcher = textEditor.getTextMatcher();
        if (textMatcher != null) {
            textMatcher.prevMatch();
        }
    }

    public void setUseRegExp() {
        textEditor.setUseRegExp();
    }
}
