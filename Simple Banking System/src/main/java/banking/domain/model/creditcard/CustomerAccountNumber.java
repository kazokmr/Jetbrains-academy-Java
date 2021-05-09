package banking.domain.model.creditcard;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomerAccountNumber {
    private final int[] digits = new int[9];

    CustomerAccountNumber() {
        IntStream.range(0, digits.length).forEach(i -> digits[i] = new Random().nextInt(10));
    }

    CustomerAccountNumber(String number) {
        IntStream.range(0, digits.length).forEach(i -> digits[i] = Character.getNumericValue(number.charAt(i)));
    }

    String getId() {
        return Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining());
    }
}
