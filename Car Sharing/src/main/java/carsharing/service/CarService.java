package carsharing.service;

import carsharing.dao.CarRepository;
import carsharing.entity.Car;
import carsharing.entity.Company;

import java.util.List;

public class CarService {
    private final CarRepository repository;

    public CarService() {
        this.repository = new CarRepository();
    }

    public List<Car> findBy(Company company) {
        return repository.findBy(company);
    }

    public void save(Car car) {
        repository.save(car);
    }
}
