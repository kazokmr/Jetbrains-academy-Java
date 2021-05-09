package blockchain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Block implements Serializable {
    private static final long serialVersionUID = 3249381845037498710L;
    private final int id;
    private final long timestamp;
    private final String previousHash;
    private final int numberOfZero;
    private final List<Transaction> transactions;
    private final VirtualCoins award;
    private long magicNumber;
    private String hash;
    private Miner miner;
    private long timeOfGenerate;

    public Block(int id, String previousHash, int numberOfZero, List<Transaction> transactions, VirtualCoins award) {
        this.id = id;
        this.timestamp = new Date().getTime();
        this.previousHash = previousHash;
        this.numberOfZero = numberOfZero;
        this.transactions = transactions;
        this.award = award;
    }

    public void setMining(long magicNumber, String hash, Miner miner, long timeOfGenerate) {
        this.magicNumber = magicNumber;
        this.hash = hash;
        this.miner = miner;
        this.timeOfGenerate = timeOfGenerate;
    }

    public int getId() {
        return id;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public long getTimeOfGenerate() {
        return timeOfGenerate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void giveAwardToMiner() {
        miner.getAward(award);
    }

    public String getDataByText(long magicNumber, Miner miner) {
        return id + timestamp + previousHash + transactions + miner.getName() + award + magicNumber;
    }

    public boolean isHashValid(String hash) {
        String regexp = String.format("^0{%d}.+", numberOfZero);
        return hash.matches(regexp);
    }

    private String getTransactionMessages() {
        if (transactions == null || transactions.isEmpty()) {
            return "No transactions";
        }
        return transactions.stream()
                .map(t -> String.format("\n%s", t.toString()))
                .collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return "Block:\n" +
                String.format("Created by %s\n", miner.getName()) +
                String.format("%s gets %s\n", miner.getName(), award.toString()) +
                String.format("Id: %s\n", id) +
                String.format("Timestamp: %d\n", timestamp) +
                String.format("Magic number: %d\n", magicNumber) +
                String.format("Hash of the previous block:\n%s\n", previousHash) +
                String.format("Hash of the block:\n%s\n", hash) +
                String.format("Block data:%s\n", getTransactionMessages()) +
                String.format("Block was generating for %d seconds", timeOfGenerate / 1000);
    }
}
