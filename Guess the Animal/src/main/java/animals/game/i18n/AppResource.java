package animals.game.i18n;

import java.util.ResourceBundle;

public class AppResource {

    private static final ResourceBundle messageBundle = ResourceBundle.getBundle("messages");
    private static final ResourceBundle patternsBundle = ResourceBundle.getBundle("patterns");

    public static String getMessage(String key) {
        return messageBundle.getString(key);
    }

    public static String getPattern(String key) {
        return patternsBundle.getString(key);
    }
}
