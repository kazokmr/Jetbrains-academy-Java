package budget;

import java.util.List;

public abstract class SortedPurchases {
    abstract void sort();

    protected List<PurchaseItem> sortedPurchaseItems(List<PurchaseItem> purchases) {
        for (int i = 0; i < purchases.size(); i++) {
            for (int j = 0; j < purchases.size() - 1 - i; j++) {
                if (purchases.get(j).getPrice() < purchases.get(j + 1).getPrice()) {
                    PurchaseItem temp = purchases.get(j);
                    purchases.set(j, purchases.get(j + 1));
                    purchases.set(j + 1, temp);
                }
            }
        }
        return purchases;
    }

    protected void outSortedItem(List<PurchaseItem> purchases) {
        for (PurchaseItem purchase : purchases) {
            System.out.printf("%s $%.2f\n", purchase.getName(), purchase.getPrice());
        }
    }
}

