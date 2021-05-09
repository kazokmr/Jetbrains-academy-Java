package banking.domain.model;

import banking.domain.model.creditcard.CreditCard;

public class Account {
    private final CreditCard creditCard;
    private Long balance;

    public Account() {
        this.creditCard = new CreditCard();
        this.balance = 0L;
    }

    public Account(String cardNumber, String pin, Long balance) {
        this.creditCard = new CreditCard(cardNumber, pin);
        this.balance = balance;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public String getCardNumber() {
        return creditCard.getCardNumber();
    }

    public String getPin() {
        return creditCard.getPinCode();
    }

    public boolean isPin(String code) {
        return creditCard.isPin(code);
    }

    public long getBalance() {
        return balance;
    }

    public void addIncome(Long income) {
        balance += income;
    }

    public void transferIncome(Long income) {
        balance -= income;
    }
}
