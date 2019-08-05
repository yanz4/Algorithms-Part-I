//import test.Percolation;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
    private Percolation qu;
    private double[] p;
    private int cnt = 0;
    private int t;
    private double dim;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        p = new double[trials];
        t = trials;
        dim = n;
        if (n <= 0 || t <= 0)
            throw new IllegalArgumentException("N and T should be > 0");
        for (int i = 0; i < trials; i++) {
            qu = new Percolation(n);
            while (!qu.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                qu.open(row, col);

            }
            p[i] = qu.numberOfOpenSites() / dim / dim;


        }

    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(p);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(p);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(t));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(t));

    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats pStats = new PercolationStats(N, T);

        String confidence = pStats.confidenceLo() + ", "
                + pStats.confidenceHi();
        StdOut.println("mean                    = " + pStats.mean());
        StdOut.println("stddev                  = " + pStats.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
