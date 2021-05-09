package maze;

public class MazePrinter {
    private final int[][] blocks;

    MazePrinter(int[][] blocks) {
        this.blocks = blocks;
    }

    void printOut() {

        for (int[] rowBlocks : blocks) {
            for (int block : rowBlocks) {
                System.out.print(printBlock(block));
            }
            System.out.println();
        }
    }

    private String printBlock(int block) {
        return block == 1 ? "\u2588\u2588" : block == 2 ? "//" : "  ";
    }
}
