package battleship;

import java.util.Arrays;
import java.util.Objects;

import static battleship.Field.CODE_OF_ROW;

public class Coordinate {
    private final int rowPosition;
    private final int colPosition;

    public Coordinate(int rowPosition, int colPosition) {
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
    }

    Coordinate(String coordinate) {
        try {
            this.rowPosition = Arrays.binarySearch(CODE_OF_ROW, coordinate.charAt(0));
            this.colPosition = Integer.parseInt(coordinate.substring(1)) - 1;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            throw new RuntimeException();
        }
    }

    int getRowPosition() {
        return rowPosition;
    }

    int getColPosition() {
        return colPosition;
    }

    Direction getDirection(Coordinate to) {
        try {
            return Direction.getDirection(this, to);
        } catch (IllegalArgumentException e) {
            System.out.println("\nError! Wrong ship location! Try again:\n");
            throw new RuntimeException();
        }
    }

    Coordinate move(Direction direction) {
        return direction.move(this);
    }

    boolean isInField() {
        return colPosition >= 0 && colPosition < 10 && rowPosition >= 0 && rowPosition <= 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return rowPosition == that.rowPosition &&
                colPosition == that.colPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowPosition, colPosition);
    }
}
