package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int length;
    private int numberOfOpenSites = 0;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int[] site;
    private int top = length * length;
    private int bottom = length * length + 1;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        length = N;
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 2);
        site = new int[N * N];
    }


    private int xyToID(int row, int col) {
        return row * length + col;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= length || col >= length) {
            throw new IndexOutOfBoundsException();
        }

        if (!isOpen(row, col)) {
            int id = xyToID(row, col);
            site[id] = 1;
            numberOfOpenSites += 1;
            unionOpen(row, col);
        }
    }

    private void unionOpen(int row, int col) {
        int id = xyToID(row, col);

        if (row == 0) {
            uf.union(top, id);
            uf2.union(top, id);
        }

        if (row == length - 1) {
            uf2.union(bottom, id);
        }

        int[][] temp = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] i : temp) {
            i[0] = i[0] + row;
            i[1] = i[1] + col;
            if (0 <= i[0] && i[0] < length && 0 <= i[1] && i[1] < length && isOpen(i[0], i[1])) {
                uf.union(id, xyToID(i[0], i[1]));
                uf2.union(id, xyToID(i[0], i[1]));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= length || col >= length) {
            throw new IndexOutOfBoundsException();
        }
        return site[xyToID(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= length || col >= length) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            return false;
        }
        int id = xyToID(row, col);
        return uf.connected(top, id);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return uf2.connected(top, bottom);
    }

    public static void main(String[] args) {}
    // use for unit testing (not required, but keep this here for the autograder)
}
