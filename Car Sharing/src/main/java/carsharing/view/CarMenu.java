package carsharing.view;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.service.CarService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CarMenu {

    private final Company company;
    private final CarService service;

    CarMenu(Company company) {
        this.company = company;
        this.service = new CarService();
        System.out.printf("'%s' company %n", company.getName());
    }

    void prompt() {
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");
        String command = new Scanner(System.in).nextLine();
        System.out.println();
        switch (command) {
            case "1":
                findCars();
                break;
            case "2":
                createCar();
                break;
            case "0":
                return;
            default:
                break;
        }
        System.out.println();
        prompt();
    }

    private void findCars() {
        List<Car> carList = service.findBy(company);
        if (carList.isEmpty()) {
            System.out.println("The car list is empty!");
            return;
        }
        System.out.println("Car list:");
        // It can't use id of car because all company use common number.
        IntStream.rangeClosed(1, carList.size())
                .forEach(i -> System.out.printf("%d. %s%n", i, carList.get(i - 1).getName()));
    }

    private void createCar() {
        System.out.println("Enter the car name:");
        String name = new Scanner(System.in).nextLine();
        Car car = new Car(null, name, company);
        service.save(car);
        System.out.println("The car was added!");
    }
}
