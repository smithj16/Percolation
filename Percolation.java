

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Percolation{

    int numOfOpenSites = 0;
    int startRow, startColumn;
    int nextRow = 0, nextCol = 0;
    boolean[][] twoDArray;
    boolean[][] visitedArray;
    int[] directionRow = {-1,1,0,0};
    int[] directionColumns = {0,0,-1,1};
    Queue<Integer> rowQueue = new LinkedList<Integer>();
    Queue<Integer> colQueue = new LinkedList<Integer>();

    public Percolation(int N){
        if ( N > 0){
            /*creates two boolean 2D arrays
           one for percolation system and one to keep track of visited sites
           in the first array
            */

            twoDArray = new boolean[N][N];
            visitedArray = new boolean[N][N];
        }

   else
         throw new java.lang.IllegalArgumentException();

    }


    public void open(int i, int j){
        // if index is in bound, flip false value to true value

        if((i >= 0 && i <= twoDArray.length - 1) && (j >= 0 && j <= twoDArray.length - 1)){
            twoDArray[i][j] = true;
            numOfOpenSites++;
        }
    else
        throw new java.lang.IndexOutOfBoundsException();

    }

    public boolean isOpen(int i, int j){
        // returns truth value at that index of the array

        boolean ans = false;
        if((i >= 0 && i < twoDArray.length) && (j >= 0 && j < twoDArray.length)){
            ans = twoDArray[i][j];
        }
       else {
           //System.out.println("Inside Else statement");
            throw new java.lang.IndexOutOfBoundsException();
        }
       return ans;
    }

    public int numberOfOpenSites(){
        return numOfOpenSites;
    }

    public void setNextRow(int newRow){

    }

    public boolean isFull(int i, int j){
         /*Creates variables to represent the starting point in our
       array
        */

        int x = -1, y;
        startRow = i;
        startColumn = j;
        // add the starting row to its queue and starting columns to it queue

        rowQueue.add(startRow);
        colQueue.add(startColumn);
        // mark visited site
        visitedArray [i][j] = true;

         /*
       while both queues are not empty, visit the neighboring sites if:
       1. index is inbounds
       2. is open
       This is the breadth first search algorithm
        */

        while (!rowQueue.isEmpty() && !colQueue.isEmpty()){
            x = rowQueue.remove();
            //System.out.println(x);
            y = colQueue.remove();
            //System.out.println(y);

            for (int u = 0; u < 4; u++ ){

                // if the neighbor sites are within the 2D array, check if sites are open and not visited
                if (x + directionRow[u] >= 0 && x + directionRow[u] < twoDArray.length )
                    nextRow = x + directionRow[u];

                if (y + directionColumns[u] >= 0 && y + directionColumns[u] < twoDArray.length)
                    nextCol = y + directionColumns[u];


                if (isOpen(nextRow,nextCol) && (!visitedArray[nextRow][nextCol])){

                    rowQueue.add(nextRow);
                    colQueue.add(nextCol);
                    visitedArray[nextRow][nextCol] = true;

                }

            }


        }


        return x == twoDArray.length - 1;

    }

    public void percolates(){
        int i = 0;
        boolean result = false;

         /*
       - loop through the first row of the array until an open site is visited
       - call isFull function
       - if the last site visited belongs to the last row in the array
         the system percolates
       - if there doesn't exist a site that is open in the first or last row of
         the array, the system does not percolate
        */

        for (int j = 0; j < twoDArray.length; j++ ){
            if (isOpen(i,j)){

                result =  isFull(i,j);
                if (result){
                    System.out.print("The system percolate.");
                    break;
                }
            }
        }
        if (!result){
            System.out.println("The system does not percolate.");
        }


        System.out.println("\n" + "The number of open sites are " + numberOfOpenSites());
    }



    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
      if (args.length > 0){

          for(int counter = 0; counter < args.length; counter++) {
              File file = new File(args[counter]);
              Scanner input = new Scanner(file);
              int size = input.nextInt();
              Percolation obj = new Percolation(size);

              for (; input.hasNext(); ) {
                  int row = input.nextInt(), col = input.nextInt();
                  obj.open(row, col);
              }

              obj.percolates();
              System.out.println("\n");
          }
        }

    }

}




