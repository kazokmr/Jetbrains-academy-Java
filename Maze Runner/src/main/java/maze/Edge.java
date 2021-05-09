package maze;

import java.util.Random;

public class Edge {
    private final Vertex vertex1;
    private final Vertex vertex2;
    private final int weight;
    private boolean currentTree = false;

    public Edge(Vertex vertex1, Vertex vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = new Random().nextInt(10) + 1;
    }

    public boolean isBetweenEdge(Vertex from, Vertex to) {
        if (from.equals(vertex1) && to.equals(vertex2)) {
            return true;
        } else {
            return from.equals(vertex2) && to.equals(vertex1);
        }
    }

    public boolean isBetweenEdgeByGrid(int fromRow, int fromCol, int toRow, int toCol) {
        return isBetweenEdge(new Vertex(fromRow, fromCol), new Vertex(toRow, toCol));
    }

    public int getWeight() {
        return weight;
    }

    public boolean isCurrentTree() {
        return currentTree;
    }

    public void setCurrentTree() {
        this.currentTree = true;
    }
}
