package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 317. Shortest Distance from All Buildings ou are given an m x n grid grid of
 * values 0, 1, or 2, where:
 * 
 * each 0 marks an empty land that you can pass by freely, each 1 marks a
 * building that you cannot pass through, and each 2 marks an obstacle that you
 * cannot pass through. You want to build a house on an empty land that reaches
 * all buildings in the shortest total travel distance. You can only move up,
 * down, left, and right.
 * 
 * Return the shortest travel distance for such a house. If it is not possible
 * to build such a house according to the above rules, return -1.
 * 
 * The total travel distance is the sum of the distances between the houses of
 * the friends and the meeting point.
 * 
 * Answer:
 * Since we're searching for the lowest path between a point and multiple other points, BFS
 * is a good solution since it will fan out to all possible cells.
 * 
 * Runtime: O(rows*columns).  For each house found we will traverse all reachable land O(rows+columns)
 * meaning our runtime is O(n^2*m^2)
 * 
 * Storage: The hashmap stores distances for all open space O(m*n) for each constant building.  The queue holds some elements
 * 
 */
public class Graphs_ShortestDistanceAllBuildings {
    public static void main(String[] args) { 
        Graphs_ShortestDistanceAllBuildings test = new Graphs_ShortestDistanceAllBuildings();
        //int[][] testGrid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        //int[][] testGrid = {{1,1},{0,1}};
        /*int[][] testGrid = {{1,1,1,1,1,0},
                            {0,0,0,0,0,1},
                            {0,1,1,0,0,1},
                            {1,0,0,1,0,1},
                            {1,0,1,0,0,1},
                            {1,0,0,0,0,1},
                            {0,1,1,1,1,0}};*/
        
        int[][] testGrid = {
                {0,2,0,2,2,0,2,2},
                {0,2,2,2,1,0,1,2},
                {0,0,0,1,0,2,0,0},
                {2,0,0,2,0,2,2,0},
                {0,0,0,2,0,0,0,0}};
        System.err.println(test.shortestDistance(testGrid));
    }
    
    private final int[][] directions = {{0,-1}/*left*/, {-1,0}/*up*/, {0,1} /*right*/, {1,0} /*down*/};
    
