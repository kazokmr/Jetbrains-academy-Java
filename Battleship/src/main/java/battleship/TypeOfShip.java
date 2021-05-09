package battleship;

public enum TypeOfShip {

    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    private final String name;
    private final int numberOfShips;

    TypeOfShip(String name, int numberOfShips) {
        this.name = name;
        this.numberOfShips = numberOfShips;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfShips() {
        return numberOfShips;
    }
}
