package carsharing.view;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.service.CarService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CarRentalMenu {
    private final CarService carService;

    CarRentalMenu() {
        this.carService = new CarService();
    }

    private Car chooseCar(Company company) {
        List<Car> carList = carService.findBy(company);
        if (carList.isEmpty()) {
            System.out.printf("No available cars in the '%s'%n", company.getName());
            return null;
        }
        System.out.println("Choose a car:");
        IntStream.rangeClosed(1, carList.size())
                .forEach(i -> System.out.printf("%d. %s%n", i, carList.get(i - 1).getName()));
        System.out.println("0. Back");
        int number = Integer.parseInt(new Scanner(System.in).nextLine());
        System.out.println();
        if (number > 0 && number <= carList.size()) {
            return carList.get(number - 1);
        }
        return null;
    }

    Car prompt() {
        Company company = new CompanyMenu().chooseCompany();
        if (company == null) {
            return null;
        }
        return chooseCar(company);
    }
}
