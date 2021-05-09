package blockchain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DummyTransactions {

    private final String[] dummyNames = {
            "Nick", "Bob", "Alice",
            "ShoesShop", "FastFood", "CarShop", "CarPartsShop",
            "Worker1", "Worker2", "Worker3", "Worker4", "Worker5"
    };

    public DummyTransactions() {
        VcUsers vcUsers = VcUsers.getInstance();
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        kpg.initialize(4096, new SecureRandom());
        KeyPair keyPair = kpg.generateKeyPair();
        for (String dummyName : dummyNames) {
            vcUsers.addAccount(dummyName, 100, keyPair);
        }
    }

    public void randomTransaction(int count) {
        VcUsers vcUsers = VcUsers.getInstance();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            Account account = vcUsers.getAccount(dummyNames[random.nextInt(dummyNames.length)]);
            String toName = dummyNames[random.nextInt(dummyNames.length)];
            int amountOfVc = random.nextInt(200);
            account.transaction(toName, amountOfVc);
        }
    }
}
