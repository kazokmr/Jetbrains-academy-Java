package carsharing.entity;

public class Customer {
    private final Integer id;
    private final String name;
    private Car rentedCar;

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.rentedCar = null;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Car getRentedCar() {
        return rentedCar;
    }

    public void setRentedCar(Car rentedCar) {
        this.rentedCar = rentedCar;
    }

    public boolean hasRentedCar() {
        return rentedCar != null;
    }

    public void returnRentedCar() {
        setRentedCar(null);
    }
}
