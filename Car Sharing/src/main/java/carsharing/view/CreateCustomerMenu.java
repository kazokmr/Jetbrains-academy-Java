package carsharing.view;

import carsharing.entity.Customer;
import carsharing.service.CustomerService;

import java.util.Scanner;

public class CreateCustomerMenu {

    private final CustomerService service;

    CreateCustomerMenu() {
        this.service = new CustomerService();
    }

    void prompt() {
        System.out.println("Enter the customer name:");
        String name = new Scanner(System.in).nextLine();
        service.save(new Customer(null, name));
        System.out.println("The customer was added!");
        System.out.println();
    }
}
