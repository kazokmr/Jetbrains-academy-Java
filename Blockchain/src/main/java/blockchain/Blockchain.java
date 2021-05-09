package blockchain;

import java.io.Serializable;
import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Blockchain implements Serializable {
    private static final long serialVersionUID = 2847783396517015023L;
    private static final Blockchain blockchain = new Blockchain();
    private final List<Block> blocks = new ArrayList<>();
    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();
    private int currentId;
    private int numberOfZero;
    private String currentHash;
    private int maxSize;
    private int maxLengthOfZero;

    private Blockchain() {
        currentId = 1;
        numberOfZero = 0;
        currentHash = "0";
    }

    public static Blockchain getInstance() {
        return blockchain;
    }

    public void setMax(int maxSize, int maxLengthOfZero) {
        this.maxSize = maxSize;
        this.maxLengthOfZero = maxLengthOfZero;
    }

    public synchronized int getCurrentId() {
        return currentId;
    }

    public boolean isFull() {
        return maxSize == 0 || blocks.size() >= maxSize;
    }

    public synchronized Block getNextBlock() {
        VirtualCoins award = new VirtualCoins(100);
        return new Block(currentId, currentHash, numberOfZero, transactions, award);
    }

    public void addTransaction(Transaction transaction, byte[] signature, PublicKey publicKey) {
        try {
            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(publicKey);
            sig.update(transaction.toString().getBytes());
            if (sig.verify(signature)) {
                transactions.add(transaction);
            } else {
                System.out.println("Verifying Signature is invalid.");
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            System.out.println("Verifying Signature is invalid");
            throw new RuntimeException(e);
        }
    }

    public synchronized void addBlock(Block block) {
        if (!isValid(block) || isFull()) {
            return;
        }
        block.giveAwardToMiner();
        System.out.println(block.toString());
        long createTime = block.getTimeOfGenerate();
        if (createTime < 1000 && numberOfZero < maxLengthOfZero) {
            numberOfZero++;
            System.out.println("N was increased to " + numberOfZero);
        } else if (createTime < 2000 || numberOfZero == 0) {
            System.out.println("N stays the same");
        } else {
            numberOfZero--;
            System.out.println("N was decreased by 1");
        }
        System.out.println();
        currentId++;
        currentHash = block.getHash();
        transactions.stream()
                .parallel()
                .filter(t -> block.getTransactions().contains(t))
                .forEach(t -> {
                    t.transfer();
                    transactions.remove(t);
                });
        blocks.add(block);

    }

    private boolean isValid(Block block) {
        if (block.getId() != currentId) {
            return false;
        }
        if (!block.getPreviousHash().equals(currentHash)) {
            return false;
        }
        return numberOfZero == 0 || block.getHash().substring(0, numberOfZero).matches("^0+$");
    }
}
