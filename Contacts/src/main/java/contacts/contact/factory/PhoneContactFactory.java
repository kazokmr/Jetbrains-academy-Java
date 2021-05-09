package contacts.contact.factory;

import contacts.contact.Contact;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class PhoneContactFactory {

    protected final Scanner scanner;

    public PhoneContactFactory() {
        this.scanner = new Scanner(System.in);
    }

    public abstract Contact createContact();

    protected void editContact(Contact contact) {
        setLastUpdate(contact);
        System.out.println("Saved");
        System.out.println(contact.toString());
    }

    protected void setNumber(Contact contact) {
        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();
        if (!isCorrectNumber(phoneNumber)) {
            System.out.println("Wrong number format!");
            contact.setNumber(null);
        } else {
            contact.setNumber(phoneNumber);
        }
    }

    protected void setCreated(Contact contact) {
        LocalDateTime dateTime = LocalDateTime.now();
        contact.setCreated(dateTime);
        contact.setLastEdit(dateTime);
    }

    private void setLastUpdate(Contact contact) {
        contact.setLastEdit(LocalDateTime.now());
    }

    private boolean isCorrectNumber(String number) {

        if (number == null || "".equals(number.trim())) {
            return false;
        }
        String[] group = number.split("(\\s|-)");
        // the first group は前にプラスがあって()を含んでいてもよい。
        Pattern patternFirst = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+)");
        if (!patternFirst.matcher(group[0]).matches()) {
            return false;
        }
        // 2つ目のグループの括弧の調査
        if (group.length < 2) {
            return true;
        }
        Pattern patternSecond;
        if (group[0].contains("(")) {
            patternSecond = Pattern.compile("\\w{2,}");
        } else {
            patternSecond = Pattern.compile("(\\(\\w{2,}\\)|\\w{2,})");
        }
        if (!patternSecond.matcher(group[1]).matches()) {
            return false;
        }

        // 3つ目以降
        Pattern pattern = Pattern.compile("\\w{2,}");
        for (int index = 2; index < group.length; index++) {
            if (!pattern.matcher(group[index]).matches()) {
                return false;
            }
        }
        return true;
    }
}
