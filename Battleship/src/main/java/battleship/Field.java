package battleship;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static battleship.Symbol.*;

public class Field {
    public static final char[] CODE_OF_ROW = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private final Symbol[][] fieldWithShips = new Symbol[10][10];
    private final Symbol[][] fieldWithoutShips = new Symbol[10][10];
    private final Fleet fleet = new Fleet();

    public Field() {
        Stream.of(fieldWithShips).forEach(r -> Arrays.fill(r, FOG));
        Stream.of(fieldWithoutShips).forEach(r -> Arrays.fill(r, FOG));
    }

    void putShip(Ship ship) {
        fleet.putShip(ship);
        Coordinate coordinate = ship.getCoordinate();
        fieldWithShips[coordinate.getRowPosition()][coordinate.getColPosition()] = SHIP;
    }

    boolean isPlaceable(Coordinate coordinate) {
        if (fleet.existShips(coordinate)) {
            System.out.println("\nError! You placed it too close to another one. Try again:\n");
            throw new RuntimeException();
        }
        return true;
    }

    boolean hasShips() {
        return !fleet.areAllShipsSank();
    }

    void shotsTarget(Coordinate coordinate) {
        Symbol symbol = fieldWithShips[coordinate.getRowPosition()][coordinate.getColPosition()];
        Ship ship = fleet.getShipByCoordinate(coordinate);
        String message;
        switch (symbol) {
            case SHIP:
                marksResult(coordinate, HIT);
                ship.setHit();
                message = fleet.areSameTypeShipsSank(ship) ? "You sank a ship! Specify a new target:" : "You hit a ship! Try again:";
                break;
            case HIT:
                message = "You hit a ship! Try again:";
                break;
            default:
                marksResult(coordinate, MISS);
                message = "You missed. Try again:";
        }
        System.out.println(message);
    }

    private void marksResult(Coordinate coordinate, Symbol symbol) {
        fieldWithoutShips[coordinate.getRowPosition()][coordinate.getColPosition()] = symbol;
        fieldWithShips[coordinate.getRowPosition()][coordinate.getColPosition()] = symbol;
    }

    void showsFieldWithShips() {
        System.out.printf("%s\n", display(fieldWithShips));
    }

    void showsFieldWithoutShips() {
        System.out.printf("%s\n", display(fieldWithoutShips));
    }

    private String display(Symbol[][] field) {
        StringBuilder display = new StringBuilder("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < field.length; i++) {
            display.append(System.lineSeparator()).append(CODE_OF_ROW[i]).append(" ");
            display.append(Stream.of(field[i]).map(Symbol::getMark).collect(Collectors.joining(" ")));
        }
        return display.toString();
    }
}
