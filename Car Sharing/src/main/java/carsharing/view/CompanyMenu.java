package carsharing.view;

import carsharing.entity.Company;
import carsharing.service.CompanyService;

import java.util.List;
import java.util.Scanner;

public class CompanyMenu {

    private final CompanyService service;

    CompanyMenu() {
        service = new CompanyService();
    }

    void prompt() {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        System.out.println();
        switch (command) {
            case "1":
                findCompanies();
                break;
            case "2":
                createCompany();
                break;
            case "0":
                return;
            default:
                break;
        }
        System.out.println();
        prompt();
    }

    private void findCompanies() {
        Company company = chooseCompany();
        if (company != null) {
            new CarMenu(company).prompt();
        }
    }

    private void createCompany() {
        System.out.println("Enter the company name:");
        Scanner scanner = new Scanner(System.in);
        Company company = new Company(null, scanner.nextLine());
        service.save(company);
        System.out.println("The company was created!");
    }

    Company chooseCompany() {
        List<Company> companyList = service.findAll();
        if (companyList.isEmpty()) {
            System.out.println("The company list is empty!");
            return null;
        }
        System.out.println("Choose the company:");
        companyList.forEach(company -> System.out.printf("%d. %s%n", company.getId(), company.getName()));
        System.out.println("0. Back");
        int number = Integer.parseInt(new Scanner(System.in).nextLine());
        System.out.println();
        if (number > 0 && number <= companyList.size()) {
            return companyList.get(number - 1);
        }
        return null;
    }
}
