package blockchain;

public class VirtualCoins {
    private final int vc;

    public VirtualCoins(int vc) {
        this.vc = vc;
    }

    public VirtualCoins add(VirtualCoins added) {
        return new VirtualCoins(vc + added.vc);
    }

    public VirtualCoins subtract(VirtualCoins coins) {
        return new VirtualCoins(vc - coins.vc);
    }

    @Override
    public String toString() {
        return String.format("%d VC", vc);
    }
}
