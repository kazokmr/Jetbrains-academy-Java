package maze;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final List<Vertex> vertexes = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();

    public Graph(int rowNum, int colNum) {
        int maxRowVertex = (int) Math.ceil((double) rowNum / 2);
        int maxColVertex = (int) Math.ceil((double) colNum / 2);
        for (int rowIndex = 0; rowIndex < maxRowVertex; rowIndex++) {
            for (int colIndex = 0; colIndex < maxColVertex; colIndex++) {
                vertexes.add(new Vertex(rowIndex, colIndex));
            }
        }

        for (Vertex vertex : vertexes) {
            // Right
            if (vertex.getColIndex() < maxColVertex - 1) {
                Vertex rightVertex = getVertexByGrid(vertex.getRowIndex(), vertex.getColIndex() + 1);
                edges.add(new Edge(vertex, rightVertex));
            }
            // Bottom
            if (vertex.getRowIndex() < maxRowVertex - 1) {
                Vertex bottomVertex = getVertexByGrid(vertex.getRowIndex() + 1, vertex.getColIndex());
                edges.add(new Edge(vertex, bottomVertex));
            }
        }
    }

    void runMinimumSpanningTree() {
        prim();
    }

    boolean isEdgeOfMst(int fromRow, int fromCol, int toRow, int toCol) {
        for (Edge edge : edges) {
            if (edge.isBetweenEdgeByGrid(fromRow, fromCol, toRow, toCol)) {
                return edge.isCurrentTree();
            }
        }
        // If this is false, there aren't any through in case of size of column is odd.
        return true;
    }

    void prim() {
        vertexes.get(0).setCurrentTree();
        while (true) {
            int candidateMinWeight = Integer.MAX_VALUE;
            Vertex candidateVertex = null;
            Edge candidateEdge = null;
            for (Vertex vertex : vertexes) {
                if (!vertex.isCurrentTree()) {
                    continue;
                }
                for (Vertex adjacent : getAdjacent(vertex)) {
                    if (adjacent == null || adjacent.isCurrentTree()) {
                        continue;
                    }
                    Edge edge = getEdge(vertex, adjacent);
                    if (edge == null || edge.isCurrentTree()) {
                        continue;
                    }
                    int weight = edge.getWeight();
                    if (weight < candidateMinWeight) {
                        candidateMinWeight = weight;
                        candidateVertex = adjacent;
                        candidateEdge = edge;
                    }
                }
            }
            if (candidateVertex == null) {
                break;
            }
            candidateVertex.setCurrentTree();
            candidateEdge.setCurrentTree();
        }
    }

    private Vertex getVertexByGrid(int row, int col) {
        for (Vertex vertex : vertexes) {
            if (vertex.isVertexByGrid(row, col)) {
                return vertex;
            }
        }
        return null;
    }

    private List<Vertex> getAdjacent(Vertex curVertex) {
        List<Vertex> adjacentVertexes = new ArrayList<>();
        for (Vertex vertex : vertexes) {
            if (curVertex.isAdjacent(vertex)) {
                adjacentVertexes.add(vertex);
            }
        }
        return adjacentVertexes;
    }

    private Edge getEdge(Vertex from, Vertex to) {
        for (Edge edge : edges) {
            if (edge.isBetweenEdge(from, to)) {
                return edge;
            }
        }
        return null;
    }
}
