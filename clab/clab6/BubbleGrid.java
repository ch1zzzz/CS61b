import java.util.Arrays;

public class BubbleGrid {
    int[][] grid;
    int rows;
    int columns;



    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        columns = grid[0].length;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int size = darts.length;

        // the return array of bubble fall after dart.
        int[] bubbleFall = new int[size];

        // use the disjointed set to help , +1 means uf0 will set to be ceiling.
        UnionFind uf = new UnionFind(rows * columns + 1);

        // replace all the shot bubble from 1 to 2.
        for (int[] dart : darts) {
            if (grid[dart[0]][dart[1]] == 1) {
                grid[dart[0]][dart[1]] = 2;
            }
        }

        // uf[0] set to be ceiling, union 0 row's bubble to ceiling(0).
        for (int j = 0; j<columns; j++) {
            if (grid[0][j] == 1) {
                uf.union(0, j + 1);
            }
        }

        // union all the bubble after all dart was shot.
        for (int i = 0; i<rows; i++) {
            for (int j = 0 ;j<columns; j++) {
                if (grid[i][j] == 1) {
                    unionRound(i, j, grid, uf);
                }
            }
        }

        // setSize is the total size of bubble not fallen.
        int setSize = uf.sizeOf(0) - 1;

        // reversely put 1 back to the position that was be shot. the difference between
        // size is the fallen value after i th dart.
        for (int i = darts.length - 1; i>-1; i--) {

            int shootX = darts[i][0];
            int shootY = darts[i][1];

            if (grid[shootX][shootY] == 2) {
                unionRound(shootX, shootY, grid, uf);
                grid[shootX][shootY] = 1;
            }
            int newSize = uf.sizeOf(0) - 1;
            // ? means if it is true then run the left command otherwise run the right.
            // it may be 0 fallen bubble after shot.
            bubbleFall[i] = newSize > setSize ? newSize - setSize - 1 : 0;
            setSize = newSize;
        }

        return bubbleFall;

    }

    /** Union grid[i][j] with the bubble around it.
     *  if it's in the top row then firstly connect it with ceiling(0). */
    public void unionRound(int i, int j, int[][] grid, UnionFind uf) {
        int[][] round = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        if (i == 0) {
            uf.union(0, gridToUf(i, j));
        }
        for (int[] direction : round) {
            int roundx = i + direction[0];
            int roundy = j + direction[1];
            if (-1<roundx && roundx<rows &&
                -1<roundy && roundy<columns && grid[roundx][roundy] == 1) {
                int indexRound = gridToUf(roundx, roundy);
                int index = gridToUf(i, j);
                uf.union(index, indexRound);
            }
        }
    }

    /** return the index of the bubble in uf. */
    public int gridToUf(int row, int column) {
        return row * columns + column + 1;
    }
}
