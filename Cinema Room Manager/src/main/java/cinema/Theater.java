package cinema;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Theater {

    private final Boolean[][] seats;

    public Theater(int row, int col) {
        this.seats = new Boolean[row][col];
        Arrays.stream(this.seats).forEach(b -> Arrays.fill(b, false));
    }

    int numberOfRows() {
        return seats.length;
    }

    int numberOfSeatsInRow() {
        return seats[0].length;
    }

    int numberOfAllSeats() {
        return numberOfRows() * numberOfSeatsInRow();
    }

    void occupySeat(int numberOfRow, int numberOfSeatInRow) {
        seats[numberOfRow - 1][numberOfSeatInRow - 1] = true;
    }

    boolean isSeatOccupy(int numberOfRow, int numberOfSeatInRow) {
        return seats[numberOfRow - 1][numberOfSeatInRow - 1];
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder("Cinema:\n");
        // Header
        String headerRow = IntStream.rangeClosed(0, seats[0].length)
                .mapToObj(String::valueOf)
                .map(s -> s.equals("0") ? " " : s)
                .collect(Collectors.joining(" "));
        print.append(headerRow).append(System.lineSeparator());
        for (int i = 0; i < seats.length; i++) {
            String row = Arrays.stream(seats[i])
                    .map(s -> s ? "B" : "S")
                    .collect(Collectors.joining(" "));
            print.append(String.format("%d %s\n", i + 1, row));
        }
        return print.toString();
    }
}
