package animals.game.message;

import java.util.Random;

import static animals.game.i18n.AppResource.getMessage;

public class Goodbyes {
    private static final String[] WORD_ARRAY = getMessage("farewell").split("\\f");

    public String say() {
        return WORD_ARRAY[new Random().nextInt(WORD_ARRAY.length)];
    }
}
