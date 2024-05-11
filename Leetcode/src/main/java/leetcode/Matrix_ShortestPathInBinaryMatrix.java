package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1091. Shortest path in a binary matrix 
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/description/
 * 
 * Given an n x n binary matrix grid, return the length of the shortest clear
 * path in the matrix. If there is no clear path, return -1.
 * 
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0,
 * 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * 
 * - All the visited cells of the path are 0. 
 * - All the adjacent cells of the path are 8-directionally connected (i.e., they are 
 * different and they share an edge or a corner). 
 * - The length of a clear path is the number of visited cells of this path.
 * 
 * Solution: 
 * Traverse the matrix, starting at 0,0, visiting each cell's neighbors by putting 
 * neighbors onto a queue of cells to evaluate.  To indicate that a cell has been
 * evaluated, we can change the cell value to "1", or "blocked"; this way a cell doesn't 
 * get evaluated multiple times.
 * 
 * For each time we add an element to the queue of cells to evaluate we'll increment
 * a "move counter".  If we reach the bottom right position, we will return the total
 * move counter.
 * 
 * Complexity: O(n) - we visit each matrix cell once.  For every cell we evaluate 
 * whether its 8 neighbors are 0 (open) and should be evaluated; looking at the 
 * neighbor values is a O(1) lookup.  So in total we'll loop at most O(n) times.
 * 
 * Space complexity: O(n) in the worst case, we will add every cell into the queue
 * to visit.  We overwrite the matrix array in-place.  
 * 
 * If we didn't want to overwrite the array, we could use a "visited" set, which would 
 * require an additional O(n) space.
 */
public class Matrix_ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {    
        //breadth first search for the max position
        int boundary = grid.length-1;
        int[][] moves = new int[][]  { {0,1}, {1,0}, {1,1}, {0,-1}, {-1,0}, {-1,-1}, {-1,1}, {1,-1}};

        //if start or ending positions are 1 (blocked) we can return immediately
        //as those positions are unreachable
        if(grid[0][0] == 1 || grid[boundary][boundary]==1){ 
            return -1;
        }

        Deque<int[]> tries = new ArrayDeque<>();
        tries.offerFirst(new int[]{0,0,1}); //y,x,#of moves
        grid[0][0] = 1;  //don't re-use this cell 

        while(!tries.isEmpty()) {
            
            int[] currentCell = tries.pollFirst();            
            int currentRow = currentCell[0];
            int currentCol = currentCell[1];
            int moveTotal  = currentCell[2];

            //at bottom right
            if(currentCol == boundary && currentRow == boundary){  
                //since BFS searches all directions with same speed, the first
                //entry that makes it to the bottom is the fastest.               
                return moveTotal;
            }

            //try all directions from current
            //O(nm)
            for(var direction : moves){ 
                //boundary check to ensure we're not out of bounds of our matrix.
                int x = direction[0];
                int y = direction[1];
                int newCol = currentCol + x;
                int newRow = currentRow + y;

                //right/left out of bounds, not a valid move
                if(newCol >= 0 && newRow >= 0 && newRow <= boundary && newCol <= boundary && grid[newRow][newCol]==0) { 
                    //candidate cell - add it to the queue to try
                    tries.offerLast(new int[]{newRow,newCol,moveTotal+1});
                    
                    //mark this cell as having been evaluated by changing its value to "1"
                    grid[newRow][newCol] = 1;
                }
            }
          }
        return -1;       
    }
}
