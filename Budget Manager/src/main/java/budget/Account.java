package budget;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private final Wallet wallet;
    private final Map<String, PurchaseByType> purchaseMap;

    public Account() {
        this.wallet = new Wallet();
        this.purchaseMap = new HashMap<>();
    }

    public Account(Wallet wallet, Map<String, PurchaseByType> purchaseMap) {
        this.wallet = wallet;
        this.purchaseMap = purchaseMap;
    }

    void income(double income) {
        wallet.charge(income);
    }

    void purchase(String type, String name, double price) {
        wallet.expanse(price);
        PurchaseByType purchaseByType = purchaseMap.computeIfAbsent(type, PurchaseByType::new);
        purchaseByType.add(new PurchaseItem(name, price));
    }

    void listOfPurchasesByType(String type) {
        System.out.println(type + ":");
        if (!purchaseMap.containsKey(type)) {
            System.out.println("Purchase list is empty");
            return;
        }
        PurchaseByType purchaseByType = purchaseMap.get(type);
        purchaseByType.listOfPurchases();
        System.out.printf("Total sum: $%.2f\n", purchaseByType.getTotalPrice());
    }

    void listOfAllPurchases() {
        double total = 0d;
        System.out.println("All:");
        if (purchaseMap.isEmpty()) {
            System.out.println("Purchase list is empty");
            return;
        }
        for (PurchaseByType purchaseByType : purchaseMap.values()) {
            purchaseByType.listOfPurchases();
            total += purchaseByType.getTotalPrice();
        }
        System.out.printf("Total sum: $%.2f\n", total);
    }

    String balance() {
        return wallet.toString();
    }

    String outputAllPurchases() {
        StringBuilder str = new StringBuilder();
        for (PurchaseByType purchase : purchaseMap.values()) {
            str.append(purchase.getAllList()).append("\n");
        }
        return str.toString().replaceAll("\\n$", "");
    }

    Map<String, PurchaseByType> getPurchaseMap() {
        return purchaseMap;
    }
}
