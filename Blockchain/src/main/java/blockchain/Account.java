package blockchain;

import java.security.*;
import java.util.Objects;

public class Account {
    private final String name;
    private final VirtualCoins vc;
    private final KeyPair keyPair;

    public Account(String name) {
        this.name = name;
        this.vc = new VirtualCoins(0);
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        kpg.initialize(4096, new SecureRandom());
        this.keyPair = kpg.generateKeyPair();
    }

    public Account(String name, int amountOfVc, KeyPair keyPair) {
        this.name = name;
        this.vc = new VirtualCoins(amountOfVc);
        this.keyPair = keyPair;
    }

    public String getName() {
        return name;
    }

    public void transaction(String toName, int toVc) {
        Blockchain blockchain = Blockchain.getInstance();
        Account to = VcUsers.getInstance().getAccount(toName);
        VirtualCoins sentVc = new VirtualCoins(toVc);
        Transaction transaction = new Transaction(this, to, sentVc);
        blockchain.addTransaction(transaction, signature(transaction), keyPair.getPublic());
    }

    public void sent(VirtualCoins vc) {
        this.vc.subtract(vc);
    }

    public void get(VirtualCoins vc) {
        this.vc.add(vc);
    }

    private byte[] signature(Transaction transaction) {
        try {
            Signature rsa = Signature.getInstance("SHA256withRSA");
            rsa.initSign(keyPair.getPrivate());
            rsa.update(transaction.toString().getBytes());
            return rsa.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return name.equals(account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("User %s has %s", name, vc.toString());
    }
}
