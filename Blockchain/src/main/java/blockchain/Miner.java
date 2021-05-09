package blockchain;

import java.util.Random;

public class Miner {

    private final String name;
    private final VirtualCoins vc;

    public Miner(String name) {
        this.name = name;
        this.vc = new VirtualCoins(0);
    }

    public void getAward(VirtualCoins vc) {
        this.vc.add(vc);
    }

    public String getName() {
        return name;
    }

    public void mining() {
        Blockchain blockchain = Blockchain.getInstance();
        if (blockchain.isFull()) {
            return;
        }
        Block block = blockchain.getNextBlock();
        long start = System.currentTimeMillis();
        long magicNumber;
        String hash;
        do {
            magicNumber = new Random().nextInt(Integer.MAX_VALUE);
            String text = block.getDataByText(magicNumber, this);
            hash = StringUtil.generateHash(text);
        } while (!blockchain.isFull() && block.getId() == blockchain.getCurrentId() && !block.isHashValid(hash));
        if (block.getId() != blockchain.getCurrentId()) {
            return;
        }
        long timeOfGenerate = System.currentTimeMillis() - start;
        block.setMining(magicNumber, hash, this, timeOfGenerate);
        blockchain.addBlock(block);
    }
}
