package contacts;

import contacts.contact.Contact;

import java.io.Serializable;
import java.util.ArrayList;

public class PhoneBook implements Serializable {
    private static final long serialVersionUID = -7447734644457249806L;

    private final ArrayList<Contact> contacts;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public void edit(Contact contact) {
        contact.edit();
    }

    public void remove(Contact contact) {
        contacts.remove(contact);
    }

    public int count() {
        return contacts.size();
    }

    public ArrayList<Contact> list() {
        return this.contacts;
    }

    public ArrayList<Contact> search(String query) {
        ArrayList<Contact> searchedContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.isMatch(query)) {
                searchedContacts.add(contact);
            }
        }
        return searchedContacts;
    }
}
