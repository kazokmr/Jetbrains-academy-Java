package banking.domain.model.creditcard;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LuhnAlgorithm {

    public static int getDigit(String cardNumber) {
        int[] originalNumbers = getOriginalNumbers(cardNumber);
        int sum = IntStream.rangeClosed(1, originalNumbers.length)
                .map(index -> index % 2 != 0 ? originalNumbers[index - 1] * 2 : originalNumbers[index - 1])
                .map(number -> number > 9 ? number - 9 : number)
                .sum();
        return sum % 10 != 0 ? 10 - sum % 10 : 0;
    }

    public static boolean isCorrectCardNumber(String cardNumber) {
        int checkDigit = Integer.parseInt(cardNumber.substring(cardNumber.length() - 1));
        return getDigit(cardNumber) == checkDigit;
    }

    private static int[] getOriginalNumbers(String cardNumber) {
        return Arrays.stream(cardNumber.substring(0, 15).split("")).mapToInt(Integer::parseInt).toArray();
    }
}
