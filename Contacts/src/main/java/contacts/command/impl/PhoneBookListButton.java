package contacts.command.impl;

import contacts.contact.Contact;
import contacts.PhoneBook;
import contacts.command.Button;
import contacts.contact.ContactInvoker;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBookListButton implements Button {

    private final PhoneBook phoneBook;

    public PhoneBookListButton(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    @Override
    public void push() {
        if (phoneBook.count() == 0) {
            System.out.println("No records!");
            return;
        }
        ArrayList<Contact> contacts = phoneBook.list();
        int index = 0;
        for (Contact contact : contacts) {
            System.out.println(++index + ". " + contact.getTitle());
        }
        System.out.println();

        System.out.println("[list] Enter action ([number], back):");
        String action = new Scanner(System.in).nextLine().toLowerCase();
        if (action.matches("[1-9]?\\d+")) {
            Contact contact = contacts.get(Integer.parseInt(action) - 1);
            new ContactInvoker(phoneBook, contact).openRecord();
        }
    }
}