package contacts.contact.impl;

import contacts.contact.Contact;
import contacts.contact.factory.impl.OrganizationPhoneContactFactory;

public class OrganizationContact extends Contact {

    private static final long serialVersionUID = -6529478145591373781L;
    private String address;

    public String getAddress() {
        return address == null ? "[no data]" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getTitle() {
        return getName();
    }

    @Override
    public void edit() {
        new OrganizationPhoneContactFactory().editContact(this);
    }

    @Override
    public boolean isMatch(String query) {
        if (getName().toLowerCase().contains(query)) {
            return true;
        }
        if (getAddress().toLowerCase().contains(query)) {
            return true;
        }
        return super.isMatch(query);
    }

    @Override
    public String toString() {
        return "Organization name: " + getName() + "\n" +
                "Address: " + getAddress() + "\n" +
                super.toString();
    }
}
