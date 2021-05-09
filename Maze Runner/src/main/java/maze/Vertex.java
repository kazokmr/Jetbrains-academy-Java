package maze;

import java.util.Objects;

public class Vertex {
    private final int rowIndex;
    private final int colIndex;
    private boolean currentTree = false;

    public Vertex(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public boolean isCurrentTree() {
        return currentTree;
    }

    public void setCurrentTree() {
        this.currentTree = true;
    }

    public boolean isAdjacent(Vertex adjacent) {
        int diffRow = Math.abs(adjacent.rowIndex - this.rowIndex);
        int diffCol = Math.abs(adjacent.colIndex - this.colIndex);
        return (diffRow == 0 && diffCol == 1) || (diffRow == 1 && diffCol == 0);
    }

    public boolean isVertexByGrid(int row, int col) {
        return rowIndex == row && colIndex == col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return rowIndex == vertex.rowIndex &&
                colIndex == vertex.colIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colIndex);
    }
}
