package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static budget.PurchaseByType.*;

public class SortedPurchasesByType extends SortedPurchases {
    private final Map<String, PurchaseByType> purchaseMap;

    public SortedPurchasesByType(Account account) {
        purchaseMap = account.getPurchaseMap();
    }

    @Override
    public void sort() {
        String[] types = new String[]{TYPE_FOOD, TYPE_CLOTHES, TYPE_ENTERTAINMENT, TYPE_OTHER};
        List<PurchaseItem> purchases = new ArrayList<>();
        for (String type : types) {
            if (purchaseMap.containsKey(type)) {
                purchases.add(new PurchaseItem(type, purchaseMap.get(type).getTotalPrice()));
            } else {
                purchases.add(new PurchaseItem(type, 0d));
            }
        }
        System.out.println("Type:");
        purchases = sortedPurchaseItems(purchases);
        for (PurchaseItem purchase : purchases) {
            System.out.printf("%s - $%.2f\n", purchase.getName(), purchase.getPrice());
        }
    }
}
