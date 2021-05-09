package contacts.contact.factory.impl;

import contacts.contact.Contact;
import contacts.contact.factory.PhoneContactFactory;
import contacts.contact.impl.OrganizationContact;

public class OrganizationPhoneContactFactory extends PhoneContactFactory {

    @Override
    public Contact createContact() {
        OrganizationContact phoneContact = new OrganizationContact();
        setOrganizationName(phoneContact);
        setAddress(phoneContact);
        setNumber(phoneContact);
        setCreated(phoneContact);
        System.out.println("The record added.");
        return phoneContact;
    }

    @Override
    public void editContact(Contact contact) {
        OrganizationContact orgPhoneContact = (OrganizationContact) contact;
        System.out.println("Select a field (organization, address, number):");
        switch (scanner.nextLine()) {
            case "organization":
                setOrganizationName(orgPhoneContact);
                break;
            case "address":
                setAddress(orgPhoneContact);
                break;
            case "number":
                setNumber(orgPhoneContact);
                break;
            default:
                return;
        }
        super.editContact(contact);
    }

    private void setOrganizationName(OrganizationContact phoneContact) {
        System.out.println("Enter the organization name:");
        phoneContact.setName(scanner.nextLine());
    }

    private void setAddress(OrganizationContact phoneContact) {
        System.out.println("Enter the address:");
        phoneContact.setAddress(scanner.nextLine());
    }
}
