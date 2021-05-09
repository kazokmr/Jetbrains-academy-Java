package banking.domain.model.creditcard;

public class CreditCardNumber {
    private final BankIdentificationNumber bankId;
    private final CustomerAccountNumber customerId;
    private final CheckDigit checkDigit;

    CreditCardNumber() {
        this.bankId = new BankIdentificationNumber();
        this.customerId = new CustomerAccountNumber();
        this.checkDigit = new CheckDigit(String.format("%s%s", bankId.getId(), customerId.getId()));
    }

    CreditCardNumber(String cardNumber) {
        this.bankId = new BankIdentificationNumber(cardNumber.substring(0, 6));
        this.customerId = new CustomerAccountNumber(cardNumber.substring(6, 15));
        this.checkDigit = new CheckDigit(Integer.parseInt(cardNumber.substring(15)));
    }

    String getCardNumber() {
        return String.format("%s%s%d", bankId.getId(), customerId.getId(), checkDigit.getDigit());
    }
}
