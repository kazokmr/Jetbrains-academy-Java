package cinema;

import java.util.stream.IntStream;

public class Statistics {
    private final Theater theater;
    private final BoxOffice boxOffice;

    public Statistics(Theater theater) {
        this.theater = theater;
        this.boxOffice = new BoxOffice(theater);
    }

    private int getPurchasedTickets() {
        return IntStream.rangeClosed(1, theater.numberOfRows())
                .map(row -> (int) IntStream.rangeClosed(1, theater.numberOfSeatsInRow())
                        .filter(seat -> theater.isSeatOccupy(row, seat))
                        .count())
                .sum();
    }

    private double getOccupancy() {
        double sum = theater.numberOfRows() * theater.numberOfSeatsInRow();
        double occupy = getPurchasedTickets();
        return (occupy / sum) * 100;
    }

    private int getCurrentIncome() {
        return IntStream.rangeClosed(1, theater.numberOfRows())
                .map(row -> (int) (IntStream.rangeClosed(1, theater.numberOfSeatsInRow())
                        .filter(seat -> theater.isSeatOccupy(row, seat))
                        .count() * boxOffice.getPriceByRow(row)))
                .sum();
    }

    private int getTotalIncome() {
        return IntStream.rangeClosed(1, theater.numberOfRows())
                .map(boxOffice::getPriceByRow)
                .sum() * theater.numberOfSeatsInRow();
    }

    @Override
    public String toString() {
        return "Number of purchased tickets: " + getPurchasedTickets() + "\n" +
                "Percentage: " + String.format("%.2f", getOccupancy()) + "%" + "\n" +
                "Current income: " + "$" + getCurrentIncome() + "\n" +
                "Total income: " + "$" + getTotalIncome() + "\n";
    }
}
