package battleship;

public class Ship {
    private final TypeOfShip typeOfShip;
    private final Coordinate coordinate;
    private boolean hit;

    public Ship(TypeOfShip typeOfShip, Coordinate coordinate) {
        this.typeOfShip = typeOfShip;
        this.coordinate = coordinate;
        this.hit = false;
    }

    boolean isHit() {
        return hit;
    }

    void setHit() {
        this.hit = true;
    }

    TypeOfShip getTypeOfShip() {
        return typeOfShip;
    }

    Coordinate getCoordinate() {
        return coordinate;
    }
}
