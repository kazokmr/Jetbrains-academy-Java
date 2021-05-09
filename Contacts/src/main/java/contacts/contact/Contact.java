package contacts.contact;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Contact implements Serializable {

    private static final long serialVersionUID = -8029149844216816646L;
    private String name;
    private String number;
    private LocalDateTime created;
    private LocalDateTime lastEdit;

    public abstract String getTitle();

    public abstract void edit();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number == null ? "[no data]" : number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created.truncatedTo(ChronoUnit.SECONDS);
    }

    public void setLastEdit(LocalDateTime lastEdit) {
        this.lastEdit = lastEdit.truncatedTo(ChronoUnit.SECONDS);
    }

    public boolean isMatch(String query) {
        return getNumber().contains(query);
    }

    @Override
    public String toString() {
        return String.format("Number: %s \nTime created: %s \nTime last edit: %s",
                getNumber(), created.toString(), lastEdit.toString());
    }
}
