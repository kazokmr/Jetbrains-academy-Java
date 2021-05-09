package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Fleet {
    private final List<Ship> ships = new ArrayList<>();

    void putShip(Ship ship) {
        ships.add(ship);
    }

    Ship getShipByCoordinate(Coordinate coordinate) {
        return ships.stream()
                .filter(s -> s.getCoordinate().equals(coordinate))
                .findFirst().orElse(null);
    }

    boolean existShips(Coordinate coordinate) {
        return Stream.of(Direction.values())
                .map(coordinate::move)
                .filter(c -> c.isInField())
                .anyMatch(c -> getShipByCoordinate(c) != null);
    }

    boolean areSameTypeShipsSank(Ship ship) {
        return ships.stream()
                .filter(s -> s.getTypeOfShip() == ship.getTypeOfShip())
                .allMatch(Ship::isHit);
    }

    boolean areAllShipsSank() {
        return ships.stream().allMatch(Ship::isHit);
    }
}
