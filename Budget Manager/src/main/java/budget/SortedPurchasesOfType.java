package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static budget.PurchaseByType.*;

public class SortedPurchasesOfType extends SortedPurchases {
    private final Map<String, PurchaseByType> purchaseMap;

    public SortedPurchasesOfType(Account account) {
        this.purchaseMap = account.getPurchaseMap();
    }

    @Override
    public void sort() {
        String type = getType();
        if (!purchaseMap.containsKey(type)) {
            System.out.println("Purchase list is empty!");
            return;
        }
        System.out.println(type + ":");
        List<PurchaseItem> purchases = new ArrayList<>(purchaseMap.get(type).getPurchases());
        purchases = sortedPurchaseItems(purchases);
        outSortedItem(purchases);
    }

    private String getType() {
        System.out.println("Choose the type of purchaseItem\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        System.out.println();
        switch (type) {
            case "1":
                return TYPE_FOOD;
            case "2":
                return TYPE_CLOTHES;
            case "3":
                return TYPE_ENTERTAINMENT;
            case "4":
                return TYPE_OTHER;
            default:
                return "";
        }
    }

}
