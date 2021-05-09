package animals.game.message;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import static animals.game.i18n.AppResource.getMessage;
import static animals.game.i18n.AppResource.getPattern;

public class Confirmation {

    private static final String[] PHRASE_ARRAY = getMessage("ask.again").split("\\f");

    public boolean is(String message) {
        return response(message);
    }

    private boolean response(String message) {
        System.out.println(message);
        String response = new Scanner(System.in).nextLine().trim();

        Pattern patYes = Pattern.compile(getPattern("positiveAnswer.isCorrect"), Pattern.CASE_INSENSITIVE);
        if (patYes.matcher(response).matches()) {
            return true;
        }

        Pattern patNo = Pattern.compile(getPattern("negativeAnswer.isCorrect"), Pattern.CASE_INSENSITIVE);
        if (patNo.matcher(response).matches()) {
            return false;
        }

        System.out.println(askAgain());
        return response(message);
    }

    private String askAgain() {
        return PHRASE_ARRAY[new Random().nextInt(PHRASE_ARRAY.length)];
    }

}
