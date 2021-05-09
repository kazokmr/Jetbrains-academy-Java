package budget;

import java.util.Scanner;

import static budget.PurchaseByType.*;

public class PurchaseService {

    private Account account;

    public PurchaseService() {
        this.account = new Account();
    }

    void start() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("7) Analyze (Sort)");
        System.out.println("0) Exit");
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        System.out.println();
        boolean terminate = false;
        switch (action) {
            case "1":
                addIncome();
                break;
            case "2":
                addPurchase();
                break;
            case "3":
                showListOfPurchases();
                break;
            case "4":
                showBalance();
                break;
            case "5":
                savePurchases();
                break;
            case "6":
                loadPurchases();
                break;
            case "7":
                analyzePurchases();
                break;
            default:
                System.out.println("Bye!");
                terminate = true;
        }
        System.out.println();
        if (!terminate) {
            start();
        }
    }

    private void addIncome() {
        System.out.println("Enter income:");
        Scanner scanner = new Scanner(System.in);
        double income = scanner.nextDouble();
        account.income(income);
        System.out.println("Income was added!");
    }

    private void addPurchase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose tye type of purchases");
        System.out.println("1) " + TYPE_FOOD);
        System.out.println("2) " + TYPE_CLOTHES);
        System.out.println("3) " + TYPE_ENTERTAINMENT);
        System.out.println("4) " + TYPE_OTHER);
        System.out.println("5) Back");
        String selType = scanner.nextLine();
        System.out.println();
        String type;
        switch (selType) {
            case "1":
                type = TYPE_FOOD;
                break;
            case "2":
                type = TYPE_CLOTHES;
                break;
            case "3":
                type = TYPE_ENTERTAINMENT;
                break;
            case "4":
                type = TYPE_OTHER;
                break;
            default:
                return;
        }
        purchaseByType(type);
        System.out.println();
        addPurchase();
    }

    private void purchaseByType(String type) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter purchase name:");
        String name = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = scanner.nextDouble();
        account.purchase(type, name, price);
        System.out.println("Purchase was added!");
    }

    private void showListOfPurchases() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose tye type of purchases");
        System.out.println("1) " + TYPE_FOOD);
        System.out.println("2) " + TYPE_CLOTHES);
        System.out.println("3) " + TYPE_ENTERTAINMENT);
        System.out.println("4) " + TYPE_OTHER);
        System.out.println("5) All");
        System.out.println("6) Back");
        String selType = scanner.nextLine();
        System.out.println();
        switch (selType) {
            case "1":
                account.listOfPurchasesByType(TYPE_FOOD);
                break;
            case "2":
                account.listOfPurchasesByType(TYPE_CLOTHES);
                break;
            case "3":
                account.listOfPurchasesByType(TYPE_ENTERTAINMENT);
                break;
            case "4":
                account.listOfPurchasesByType(TYPE_OTHER);
                break;
            case "5":
                account.listOfAllPurchases();
                break;
            default:
                return;
        }
        System.out.println();
        showListOfPurchases();
    }

    private void showBalance() {
        System.out.printf("Balance: $%s\n", account.balance());
    }

    private void savePurchases() {
        PurchasesFile purchasesFile = new PurchasesFile(account);
        purchasesFile.write();
        System.out.println("Purchases were saved!");
    }

    private void loadPurchases() {
        PurchasesFile purchasesFile = new PurchasesFile(account);
        account = purchasesFile.read();
        System.out.println("Purchases were loaded!");
    }

    private void analyzePurchases() {
        System.out.println("How do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back");
        Scanner scanner = new Scanner(System.in);
        String select = scanner.nextLine();
        System.out.println();
        SortedPurchases sortedPurchases;
        switch (select) {
            case "1":
                sortedPurchases = new SortedAllPurchases(account);
                break;
            case "2":
                sortedPurchases = new SortedPurchasesByType(account);
                break;
            case "3":
                sortedPurchases = new SortedPurchasesOfType(account);
                break;
            default:
                return;
        }
        PurchasesAnalyzer analyzer = new PurchasesAnalyzer(sortedPurchases);
        analyzer.analyze();
        System.out.println();
        analyzePurchases();
    }
}
