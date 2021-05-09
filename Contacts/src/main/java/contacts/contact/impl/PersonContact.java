package contacts.contact.impl;

import contacts.contact.Contact;
import contacts.contact.factory.impl.PersonPhoneContactFactory;

import java.time.LocalDate;

public class PersonContact extends Contact {

    private static final long serialVersionUID = -5320005830858540342L;
    private String surName;
    private LocalDate birthDate;
    private String gender;

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getBirthDate() {
        return birthDate == null ? "[no data]" : birthDate.toString();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender == null ? "[no data]" : gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getTitle() {
        return getName() + (surName == null ? "" : " " + surName);
    }

    @Override
    public void edit() {
        new PersonPhoneContactFactory().editContact(this);
    }

    @Override
    public boolean isMatch(String query) {
        if (getName().toLowerCase().contains(query)) {
            return true;
        }
        if (getSurName().toLowerCase().contains(query)) {
            return true;
        }
        return super.isMatch(query);
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "Surname: " + getSurName() + "\n" +
                "Birth date: " + getBirthDate() + "\n" +
                "Gender: " + getGender() + "\n" +
                super.toString();
    }
}
