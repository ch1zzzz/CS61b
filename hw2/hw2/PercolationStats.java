package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double[] threshold;
    private int length;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        threshold = new double[T];
        length = T;
        for (int i = 0; i < T; i++) {
            int count = 0;
            Percolation per = pf.make(N);
            while (!per.percolates()) {
                int temp1 = StdRandom.uniform(0, N);
                int temp2 = StdRandom.uniform(0, N);
                if (per.isOpen(temp1, temp2)) {
                    continue;
                }
                per.open(temp1, temp2);
                count += 1;
            }
            threshold[i] = (double) count / (double) (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return this.mean() - 1.96 * (this.stddev() / Math.sqrt(length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return this.mean() + 1.96 * (this.stddev() / Math.sqrt(length));
    }
}
