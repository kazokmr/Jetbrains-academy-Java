package phonebook;

public class Contact implements Comparable<Contact> {
    private final String number;
    private final String name;

    public Contact(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    boolean isNameMatch(String name) {
        return this.name.equals(name);
    }

    int compareName(Contact contact) {
        return compareName(contact.name);
    }

    int compareName(String name) {
        return this.name.compareTo(name);
    }

    @Override
    public int compareTo(Contact o) {
        return compareName(o);
    }
}
