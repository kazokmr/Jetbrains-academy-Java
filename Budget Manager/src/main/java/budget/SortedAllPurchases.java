package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SortedAllPurchases extends SortedPurchases {
    private final Map<String, PurchaseByType> purchaseMap;

    public SortedAllPurchases(Account account) {
        this.purchaseMap = account.getPurchaseMap();
    }

    @Override
    public void sort() {
        List<PurchaseItem> purchases = new ArrayList<>();
        for (PurchaseByType value : purchaseMap.values()) {
            purchases.addAll(value.getPurchases());
        }
        if (purchases.size() == 0) {
            System.out.println("Purchase list is empty!");
            return;
        }
        System.out.println("All:");
        purchases = sortedPurchaseItems(purchases);
        outSortedItem(purchases);
    }
}
