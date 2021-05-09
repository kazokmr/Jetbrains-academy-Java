package maze;

import java.util.ArrayDeque;
import java.util.Deque;

public class SolveMaze {
    private final int[][] maze;
    private final Deque<Node> deque;
    private final int rowSize;
    private final int colSize;

    public SolveMaze(int[][] maze) {
        this.maze = maze.clone();
        this.rowSize = maze.length;
        this.colSize = maze[0].length;
        this.deque = new ArrayDeque<>();
    }

    public int[][] getPath() {

        int startRow = 0;
        int endRow = 0;
        for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            if (maze[rowIndex][0] == 0) {
                startRow = rowIndex;
            }
            if (maze[rowIndex][colSize - 1] == 0) {
                endRow = rowIndex;
            }
        }
        Node startNode = new Node(startRow, 0);
        Node goalNode = new Node(endRow, colSize - 1);
        if (searchPath(startNode, goalNode)) {
            deque.offerLast(startNode);
        }
        while (deque.peekLast() != null) {
            Node node = deque.pollLast();
            maze[node.getRow()][node.getCol()] = 2;
        }
        return maze;
    }

    private boolean searchPath(Node start, Node goal) {
        if (start.equals(goal)) {
            deque.offerLast(goal);
            return true;
        }
        int curRow = start.getRow();
        int curCol = start.getCol();
        deque.offerLast(start);
        // top
        if (curRow > 0 && maze[curRow - 1][curCol] == 0) {
            Node nextNode = new Node(curRow - 1, curCol);
            if (!deque.contains(nextNode) && searchPath(nextNode, goal)) {
                return true;
            }
        }
        // bottom
        if (curRow < rowSize - 1 && maze[curRow + 1][curCol] == 0) {
            Node nextNode = new Node(curRow + 1, curCol);
            if (!deque.contains(nextNode) && searchPath(nextNode, goal)) {
                return true;
            }
        }
        // left
        if (curCol > 0 && maze[curRow][curCol - 1] == 0) {
            Node nextNode = new Node(curRow, curCol - 1);
            if (!deque.contains(nextNode) && searchPath(nextNode, goal)) {
                return true;
            }
        }
        // Right
        if (curCol < colSize - 1 && maze[curRow][curCol + 1] == 0) {
            Node nextNode = new Node(curRow, curCol + 1);
            if (!deque.contains(nextNode) && searchPath(nextNode, goal)) {
                return true;
            }
        }
        deque.remove(start);
        return false;
    }
}
