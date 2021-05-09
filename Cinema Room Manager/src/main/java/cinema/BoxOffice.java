package cinema;

import java.util.Scanner;

public class BoxOffice {

    private final Theater theater;

    public BoxOffice(Theater theater) {
        this.theater = theater;
    }

    void buyTicket() {
        Scanner scanner = new Scanner(System.in);
        int numberOfRow;
        int numberOfSeatInRow;
        while (true) {
            System.out.println("Enter a row number:");
            numberOfRow = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter a seat number in that row:");
            numberOfSeatInRow = Integer.parseInt(scanner.nextLine());
            if (numberOfRow > theater.numberOfRows() || numberOfSeatInRow > theater.numberOfSeatsInRow()) {
                System.out.println("\nWrong input!\n");
                continue;
            }
            if (theater.isSeatOccupy(numberOfRow, numberOfSeatInRow)) {
                System.out.println("\nThat ticket has already been purchased!\n");
                continue;
            }
            break;
        }
        scanner.close();
        System.out.printf("\nTicket price: $%d\n\n", getPriceByRow(numberOfRow));
        theater.occupySeat(numberOfRow, numberOfSeatInRow);
    }

    int getPriceByRow(int row) {
        if (theater.numberOfAllSeats() < 60) {
            return 10;
        }
        return row <= theater.numberOfRows() / 2 ? 10 : 8;
    }
}
