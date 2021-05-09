package contacts.command.impl;

import contacts.command.Button;
import contacts.contact.Contact;
import contacts.PhoneBook;
import contacts.contact.ContactInvoker;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBookSearchButton implements Button {

    private final PhoneBook phoneBook;

    public PhoneBookSearchButton(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    @Override
    public void push() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter search query: ");
        String query = scanner.nextLine();
        ArrayList<Contact> searchedContacts = phoneBook.search(query);

        String action;
        if (searchedContacts.size() == 0) {
            System.out.println("No records!");
            return;
        }

        int index = 0;
        for (Contact contact : searchedContacts) {
            System.out.println(++index + ". " + contact.getTitle());
        }
        System.out.println();

        System.out.println("[search] Enter action ([number], back, again): ");
        action = scanner.nextLine().toLowerCase();
        if (action.matches("[1-9]?\\d+")) {
            Contact contact = searchedContacts.get(Integer.parseInt(action) - 1);
            new ContactInvoker(phoneBook, contact).openRecord();
        } else if ("again".equals(action)) {
            push();
        }
    }
}