    public int shortestDistance(int[][] grid) {
        //calculate the distance to multiple ending points (buildings) from
        //multiple empty points, then find the smallest value in the list
        
        //BFS to enable looping through to get potential starting point candidates.
        Deque<Point> startingPoints = new ArrayDeque<>();
        
        //track cost to get to each point from a starting position.
        Map<Point, Integer/*cost*/> costFromStarts = new HashMap<>();       
        
        //step 1: determine the number of buildings found; this will be used to determine
        //if there is a path from a building to all other buildings.
        //O(nm) operation
        int totalBuildings = 0;
        int totalOpenNodes = 0;
        for(int rowNum = 0; rowNum < grid.length; rowNum++) {
            int[] columns = grid[rowNum];
            for(int colNum = 0; colNum < columns.length; colNum++) {
                if(grid[rowNum][colNum] == 1) { 
                    totalBuildings ++;
                   // landNodes.add(new Point(rowNum, colNum));
                } else if(grid[rowNum][colNum] == 0) {
                    totalOpenNodes ++;
                }
            }
        }
        
        //step 2: loop through each row/column O(mn); for each entry which a building
        //we fan out and calculate distance to each open "neighbor" cell.
        // Since it's possible some buildings are blocked, we also need to ensure that during 
        // the neighbor check we've encountered the total number of buildings scanned in step 1.       
        for(int rowNum = 0; rowNum < grid.length; rowNum++) {
            int[] columns = grid[rowNum];

            for(int colNum = 0; colNum < columns.length; colNum++) {
                //buildings are starting point candidates; we will fan out to calculate distances from each.
                if(columns[colNum] != 1) {
                    continue;
                }

                //track the number of buildings found.  By the end of our BFS
                //we should have the same amount of buildings found as total buildings;
                //if not there is a broken path 
                int buildingsFound = 1;
                int openNodesFound = 0;                
                
                Point start = new Point(0, rowNum, colNum);                    
                startingPoints.offer(start);
               
                //keep track of visited points to prevent BFS from re-evaluating already evaluated
                //cell for this starting point
                //a map of points visited from open nodes
                Map<Point, Boolean> visitedPoints = new HashMap<>();
                visitedPoints.put(start, true); //mark our starting point as visited.
        
                //do BFS from this point to all neighbors.
                while(!startingPoints.isEmpty()) {                      
                  Point p = startingPoints.poll();
                                        
                  //open space is found; add the cost from starting point to this open point.
                  if(grid[p.row][p.column] == 0) {
                    int currentCost = costFromStarts.getOrDefault(p, 0);
                    
                    //System.err.println(String.format("%d,%d to %d,%d cost = %d", start.row, start.column, p.row, p.column, p.cost + currentCost));
                    costFromStarts.put(p, currentCost + p.cost);
                  }
                  
                  //visit 4 neighbors; if open land add to queue.
                  for(int[] dir : directions) { 
                      Point nextPoint = new Point(p.cost+1, p.row+dir[0], p.column+dir[1]);
                      
                      //range checks
                      if(nextPoint.column < columns.length && nextPoint.column >=0 &&
                              nextPoint.row < grid.length && nextPoint.row >= 0) {
                          
                          //already visited this, skip visiting again.
                          if(visitedPoints.containsKey(nextPoint)) {
                              //System.err.println("skipping " + nextPoint.row +"," + nextPoint.column);
                              continue;
                          }
                          
                          //if neighbor is a building, no need to visit it.
                          //count the number of buildings seen when coming from open
                          //spaces
                          if(grid[nextPoint.row][nextPoint.column] == 1) {
                              //if this grid is being visited from an open node, count
                              //it in our valid path and mark it so it's not visited again
                              //System.err.println(String.format("found %d,%d count=%d", nextPoint.row, nextPoint.column, buildingsFound));
                              if(grid[p.row][p.column] == 0) {
                                  buildingsFound ++;
                                  visitedPoints.put(nextPoint, true);
                              }
                              continue;
                          } else if(grid[nextPoint.row][nextPoint.column]== 2) {
                              //skip obstacles
                              continue;
                          } else {                                  
                              //open land, examine it.
                              openNodesFound ++;
                              startingPoints.offer(nextPoint);
                             // System.err.println("visiting " + nextPoint.row + "," + nextPoint.column);
                              visitedPoints.put(nextPoint, true);
                          }
                      }                              
                  } //--end directions
               } //-end neighbor visit
                
              //step 3: if after looping over all neighbors we didn't encounter the total number
              //of buildings scanned for then there was a blockage in the BFS; this means
              //there's no shortest distance and we can fast fail.
              if(buildingsFound != totalBuildings) {                        
                  //System.err.println(String.format("%d found %d expected for start %d,%d- not a valid path!", buildingsFound, totalBuildings, start.row, start.column));                        
                  return -1;
              }
                
              //Step 4: some open cells may not have been reachable in the BFS for this entry; 
              // mark them as blocked (2) and remove them from the available land nodes.
              if (openNodesFound != totalOpenNodes) {
                  for (int _row = 0; _row < grid.length; _row++) {
                      for (int _col = 0; _col < grid[_row].length; _col++) {
                          // open row that wasn't visited - it must be unreachable.
                          Point p = new Point(_row, _col);
                          if (!visitedPoints.containsKey(p) && grid[_row][_col] == 0) {
                               //System.err.println("removing " + p.row + "," + p.column + " starting from " + start.row + "," + start.column);
                              grid[p.row][p.column] = 2; // this node is blocked and can't be visited
                              costFromStarts.remove(p);
                              totalOpenNodes--;
                          }
                      }
                  }
              }
            } //-end column loop
        } //-end rowloop
        
        //step 5: after the algorithm finishes, loop through each calculated cell looking for the
        //minimum value.
        int lowestCost = Integer.MAX_VALUE;
        for(int cost : costFromStarts.values()) { 
            lowestCost = Math.min(lowestCost, cost);
        }
        
        //if no valid path is found, return -1
        if(lowestCost == Integer.MAX_VALUE) { 
            return -1;
        } else {  
            return lowestCost;
        }
    }
    
    class Point { 
        int row;
        int column;
        
       //Note: override equals and hashcode to 
        //only include the row,column tuple; the cost of a point is not an identifier. 
        int cost;
        
        public Point(int row, int column) { 
            this.row = row;
            this.column = column;
        }
        
        public Point(int cost, int row, int column) {
            this.cost = cost;
            this.row = row;
            this.column = column;
        }
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result;
            result = prime * result + Objects.hash(column, row);
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point other = (Point) obj;            
            return column == other.column && row == other.row;
        }
    }
}
