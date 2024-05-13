package leetcode;

/**
 * 766. Toeplitz Matrix
 * https://leetcode.com/problems/toeplitz-matrix/description/
 * 
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise,
 * return false.
 * 
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the
 * same elements.
 * 
 * 1 | 2 | 3 | 4
 * 5 | 1 | 2 | 3
 * 9 | 5 | 1 | 2
 * 
 * is a valid Toeplitz matrix.. all diagonal values are the same.
 * 
 * Answer:
 * Iterate starting at row 1 and column 1, comparing each value to the value above and to the left one
 * position.  If values are different the matrix is not valid.
 * 
 * Complexity: O(MxN) where m is the number of rows and N is the number of columns
 * Storage: O(1), no additional storage is needed.
 */
public class Matrix_Toeplitz {
    
    public static void main(String[] args) { 
        Matrix_Toeplitz test = new Matrix_Toeplitz();
        boolean result = test.isToeplitzMatrix( new int [][] {{1,2,3,4},{5,1,2,3},{9,5,1,2}});
        System.err.println(result);
    }
    
    public boolean isToeplitzMatrix(int[][] matrix) {        
        //loop through each row/column, comparing the current value to it's top left
        //value
        for(int row = 1; row < matrix.length; row++) { 
            for(int col = 1; col < matrix[0].length; col++) {                
               if(matrix[row][col] != matrix[row-1][col-1]) { 
                   return false;
               }
            }
        }
        return true;
    }
}
