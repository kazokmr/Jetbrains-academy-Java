package contacts.contact.factory.impl;

import contacts.contact.Contact;
import contacts.contact.factory.PhoneContactFactory;
import contacts.contact.impl.PersonContact;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class PersonPhoneContactFactory extends PhoneContactFactory {

    @Override
    public Contact createContact() {
        PersonContact phoneContact = new PersonContact();
        setName(phoneContact);
        setSurName(phoneContact);
        setBirthday(phoneContact);
        setGender(phoneContact);
        setNumber(phoneContact);
        setCreated(phoneContact);
        System.out.println("The record added.");
        return phoneContact;
    }

    @Override
    public void editContact(Contact contact) {
        PersonContact personPhoneContact = (PersonContact) contact;
        System.out.println("Select a field (name, surname, birth, gender, number):");
        switch (scanner.nextLine()) {
            case "name":
                setName(personPhoneContact);
                break;
            case "surname":
                setSurName(personPhoneContact);
                break;
            case "birth":
                setBirthday(personPhoneContact);
                break;
            case "gender":
                setGender(personPhoneContact);
                break;
            case "number":
                setNumber(contact);
                break;
            default:
                return;
        }
        super.editContact(contact);
    }

    private void setName(PersonContact phoneContact) {
        System.out.println("Enter the name:");
        phoneContact.setName(scanner.nextLine());
    }

    private void setSurName(PersonContact phoneContact) {
        System.out.println("Enter the surname:");
        phoneContact.setSurName(scanner.nextLine());
    }

    private void setBirthday(PersonContact phoneContact) {
        System.out.println("Enter the birth date: ");
        String birthday = scanner.nextLine();
        if (!isCorrectBirthday(birthday)) {
            System.out.println("Bad birth date!");
            phoneContact.setBirthDate(null);
        } else {
            phoneContact.setBirthDate(LocalDate.parse(birthday));
        }
    }

    private void setGender(PersonContact phoneContact) {
        System.out.println("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if (!isCorrectGender(gender)) {
            System.out.println("Bad gender!");
            phoneContact.setGender(null);
        } else {
            phoneContact.setGender(gender);
        }
    }

    private boolean isCorrectBirthday(String date) {
        if (date == null || "".equals(date)) {
            return false;
        }
        Pattern patternDate = Pattern.compile("^[1-2]\\d{3}-(0[1-9]|1[0-9])-(0[1-9]|[1-2][0-9]|3[01])");
        return patternDate.matcher(date).matches();
    }

    private boolean isCorrectGender(String gender) {
        if (gender == null || "".equals(gender)) {
            return false;
        }
        return "M".equals(gender.toUpperCase()) || !"F".equals(gender.toUpperCase());
    }
}
