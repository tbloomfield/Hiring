package leetcode;

import java.util.Arrays;

/**
 * 498.  Diagonal Traverse
 * https://leetcode.com/problems/diagonal-traverse/description/
 * 
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 * 
 * 1 | 2 | 3
 * 4 | 5 | 6
 * 7 | 8 | 9
 * 
 * 
 * 2 | 5
 * 8 | 0
 * 4 | -1
 * 
 * would yield 1,2,4,7,5,3,8,6,9
 */
public class Matrix_DiagonalTraverse {
    
    public static void main(String[] args) { 
        Matrix_DiagonalTraverse test = new Matrix_DiagonalTraverse();
        //var results = test.findDiagonalOrder(new int[][] {{1,2,3},{4,5,6},{7,8,9}});
        var results = test.findDiagonalOrder(new int[][] {{2,5},{8,0},{4,-1}});
        //var results = test.findDiagonalOrder(new int[][] {{1,2},{3,4}});
        System.err.println(Arrays.toString(results));
    }

    public int[] findDiagonalOrder(int[][] mat) {
        
        //int directionChanges = 0; //a count of direction changes while traversing
        int cellsEvaluated = 0;
        int row = 0; 
        int col = 0;        
        int[] traverseResults = new int[mat.length * mat[0].length];
        
        while(cellsEvaluated < mat.length * mat[0].length) {
            //get the current value
            traverseResults[cellsEvaluated] = mat[row][col];
            
            //check direction we're going (up & right) or (down & left)
            //by comparing cell index sums.  
            // Up/Right = 0,0(even).  
            // Down/left = {0,1} and {1,0} (odd
                                    
            //set the direction of traversal: up and right:           
            if((row+col) % 2 == 0) {
            
                if(col == mat[0].length-1){  //right border bounce 
                    row++;                    
                } else if(row == 0) {   //bounce off top
                    col ++;
                } else {
                    //up and right
                    row --;
                    col ++;
                }
                
            } 
            //odd moves down and left
            else {
                if(row == mat.length-1) { //bottom border bounce
                    col++;                    
                } else if(col == 0) {           //left border bounce
                    row ++;                    
                } else {
                  //down and left
                  row ++;
                  col --; 
                }
            }
            
            cellsEvaluated ++;
        }
        
        return traverseResults;
    }
}
