package carsharing.service;

import carsharing.dao.CustomerRepository;
import carsharing.entity.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService() {
        this.repository = new CustomerRepository();
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public void updateRentedCar(Customer customer) {
        repository.saveRentedCar(customer);
    }

    public void save(Customer customer) {
        repository.save(customer);
    }
}
