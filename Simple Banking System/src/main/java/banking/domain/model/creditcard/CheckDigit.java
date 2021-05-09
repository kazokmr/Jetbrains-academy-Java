package banking.domain.model.creditcard;

public class CheckDigit {
    private final int digit;

    CheckDigit(String cardNumber) {
        this.digit = LuhnAlgorithm.getDigit(cardNumber);
    }

    CheckDigit(int digit) {
        this.digit = digit;
    }

    int getDigit() {
        return digit;
    }
}
