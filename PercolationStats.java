import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   private int numTimes;
   private double numSites;
   private double sumFractions;
   private double[] fractions;

   
   public PercolationStats(int N, int T){     // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
       numTimes = T;
       numSites = Math.pow(N, 2);
       sumFractions = 0.0;
       fractions = new double[T];
       int i = 0;
       
       StdRandom.setSeed(N + 1);
       
       while(i < T){
           double numSitesOpen = 0.0;
           Percolation percSystem = new Percolation(N);
           while(!percSystem.percolates()){
               int row = StdRandom.uniform(N) + 1;
               int col = StdRandom.uniform(N) + 1;
               if(!percSystem.isOpen(row, col)){
                   percSystem.open(row, col);
                   numSitesOpen += 1;
               }
           }
           double fraction = numSitesOpen / numSites;
           fractions[i] = fraction;
           sumFractions += fraction;
           i++;
       }
   }
   public double mean(){ // sample mean of percolation threshold
       return sumFractions/numTimes;
   };                      
   public double stddev(){                    // sample standard deviation of percolation threshold
       double currentSum = 0.0;
       double currMean = mean();
       for(int i = 0; i < fractions.length; i++){
           currentSum = currentSum + Math.pow((fractions[i] - currMean), 2);
       }
       return currentSum / (numTimes - 1);
   };
   public double confidenceLo(){ // low  endpoint of 95% confidence interval
       return mean() - ( (1.96 * Math.sqrt(stddev())) / Math.sqrt(numTimes));
   };              
   public double confidenceHi(){ // high endpoint of 95% confidence interval
       return mean() + ( (1.96 * Math.sqrt(stddev())) / Math.sqrt(numTimes));
   };             

   public static void main(String[] args){
       PercolationStats percStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
       System.out.print("mean                    = ");
       System.out.println(percStats.mean());
       System.out.print("stddev                  = ");
       System.out.println(percStats.stddev());
       System.out.print("95% confidence interval = ");
       System.out.print(percStats.confidenceLo());
       System.out.print(", ");
       System.out.println(percStats.confidenceHi());
       
   };    // test client (described below)
}
