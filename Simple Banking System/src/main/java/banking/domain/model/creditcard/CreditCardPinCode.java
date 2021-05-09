package banking.domain.model.creditcard;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreditCardPinCode {
    private final int[] digits = new int[4];

    CreditCardPinCode() {
        IntStream.range(0, digits.length).forEach(i -> digits[i] = new Random().nextInt(10));
    }

    CreditCardPinCode(String pin) {
        IntStream.range(0, digits.length).forEach(i -> digits[i] = Character.getNumericValue(pin.charAt(i)));
    }

    boolean equals(String code) {
        int[] numbers = Arrays.stream(code.split("")).mapToInt(Integer::parseInt).toArray();
        return Arrays.equals(numbers, digits);
    }

    String getPinCode() {
        return Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining());
    }
}
