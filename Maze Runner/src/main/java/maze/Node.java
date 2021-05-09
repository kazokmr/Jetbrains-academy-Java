package maze;

import java.util.Objects;

public class Node {
    private final int row;
    private final int col;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return row == node.row &&
                col == node.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
