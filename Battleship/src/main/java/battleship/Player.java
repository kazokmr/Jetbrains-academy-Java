package battleship;

import java.util.Scanner;

public class Player {
    private final String name;
    private final Field myField;
    private final Field opponent;

    public Player(String name, Field myField, Field opponent) {
        this.name = name;
        this.myField = myField;
        this.opponent = opponent;
    }

    void ready() {
        System.out.printf("%s, place your ships on the game field\n", name);
        PlaceShip placeShip = new PlaceShip(myField);
        placeShip.start();
        System.out.println("Press Enter and pass the move to another player");
        new Scanner(System.in).nextLine();
    }

    void takeShots() {
        viewsFields();
        System.out.printf("%s, it's your turn:\n\n", name);
        shots();
        if (isWon()) {
            System.out.printf("You sank the last ship. %s won. Congratulations!\n", name);
        } else {
            System.out.println("Press Enter and pass the move to another player");
            new Scanner(System.in).nextLine();
        }
    }

    boolean isWon() {
        return !opponent.hasShips();
    }

    private void shots() {
        try {
            Coordinate coordinate = new Coordinate(new Scanner(System.in).nextLine());
            System.out.println();
            opponent.shotsTarget(coordinate);
        } catch (RuntimeException e) {
            System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            shots();
        }
    }

    private void viewsFields() {
        System.out.println();
        opponent.showsFieldWithoutShips();
        System.out.println("---------------------");
        myField.showsFieldWithShips();
        System.out.println();
    }
}
