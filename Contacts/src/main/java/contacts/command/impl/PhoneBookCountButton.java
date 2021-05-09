package contacts.command.impl;

import contacts.PhoneBook;
import contacts.command.Button;

public class PhoneBookCountButton implements Button {

    private final PhoneBook phoneBook;

    public PhoneBookCountButton(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    @Override
    public void push() {
        System.out.printf("The Phone Book has %d records.", phoneBook.count());
    }
}
