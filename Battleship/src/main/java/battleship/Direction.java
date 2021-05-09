package battleship;

public enum Direction {

    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    STAY(0, 0);

    private final int shiftOfX;
    private final int shiftOfY;

    Direction(int shiftOfX, int shiftOfY) {
        this.shiftOfX = shiftOfX;
        this.shiftOfY = shiftOfY;
    }

    public static Direction getDirection(Coordinate from, Coordinate to) {
        if (from.getRowPosition() != to.getRowPosition() && from.getColPosition() != to.getColPosition()) {
            throw new IllegalArgumentException();
        }
        if (from.getRowPosition() == to.getRowPosition() && from.getColPosition() < to.getColPosition()) {
            return RIGHT;
        }
        if (from.getRowPosition() == to.getRowPosition() && from.getColPosition() > to.getColPosition()) {
            return LEFT;
        }
        if (from.getRowPosition() < to.getRowPosition()) {
            return DOWN;
        }
        if (from.getRowPosition() > to.getRowPosition()) {
            return UP;
        }
        return STAY;
    }

    public Coordinate move(Coordinate coordinate) {
        return new Coordinate(coordinate.getRowPosition() + shiftOfY, coordinate.getColPosition() + shiftOfX);
    }
}
