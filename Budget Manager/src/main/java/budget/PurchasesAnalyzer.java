package budget;

public class PurchasesAnalyzer {
    private final SortedPurchases sortedPurchases;

    public PurchasesAnalyzer(SortedPurchases sortedPurchases) {
        this.sortedPurchases = sortedPurchases;
    }

    void analyze() {
        sortedPurchases.sort();
    }
}
