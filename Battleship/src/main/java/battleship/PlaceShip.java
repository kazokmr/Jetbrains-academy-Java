package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaceShip {
    private final Scanner scanner = new Scanner(System.in);
    private final Field field;

    public PlaceShip(Field field) {
        this.field = field;
    }

    void start() {
        System.out.println();
        field.showsFieldWithShips();
        System.out.println();
        TypeOfShip[] types = TypeOfShip.values();
        for (TypeOfShip type : types) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n\n", type.getName(), type.getNumberOfShips());
            putShips(type);
        }
    }

    private void putShips(TypeOfShip typeOfShip) {
        List<Coordinate> coordinateList = getCoordinatesByInput(typeOfShip);
        coordinateList.stream().map(c -> new Ship(typeOfShip, c)).forEach(field::putShip);
        System.out.println();
        field.showsFieldWithShips();
        System.out.println();
    }

    private List<Coordinate> getCoordinatesByInput(TypeOfShip typeOfShip) {
        List<Coordinate> coordinateList = new ArrayList<>();
        try {
            String[] positions = scanner.nextLine().split("\\s+");
            Coordinate from = new Coordinate(positions[0]);
            Coordinate to = new Coordinate(positions[1]);
            Direction direction = from.getDirection(to);
            Coordinate curCoordinate = from;
            for (int i = 0; i < typeOfShip.getNumberOfShips(); i++) {
                if (field.isPlaceable(curCoordinate)) {
                    coordinateList.add(curCoordinate);
                }
                curCoordinate = curCoordinate.move(direction);
            }
            if (!coordinateList.get(coordinateList.size() - 1).equals(to)) {
                System.out.printf("\nError! Wrong length of the %s! Try again:\n\n", typeOfShip.getName());
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            coordinateList = getCoordinatesByInput(typeOfShip);
        }
        return coordinateList;
    }
}
