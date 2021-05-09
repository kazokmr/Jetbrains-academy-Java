package contacts.command.impl;

import contacts.command.Button;
import contacts.contact.Contact;
import contacts.contact.factory.PhoneContactFactory;
import contacts.contact.factory.impl.OrganizationPhoneContactFactory;
import contacts.contact.factory.impl.PersonPhoneContactFactory;
import contacts.PhoneBook;
import contacts.serialization.PhoneBookSerialization;

import java.util.Scanner;

public class PhoneBookAddButton implements Button {

    private final PhoneBook phoneBook;

    public PhoneBookAddButton(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    @Override
    public void push() {
        System.out.println("Enter the type (person, organization): ");
        String type = new Scanner(System.in).nextLine().toLowerCase();
        PhoneContactFactory phoneContactFactory;
        switch (type) {
            case "person":
                phoneContactFactory = new PersonPhoneContactFactory();
                break;
            case "organization":
                phoneContactFactory = new OrganizationPhoneContactFactory();
                break;
            default:
                System.out.println("no type");
                return;
        }
        Contact contact = phoneContactFactory.createContact();
        phoneBook.add(contact);
        PhoneBookSerialization.serialize(phoneBook);
    }
}
