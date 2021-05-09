package contacts;

import contacts.command.Button;
import contacts.command.impl.PhoneBookAddButton;
import contacts.command.impl.PhoneBookCountButton;
import contacts.command.impl.PhoneBookListButton;
import contacts.command.impl.PhoneBookSearchButton;
import contacts.serialization.PhoneBookSerialization;

import java.io.File;
import java.util.Scanner;

class PhoneBookInvoker {

    private final PhoneBook phoneBook;
    private boolean active;

    PhoneBookInvoker(String filename) {
        File file = new File(filename);
        phoneBook = file.exists() ? PhoneBookSerialization.deserialize(filename) : new PhoneBook();
    }

    boolean isActive() {
        return active;
    }

    void openMenu() {
        System.out.println("[menu] Enter action (add, list, search, count, exit): ");
        String action = new Scanner(System.in).nextLine().toLowerCase();
        active = true;
        Button button = null;
        switch (action) {
            case "add":
                button = new PhoneBookAddButton(phoneBook);
                break;
            case "list":
                button = new PhoneBookListButton(phoneBook);
                break;
            case "search":
                button = new PhoneBookSearchButton(phoneBook);
                break;
            case "count":
                button = new PhoneBookCountButton(phoneBook);
                break;
            case "exit":
                active = false;
                return;
            default:
                System.out.println("no much action");
                System.out.println();
                openMenu();
                break;
        }
        if (button == null) {
            return;
        }
        button.push();
        System.out.println();
    }
}
