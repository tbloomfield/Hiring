package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import leetcode.dto.Coordinate;

/**
 * 827. Making a Large Island
 * https://leetcode.com/problems/making-a-large-island/description/
 * 
 * You are given an n x n binary matrix grid. You are allowed to change at most
 * one 0 to be 1.
 * 
 * Return the size of the largest island in grid after applying this operation.
 * 
 * An island is a 4-directionally connected group of 1s.
 * 
 * calculate areas of all connected rectangles.
 * 
 * 
 * Answer:
 * This is similar to 200. "Number of Islands", which counts all islands.
 * 
 * Loop through each row and column of the grid.  When we find a '1', we create a new "group number"
 * to represent this and any adjacent land.   We then loop through all adjacent land, setting that
 * land to the same group number.  The looping function returns a count of the  number of adjacent
 * land that was put into this group.
 * 
 * While looping, we may encounter a '0' (or empty space).  This value abuts the land, so upon
 * encountering we add the empty space coordinates to a map of possible candidate nodes to switch.  
 * The value of this coordinate is a set of groups that the the empty space abuts.
 * 
 * After looping completes, we'll loop through all candidate nodes that were found.  If there 
 * were any, we evaluate the max area if all groups this node abuts.
 * 
 * Complexity: O(MxN) where M and N are the number of rows and columns.
 *             For each cell which is a '1' we evaluate adjoining cells
 */
public class Matrix_LargestIsland {
    
    public static void main(String[] args) { 
        Matrix_LargestIsland test = new Matrix_LargestIsland();
        System.err.println(
                test.largestIsland(new int[][] {{1,1},{1,1}}));
                
    }
    
    private final int[][] moves = {{0,1},{1,0},{0,-1},{-1,0}};    
    private final Map<Integer/*groupId*/, Integer/*area*/> groupSizes = new HashMap<>();
    
    public int largestIsland(int[][] grid) {
        //step 1: loop through each node, calculating the area (size)
        int groupNumber = 10;
        int highestIndividualGroupArea = 0;
        
        for(int row = 0; row < grid.length; row ++) { 
            for(int col = 0; col < grid[row].length; col++) { 
                //part of an island
                if(grid[row][col] == 1) {
                    //found a new group.
                    groupNumber ++;
                    
                    //loop through adjoining land, adding it to this group.
                    int groupArea = calculateAreaAndUpdateAdjacent(grid, row, col, groupNumber);
                   // System.err.println("individual area=" + groupArea);
                    highestIndividualGroupArea = Math.max(highestIndividualGroupArea, groupArea);
                    groupSizes.put(groupNumber, groupArea);
                }
            }
        }
        
        //step 2: loop through each candidate node (a '0' node which was adjacent to land)
        //determining which would result in the largest area if it was toggled to a '1' and added
        //to an individual area
        int maxArea = highestIndividualGroupArea;   //by default the area will be the highest individual area     
        for(int row = 0; row < grid.length; row++) { 
            for(int col = 0; col < grid[row].length; col++) {
                
              if(grid[row][col] != 0) { 
                  continue;
              }

              //keep a history of adjacent groups evaluated to prevent adding of the 
              //same adjacent group multiple times... for example:
              //1 0 1
              //1 1 1
              //when evaluating zero, the same group will be returned when looking left, down,
              //and right.             
              Set<Integer/*<gridId*/> visited = new HashSet<>();              
                
              //evaluate empty space to see if it adjoins a group
              int joinedGroupArea = 1;  //1 for the cell we might flip
              for(int[] move: moves) {
                  int newRow = row + move[0];
                  int newCol = col + move[1];               
                  
                  //note that if already visited, the call to set() will fail:
                  //check whether the adjacent node is a group; if so add its area
                  if(validCoordinate(grid, newRow, newCol) && visited.add(grid[newRow][newCol]) && grid[newRow][newCol] > 1) {
                      joinedGroupArea += groupSizes.get(grid[newRow][newCol]);
                  }                  
              } 
              
              //is the total potential area if we flipped this coordinate value to a '1' the largest?
              maxArea = Math.max(maxArea, joinedGroupArea);
          }
        }
        
        return maxArea;
    }
    
    //checks boundaries of the input row/col within the array
    private boolean validCoordinate(int[][]grid, int row, int col) {
        return row < grid.length && col < grid[0].length  && row >= 0 && col >= 0;
    }
    
    
    //iterate to surrounding nodes, updating in-place any nodes which are marked as '1' with the group
    //number (ie it's parent grouping).
    private int calculateAreaAndUpdateAdjacent(int[][] grid, int row, int col, int parentGroupNumber) {        
        //mark this cell with the group number to show a relationship.  Note 
        //that the group number should only be '1'
        grid[row][col] = parentGroupNumber; 
        int area = 1;
        
        for(int[] move: moves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if(validCoordinate(grid, newRow, newCol) && grid[newRow][newCol] == 1) { 
                area += calculateAreaAndUpdateAdjacent(grid, newRow, newCol, parentGroupNumber);
            }
        }
        return area;        
    }    
}