import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int maxSize;
    private int fullSize;
    private WeightedQuickUnionUF wqu;
    private boolean[] openSites;
    private int[][] adjacents = new int[4][2];
    
    private void calculateAdjacents(int i, int j){
        int[] a = {(i + 1), j};
        int[] b = {(i - 1), j};
        int[] c = {i, (j + 1)};
        int[] d = {i, (j - 1)};
        adjacents[0] = a;
        adjacents[1] = b;
        adjacents[2] = c;
        adjacents[3] = d;
    };
    
    private boolean isValidRowColumn(int i, int j){
      if((i < 1 || i > maxSize) || (j < 1 || j > maxSize)){
          return false;
      }
      return true;
    };
    
    private void isValid(int i, int j){
        if((i < 1 || i > maxSize) || (j < 1 || j > maxSize)){
            throw new IndexOutOfBoundsException();
        }
    };
    
    private int location(int i, int j){ //converts the row, grid to a number
        isValid(i, j);
        return ((i - 1) * maxSize + j);
    };

    public Percolation(int N){ // create N-by-N grid, with all sites blocked
        if (N <= 0){
            throw new IllegalArgumentException();
        }

        maxSize = N;
        fullSize = N * N + 2;
        
        wqu = new WeightedQuickUnionUF(fullSize);
        openSites = new boolean[fullSize];
        for (int i = 0; i < fullSize; i++) {
           openSites[i] = false;
        }
        openSites[0] = true;
        openSites[fullSize - 1] = true;
    };

    public void open(int i, int j){  // open site (row i, column j) if it is not already open
        isValid(i, j);
        int loc = location(i, j);
        openSites[loc] = true;
        
        if(i == 1){
            wqu.union(0, loc);
        }
        if(i == maxSize){
            wqu.union(loc, (fullSize - 1));
        }
           
        calculateAdjacents(i, j);
        
        for(int index = 0; index < adjacents.length; index++){
            if(isValidRowColumn(adjacents[index][0], adjacents[index][1])){
                int adjLoc = location(adjacents[index][0], adjacents[index][1]);
                if(openSites[adjLoc]){
                    wqu.union(loc, adjLoc);
                }
            }
        }
    };
    
    public boolean isOpen(int i, int j){  // is site (row i, column j) open?
        isValid(i, j);
        int loc = location(i, j);
        return openSites[loc];
    };
    
    public boolean isFull(int i, int j){ // is site (row i, column j) full?
        isValid(i, j);
        return wqu.connected(0, location(i, j));

    };

    public boolean percolates(){ // does the system percolate?
        return wqu.connected(0, ((maxSize * maxSize) + 1));
    }; 
    
    public static void main(String[] args){
        Percolation percolator = new Percolation(5);
        percolator.open(1, 1);
        percolator.open(2, 1);
        percolator.open(3, 1);
        percolator.open(4, 1);
        percolator.open(5, 1);
        System.out.println(percolator.isOpen(1, 1));//true
        System.out.println(percolator.isOpen(1, 2));//false
        System.out.println(percolator.isFull(1, 1));//true
        System.out.println(percolator.isFull(2, 1));//true
        System.out.println(percolator.isFull(1, 2));//false
        System.out.println(percolator.percolates());//true
        
    } // test client
}