package carsharing.entity;

public class Company {

    private final Integer id;
    private final String name;

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
