package contacts.serialization;

import contacts.PhoneBook;

import java.io.*;

public class PhoneBookSerialization {

    private static final String FILE_NAME = "phonebook.db";

    public static void serialize(PhoneBook phoneBook) {
        BufferedOutputStream bos;
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            bos = new BufferedOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(phoneBook);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PhoneBook deserialize(String fileName) {
        System.out.printf("open %s", fileName);
        PhoneBook phoneBook;

        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);
            phoneBook = (PhoneBook) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            phoneBook = new PhoneBook();
        }
        System.out.println();
        return phoneBook;
    }
}
