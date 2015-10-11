import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
   private int numTimes;
   private double numSites;
   private double[] fractions;
   public PercolationStats(int N, int T) {     // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
       numTimes = T;
       numSites = N * N;
       fractions = new double[T];
       int i = 0; 
       while (i < T) {
           double numSitesOpen = 0.0;
           Percolation percSystem = new Percolation(N);
           while (!percSystem.percolates()) {
               int row = StdRandom.uniform(N) + 1;
               int col = StdRandom.uniform(N) + 1;
               if (!percSystem.isOpen(row, col)) {
                   percSystem.open(row, col);
                   numSitesOpen += 1;
               }
           }
           double fraction = numSitesOpen / numSites;
           fractions[i] = fraction;
           i++;
       }
   }
   public double mean() { // sample mean of percolation threshold
       return StdStats.mean(fractions);
   };                      
   public double stddev() {                    // sample standard deviation of percolation threshold
       return StdStats.stddev(fractions);
   };
   public double confidenceLo() { // low  endpoint of 95% confidence interval
       return mean() - ((1.96 * stddev()) / Math.sqrt(numTimes));
   };              
   public double confidenceHi() { // high endpoint of 95% confidence interval
       return mean() + ((1.96 * stddev()) / Math.sqrt(numTimes));
   };             
   public static void main(String[] args) {
       PercolationStats percStats;
       percStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
       StdOut.print("mean                    = ");
       StdOut.println(percStats.mean());
       StdOut.print("stddev                  = ");
       StdOut.println(percStats.stddev());
       StdOut.print("95% confidence interval = ");
       StdOut.print(percStats.confidenceLo());
       StdOut.print(", ");
       StdOut.println(percStats.confidenceHi());
   };    // test client (described below)
}
