package contacts.contact;

import contacts.PhoneBook;
import contacts.serialization.PhoneBookSerialization;

import java.util.Scanner;

public class ContactInvoker {
    private final PhoneBook phoneBook;
    private final Contact contact;

    public ContactInvoker(PhoneBook phoneBook, Contact contact) {
        this.phoneBook = phoneBook;
        this.contact = contact;
    }

    public void openRecord() {
        System.out.println(contact.toString());
        System.out.println();
        System.out.println("[record] Enter action (edit, delete, menu): ");
        String action = new Scanner(System.in).nextLine().toLowerCase();
        switch (action) {
            case "edit":
                phoneBook.edit(contact);
                break;
            case "delete":
                phoneBook.remove(contact);
                System.out.println("delete");
                break;
            default:
                return;
        }
        PhoneBookSerialization.serialize(phoneBook);
    }
}
