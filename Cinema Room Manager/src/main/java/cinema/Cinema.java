package cinema;

import java.util.Scanner;

public class Cinema {

    private final Theater theater;
    private final Scanner scanner;

    public Cinema() {
        this.scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numberOfRows = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeatsInRow = Integer.parseInt(scanner.nextLine());
        System.out.println();
        this.theater = new Theater(numberOfRows, numberOfSeatsInRow);
    }

    public static void main(String[] args) {
        new Cinema().start();
    }

    private void start() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        String select = scanner.nextLine();
        System.out.println();
        switch (select) {
            case "1":
                System.out.println(theater);
                start();
                break;
            case "2":
                new BoxOffice(theater).buyTicket();
                start();
                break;
            case "3":
                System.out.println(new Statistics(theater));
                start();
                break;
        }
    }
}