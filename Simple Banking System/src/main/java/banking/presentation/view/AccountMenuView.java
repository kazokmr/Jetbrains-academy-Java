package banking.presentation.view;

import banking.application.controller.AccountController;
import banking.domain.model.Account;
import banking.domain.model.creditcard.LuhnAlgorithm;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class AccountMenuView {

    public static void prompt(Account account) {
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        System.out.println();
        switch (number) {
            case "1":
                System.out.printf("Balance: %d\n\n", account.getBalance());
                prompt(account);
                break;
            case "2":
                addIncome(account);
                prompt(account);
                break;
            case "3":
                transfer(account);
                prompt(account);
                break;
            case "4":
                close(account);
                TopMenuView.prompt();
                break;
            case "5":
                System.out.println("You have successfully logged out!\n");
                TopMenuView.prompt();
                break;
            default:
                System.out.println("Bye!");
        }
        scanner.close();
    }

    private static void addIncome(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter income:");
        account.addIncome(scanner.nextLong());
        AccountController.getInstance().updateIncome(account);
        System.out.println("Income was added!");
        scanner.close();
    }

    private static void transfer(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter card number:");
        String targetNumber = scanner.nextLine();
        if (Objects.equals(targetNumber, account.getCardNumber())) {
            System.out.println("You can't transfer money to the same account!");
            return;
        }
        if (!LuhnAlgorithm.isCorrectCardNumber(targetNumber)) {
            System.out.println("Probably you made mistake in the card number. Please try again!");
            return;
        }
        Optional<Account> targetAccount = AccountController.getInstance().findByCardNumber(targetNumber);
        if (targetAccount.isEmpty()) {
            System.out.println("Such a card does not exist.");
            return;
        }

        System.out.println("Enter how much money you want to transfer:");
        long money = scanner.nextLong();
        if (account.getBalance() < money) {
            System.out.println("Not enough money!");
            return;
        }
        account.transferIncome(money);
        targetAccount.get().addIncome(money);
        AccountController.getInstance().transfer(account, targetAccount.get());
    }

    private static void close(Account account) {
        AccountController.getInstance().closeAccount(account);
        System.out.println("Wrong card number or PIN!");
    }
}
