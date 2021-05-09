package blockchain;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VcUsers {
    private static final VcUsers vcUsers = new VcUsers();
    private final List<Account> accounts;

    private VcUsers() {
        this.accounts = new ArrayList<>();
    }

    public static VcUsers getInstance() {
        return vcUsers;
    }

    public void addAccount(String name, int amountOfVc, KeyPair keyPair) {
        accounts.add(new Account(name, amountOfVc, keyPair));
    }

    public Account getAccount(String name) {
        List<Account> matches = accounts.stream()
                .filter(a -> a.getName().equals(name))
                .collect(Collectors.toList());
        if (matches.size() > 0) {
            return matches.get(0);
        }
        Account newAccount = new Account(name);
        accounts.add(newAccount);
        return newAccount;
    }
}
