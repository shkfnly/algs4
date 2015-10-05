import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   private double sumFractions;
   private int numTimes;
   private double[] fractions;
   
   public PercolationStats(int N, int T){     // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0){
            throw new IllegalArgumentException();
        }
       
       numTimes = T;
       
       Percolation percSystem = new Percolation(N);
       int i = 0;
       while(i < T){
           
       
       }
   }
   public double mean(){ // sample mean of percolation threshold
       return sumFractions/numTimes;
   };                      
   public double stddev(){                    // sample standard deviation of percolation threshold
       double currentSum = 0;
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
   };    // test client (described below)
}
