//import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//
//public class Percolation {
//    private int maxSize;
//    public boolean[][] grid;
//    public Percolation(int N){
//        if (N <= 0){
//            throw new IllegalArgumentException();
//        }
//        WeightedQuickUnionUF wqu = new WeightedQuickUnionUF(N);
//        maxSize = N;
//        grid = new boolean[N][N];
//        for (int i = 0; i < N; i++){
//            for (int j = 0; j < N; j++){
//                grid[i][j] = false;
//            }
//        }
//    };
//        
//// create N-by-N grid, with all sites blocked
//    public void open(int i, int j){
//        if((i < 1 || i > maxSize) || (j < 1 || j > maxSize)){
//            throw new IndexOutOfBoundsException();
//        }
//        grid[i-1][j-1] = true;
//    }; // open site (row i, column j) if it is not already open
//    
//    public boolean isOpen(int i, int j){
//        if((i < 1 || i > maxSize) || (j < 1 || j > maxSize)){
//            throw new IndexOutOfBoundsException();
//        }
//        return grid[i-1][j-1];
//    }; // is site (row i, column j) open?
//    
//    public boolean isFull(int i, int j){
//        if((i < 1 || i > maxSize) || (j < 1 || j > maxSize)){
//            throw new IndexOutOfBoundsException();
//        }
//        if(!isOpen(i, j)){
//            return false; 
//        }
//        int[][] s2Test;
//        int[] a = {1, -1}; 
//        while(i > 1 || j > 1){
//            for(int q = 0; q < a.length; q++){
//            
//            }
//        }
//        return true;
//    }; // is site (row i, column j) full?
//    
//    public boolean percolates(){ return true;}; // does the system percolate?
//    
//    public static void main(String[] args){
//        Percolation percolator = new Percolation(5);
//        percolator.open(1, 1);
//    } // test client
//}