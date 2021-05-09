package budget;

public class PurchaseItem {

    private final String name;
    private final double price;

    public PurchaseItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", name, price);
    }
}
