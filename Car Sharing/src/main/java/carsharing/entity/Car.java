package carsharing.entity;

public class Car {
    private final Integer id;
    private final String name;
    private final Company company;

    public Car(Integer id, String name, Company company) {
        this.id = id;
        this.name = name;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Company getCompany() {
        return company;
    }

    public Integer getCompanyId() {
        return company.getId();
    }
}
