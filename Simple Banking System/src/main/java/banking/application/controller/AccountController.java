package banking.application.controller;

import banking.application.service.AccountService;
import banking.domain.model.Account;
import banking.domain.model.creditcard.CreditCard;

import java.util.Optional;

public class AccountController {

    private static final AccountController instance = new AccountController();
    private final AccountService service = new AccountService();

    public static AccountController getInstance() {
        return instance;
    }

    public CreditCard execute() {
        return service.createAccount();
    }

    public Optional<Account> getAccount(String cardNumber, String pin) {
        Optional<Account> optionalAccount = service.findByCardNumber(cardNumber);
        if (optionalAccount.isEmpty()) {
            return Optional.empty();
        }
        return optionalAccount.get().isPin(pin) ? optionalAccount : Optional.empty();
    }

    public Optional<Account> findByCardNumber(String cardNumber) {
        return service.findByCardNumber(cardNumber);
    }

    public void updateIncome(Account account) {
        service.update(account);
    }

    public void transfer(Account from, Account to) {
        service.updateTransfer(from, to);
    }

    public void closeAccount(Account account) {
        service.delete(account);
    }
}
