package carsharing.view;

import carsharing.entity.Customer;
import carsharing.service.CustomerService;

import java.util.List;
import java.util.Scanner;

public class CustomerListMenu {
    private final CustomerService service;

    CustomerListMenu() {
        this.service = new CustomerService();
    }

    void prompt() {
        List<Customer> customerList = service.findAll();
        if (customerList.isEmpty()) {
            System.out.println("The customer list is empty!\n");
            return;
        }
        System.out.println("Customer list:");
        customerList.forEach(customer -> System.out.printf("%d. %s\n", customer.getId(), customer.getName()));
        System.out.println("0. Back");
        int input = Integer.parseInt(new Scanner(System.in).nextLine());
        System.out.println();
        if (input > 0 && input <= customerList.size()) {
            Customer customer = customerList.get(input - 1);
            new CustomerMenu(customer).prompt();
        }
    }
}
