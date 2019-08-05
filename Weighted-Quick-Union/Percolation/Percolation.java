//package test;




import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grids;
    private int comp = 3;
    private WeightedQuickUnionUF qu;
    private int dim;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        dim = n;
        boolean[][] ns = new boolean[n][n];
        grids = ns;
        qu = new WeightedQuickUnionUF(n * n);
    }

    private boolean hasleft(int row, int col) {
        return (col != 1);
    }

    private boolean hasright(int row, int col) {
        return (col != dim);
    }

    private boolean hastop(int row, int col) {
        return (row != 1);
    }

    private boolean hasbot(int row, int col) {
        return (row != dim);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            grids[row - 1][col - 1] = true;


        }
        if (hasleft(row, col)) {
            if (isOpen(row, col - 1)) {
                qu.union((row - 1) * dim + col - 1, (row - 1) * dim + col - 2);
            }
        }
        if (hasright(row, col)) {
            if (isOpen(row, col + 1)) {
                qu.union((row - 1) * dim + col - 1, (row - 1) * dim + col);
            }
        }
        if (hastop(row, col)) {
            if (isOpen(row - 1, col)) {
                qu.union((row - 1) * dim + col - 1, (row - 2) * dim + col - 1);
            }
        }
        if (hasbot(row, col)) {
            if (isOpen(row + 1, col)) {
                qu.union((row - 1) * dim + col - 1, row * dim + col - 1);
            }
        }



    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grids[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        for (int i = 0; i < dim; i++) {
            if (isOpen(1, i + 1)) {
                //System.out.println((row - 1) * dim + col - 1);
                if (qu.connected(i, (row - 1) * dim + col - 1)) {
                    return true;

                }
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {

        int total = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (isOpen(i + 1, j + 1)) {
                    total++;
                }
            }
        }
        return total;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < dim; i++) {
            if (isFull(dim, i + 1)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Percolation a = new Percolation(20);
        for (int i = 0; i < 19; i++) {
            a.open(i + 1, 3);
        }
        System.out.println(a.numberOfOpenSites());

    }
}
