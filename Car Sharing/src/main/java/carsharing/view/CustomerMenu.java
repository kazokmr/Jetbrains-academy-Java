package carsharing.view;

import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;
import carsharing.service.CustomerService;

import java.util.Scanner;

public class CustomerMenu {
    private final CustomerService service;
    private final Customer customer;

    CustomerMenu(Customer customer) {
        this.customer = customer;
        this.service = new CustomerService();
    }

    void prompt() {
        System.out.println("1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
        String input = new Scanner(System.in).nextLine();
        System.out.println();
        switch (input) {
            case "1":
                rentCar();
                break;
            case "2":
                returnRentedCar();
                break;
            case "3":
                showRentedCar();
                break;
            case "0":
                return;
            default:
                break;
        }
        System.out.println();
        prompt();
    }

    private void rentCar() {
        if (customer.hasRentedCar()) {
            System.out.println("You've already rented a car!");
            return;
        }
        Car rentedCar = new CarRentalMenu().prompt();
        if (rentedCar == null) {
            return;
        }
        customer.setRentedCar(rentedCar);
        service.updateRentedCar(customer);
        System.out.printf("You rented '%s'%n", rentedCar.getName());
    }

    private void returnRentedCar() {
        if (!customer.hasRentedCar()) {
            System.out.println("You didn't rent a car!");
            return;
        }
        customer.returnRentedCar();
        service.updateRentedCar(customer);
        System.out.println("You've returned a rented car!");
    }

    private void showRentedCar() {
        if (!customer.hasRentedCar()) {
            System.out.println("You didn't rent a car!");
            return;
        }
        Car rentedCar = customer.getRentedCar();
        Company company = rentedCar.getCompany();
        System.out.println("Your rented car:");
        System.out.println(rentedCar.getName());
        System.out.println("Company:");
        System.out.println(company.getName());
    }
}
