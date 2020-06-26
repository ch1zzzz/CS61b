package hw2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int length;
    private int numberOfOpenSites = 0;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufNoBottom;
    private boolean[][] grid;
    private int top;
    private int bottom;
    private int[][] surround = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        length = N;
        top = 0;
        bottom = N * N + 1;
        ufNoBottom = new WeightedQuickUnionUF(N * N + 1);
        uf = new WeightedQuickUnionUF(N * N + 2);
        grid = new boolean[N][N];
    }


    private int xyToID(int row, int col) {
        return row * length + col + 1;
    }

    private void validate(int row, int col) {
        if (row < 0 || col < 0 || row >= length || col >= length) {
            throw new IndexOutOfBoundsException();
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numberOfOpenSites += 1;
            unionOpen(row, col);
        }
    }

    private void unionOpen(int row, int col) {
        int id = xyToID(row, col);

        if (row == 0) {
            uf.union(top, id);
            ufNoBottom.union(top, id);
        }

        if (row == length - 1) {
            uf.union(bottom, id);
        }

        for (int[] i : surround) {
            int adjacentRow = i[0] + row;
            int adjacentCol = i[1] + col;
            if (0 <= adjacentRow && adjacentRow < length && 0 <= adjacentCol
                    && adjacentCol < length && isOpen(adjacentRow, adjacentCol)) {
                uf.union(id, xyToID(adjacentRow, adjacentCol));
                ufNoBottom.union(id, xyToID(adjacentRow, adjacentCol));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int id = xyToID(row, col);
        return ufNoBottom.connected(top, id);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    public static void main(String[] args) {

    }
    // use for unit testing (not required, but keep this here for the autograder)
}
