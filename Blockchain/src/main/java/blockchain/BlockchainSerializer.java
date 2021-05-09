package blockchain;

import java.io.*;

public class BlockchainSerializer {

    private static final String fileName = "blockchain.dat";

    private static void serialize(Blockchain blockchain) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            oos.writeObject(blockchain);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Blockchain deSerialize() {
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            return (Blockchain) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
