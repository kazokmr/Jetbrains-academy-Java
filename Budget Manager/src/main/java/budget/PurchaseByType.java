package budget;

import java.util.ArrayList;
import java.util.List;

public class PurchaseByType {
    public static final String TYPE_FOOD = "Food";
    public static final String TYPE_CLOTHES = "Clothes";
    public static final String TYPE_ENTERTAINMENT = "Entertainment";
    public static final String TYPE_OTHER = "Other";

    private final List<PurchaseItem> purchases;
    private final String type;

    public PurchaseByType(String type) {
        this.type = type;
        this.purchases = new ArrayList<>();
    }

    void add(PurchaseItem item) {
        purchases.add(item);
    }

    public void listOfPurchases() {
        for (PurchaseItem ite : purchases) {
            System.out.println(ite.toString());
        }
    }

    double getTotalPrice() {
        double total = 0d;
        for (PurchaseItem ite : purchases) {
            total += ite.getPrice();
        }
        return total;
    }

    String getAllList() {
        StringBuilder str = new StringBuilder();
        for (PurchaseItem purchase : purchases) {
            str.append(type).append(",").append(purchase.getName()).append(",").append(purchase.getPrice()).append("\n");
        }
        return str.toString().replaceAll("\\n$", "");
    }

    List<PurchaseItem> getPurchases() {
        return this.purchases;
    }

}
