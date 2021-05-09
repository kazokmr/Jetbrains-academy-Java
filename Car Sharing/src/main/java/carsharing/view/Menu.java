package carsharing.view;

import java.util.Scanner;

public class Menu {

    public void prompt() {
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit");
        String command = new Scanner(System.in).nextLine();
        System.out.println();
        switch (command) {
            case "1":
                new CompanyMenu().prompt();
                break;
            case "2":
                new CustomerListMenu().prompt();
                break;
            case "3":
                new CreateCustomerMenu().prompt();
                break;
            case "0":
                return;
            default:
                break;
        }
        prompt();
    }
}
