package banking.presentation.view;

import banking.application.controller.AccountController;
import banking.domain.model.Account;
import banking.domain.model.creditcard.CreditCard;

import java.util.Optional;
import java.util.Scanner;

public class TopMenuView {

    public static void prompt() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        System.out.println();
        switch (number) {
            case "1":
                createAccount();
                prompt();
                break;
            case "2":
                login();
                break;
            default:
                System.out.println("Bye!");
        }
        scanner.close();
    }

    private static void createAccount() {
        CreditCard creditCard = AccountController.getInstance().execute();
        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(creditCard.getCardNumber());
        System.out.println("Your card PIN:");
        System.out.println(creditCard.getPinCode());
        System.out.println();
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your card number;");
        String cardNumber = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();
        System.out.println();
        Optional<Account> optionalAccount = AccountController.getInstance().getAccount(cardNumber, pin);
        optionalAccount.ifPresentOrElse(
                account -> {
                    System.out.println("You have successfully logged in!\n");
                    AccountMenuView.prompt(account);
                },
                () -> {
                    System.out.println("Wrong card number or PIN!\n");
                    prompt();
                }
        );
        scanner.close();
    }
}
