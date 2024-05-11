package leetcode;

/**
 * 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/description/
 * 
 * Given an M x N 2D binary grid grid which represents a map of '1's (land) and
 * '0's (water), return the number of islands.
 * 
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all
 * surrounded by water.
 * 
 * Answer:
 * We only need to find the number of '1's surrounded by water; therefore we iterate
 * through each row and column - when we find a land '1' we will do a BFS in 4 directions;
 * any attached land '1' will be updated to 2, indicating it's attached.  
 * 
 * After updating, we continue to traverse through the matrix - if we find another '1' we
 * can assume it's new / isn't attached to previously found land, and start the process again.
 * 
 * Time complexity: O(mn) where m is the number of rows and n is the number of columns
 * Space complexity: O(1) no additional space is needed.
 */

public class Matrix_NumberOfIslands {
 
    public int numIslands(char[][] grid) {
        //a track of the first land we see in each row/ column.  
        //Any attached land to the 'first' will be traversed and not counted against
        //the number total.
        int numberIslands = 0;
        
        //run through each row and column, O(RxC)
        for(int row = 0; row < grid.length; row ++) {
            char[] _row = grid[row];
            for(int column = 0; column < _row.length; column++) {
                if(grid[row][column] == '1') { 
                    updateAttached(row, column, grid);
                    numberIslands +=1;
                }
            }
        }

        return numberIslands;
    }
    private void updateAttached(int row, int column, char[][] grid) {           
        //skip cells outside of bounds
        if(row < 0 || column < 0 || row >= grid.length || column >= grid[0].length) { 
            return;
        }
        
        //any attached land should be set to '2', indicating that it's been visited 
        //and counted against our island 'group'
        if(grid[row][column] == '1') {            
            grid[row][column] = '2';
            
            //traverse all other nodes, marking as visited
            //note that prior to adding to our DFS, check the value of the next cell (which runs in O(1))
            //this prevent unecessary additions to the stack which can be expensive
            if(column+1 < grid[0].length && grid[row][column+1] == '1') { 
                updateAttached(row, column + 1, grid);  //right
            }
            if(column-1 >= 0 && grid[row][column-1] == '1') {
                updateAttached(row, column - 1, grid);  //left
            }
            if(row+1 < grid.length && grid[row+1][column] == '1') {
                updateAttached(row + 1, column, grid);  //down
            }
            if(row-1 >=0 && grid[row-1][column] == '1') {
                updateAttached(row - 1, column, grid);  //up
            }
        }
    }
}
