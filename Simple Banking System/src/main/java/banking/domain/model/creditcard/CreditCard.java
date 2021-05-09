package banking.domain.model.creditcard;

public class CreditCard {
    private final CreditCardNumber number;
    private final CreditCardPinCode pin;

    public CreditCard() {
        this.number = new CreditCardNumber();
        this.pin = new CreditCardPinCode();
    }

    public CreditCard(String cardNumber, String pin) {
        this.number = new CreditCardNumber(cardNumber);
        this.pin = new CreditCardPinCode(pin);
    }

    public boolean isPin(String code) {
        return this.pin.equals(code);
    }

    public String getCardNumber() {
        return number.getCardNumber();
    }

    public String getPinCode() {
        return pin.getPinCode();
    }
}
