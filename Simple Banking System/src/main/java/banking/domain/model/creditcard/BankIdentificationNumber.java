package banking.domain.model.creditcard;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BankIdentificationNumber {

    private final int[] digits = new int[6];

    BankIdentificationNumber() {
        // prefix number is Major Industry Identifier and it is always 4.
        this.digits[0] = 4;
    }

    BankIdentificationNumber(String number) {
        IntStream.range(0, digits.length).forEach(i -> digits[i] = Character.getNumericValue(number.charAt(i)));
    }

    String getId() {
        return Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining());
    }
}
