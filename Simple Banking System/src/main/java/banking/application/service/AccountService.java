package banking.application.service;

import banking.data.repository.AccountRepository;
import banking.domain.model.Account;
import banking.domain.model.creditcard.CreditCard;

import java.util.Optional;

public class AccountService {

    private final AccountRepository repository = new AccountRepository();

    public CreditCard createAccount() {
        Account account = new Account();
        repository.save(account);
        return account.getCreditCard();
    }

    public Optional<Account> findByCardNumber(String cardNumber) {
        return repository.findByCardNumber(cardNumber);
    }

    public void update(Account account) {
        repository.update(account);
    }

    public void updateTransfer(Account from, Account to) {
        repository.updateTransfer(from, to);
    }

    public void delete(Account account) {
        repository.delete(account);
    }
}
