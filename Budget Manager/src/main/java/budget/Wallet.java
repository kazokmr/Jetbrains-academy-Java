package budget;

public class Wallet {
    private double balance;

    public Wallet() {
        this.balance = 0d;
    }

    public Wallet(double balance) {
        this.balance = balance;
    }

    void charge(double income) {
        balance += income;
    }

    void expanse(double expense) {
        balance -= expense;
    }

    @Override
    public String toString() {
        return Double.toString(balance);
    }
}
