package correcter;

import java.util.Random;

public class ErrorEmulator {

    private final String inputMessage;

    public ErrorEmulator(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    void createErrorMessage() {
        System.out.println(inputMessage);
        String encode = encodeMessage();
        modifyErrorText(encode);
        System.out.println(inputMessage);
    }

    private String encodeMessage() {
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < inputMessage.length(); i++) {
            char c = inputMessage.charAt(i);
            encodedText.append(String.valueOf(c).repeat(3));
        }
        System.out.println(encodedText.toString());
        return encodedText.toString();
    }


    private void modifyErrorText(String encoded) {
        StringBuilder errorText = new StringBuilder();
        for (int index = 0; index < encoded.length(); index += 3) {
            String subText = encoded.substring(index, Math.min(index + 3, encoded.length()));
            String errorSubText = makeErrorText(subText);
            errorText.append(errorSubText);
        }
        System.out.println(errorText.toString());
    }

    private String makeErrorText(String subtext) {
        char[] symbols = subtext.toCharArray();
        Random random = new Random();
        int index = random.nextInt(symbols.length);
        char changeCharacter = symbols[index];

        Character errorChar = null;
        while (errorChar == null || errorChar.equals(changeCharacter)) {
            errorChar = getCharacter();
        }
        symbols[index] = errorChar;
        return String.valueOf(symbols);
    }

    private Character getCharacter() {
        final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 0123456789";
        Random random = new Random();
        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }
}
