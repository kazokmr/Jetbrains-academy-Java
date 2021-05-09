package blockchain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = Blockchain.getInstance();
        blockchain.setMax(15, 3);
        ExecutorService transactionExecutor = Executors.newFixedThreadPool(3);
        DummyTransactions dummyTransactions = new DummyTransactions();
        transactionExecutor.submit(() -> dummyTransactions.randomTransaction(30));

        ExecutorService miningExecutor = Executors.newFixedThreadPool(30);
        do {
            miningExecutor.submit(() -> {
                Miner miner = new Miner("Miner" + Thread.currentThread().getId());
                miner.mining();
            });
        } while (!blockchain.isFull());

        transactionExecutor.shutdownNow();
        miningExecutor.shutdownNow();
    }
}
