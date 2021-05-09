package battleship;

public enum Symbol {
    FOG("~"),
    SHIP("O"),
    HIT("X"),
    MISS("M");

    private final String mark;

    Symbol(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}
