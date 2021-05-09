package budget;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PurchasesFile {
    private final File file;
    private final Account account;

    public PurchasesFile(Account account) {
        this.file = new File("purchases.txt");
        this.account = account;
    }

    public void write() {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(file))) {
            write.write(account.balance());
            write.newLine();
            write.write(account.outputAllPurchases());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Account read() {
        if (!file.exists()) {
            return account;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            double balance = Double.parseDouble(reader.readLine());
            Map<String, PurchaseByType> purchaseMap = new HashMap<>();
            String line = reader.readLine();
            while (line != null) {
                String[] values = line.split(",");
                PurchaseByType purchase = purchaseMap.computeIfAbsent(values[0], PurchaseByType::new);
                purchase.add(new PurchaseItem(values[1], Double.parseDouble(values[2])));
                line = reader.readLine();
            }
            return new Account(new Wallet(balance), purchaseMap);
        } catch (IOException e) {
            e.printStackTrace();
            return new Account();
        }
    }
}
