package blockchain;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private final long timestamp;
    private final Account from;
    private final Account to;
    private final VirtualCoins vc;

    public Transaction(Account from, Account to, VirtualCoins vc) {
        this.timestamp = new Date().getTime();
        this.from = from;
        this.to = to;
        this.vc = vc;
    }

    public void transfer() {
        from.sent(vc);
        to.get(vc);
    }

    @Override
    public String toString() {
        return String.format("%s sent %s to %s", from.getName(), vc.toString(), to.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return timestamp == that.timestamp &&
                from.equals(that.from) &&
                to.equals(that.to) &&
                vc.equals(that.vc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, from, to, vc);
    }
}
