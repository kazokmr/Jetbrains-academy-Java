package editor.business.matcher;

import editor.TextPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpMatcher extends TextMatcher {

    public RegExpMatcher(TextPane textPane, String pattern) {
        super(textPane, pattern);
    }

    @Override
    public void search() {
        Matcher matcher = Pattern.compile(pattern).matcher(textPane.getText());
        while (matcher.find()) {
            occurrences.add(new MatcherIndex(matcher.start(), matcher.end()));
        }
    }
}
