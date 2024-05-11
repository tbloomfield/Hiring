package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. Interval List Intersections 
 * https://leetcode.com/problems/interval-list-intersections/description/
 * 
 * You are given two lists of closed intervals,
 * firstList and secondList, where firstList[i] = [starti, endi] and
 * secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint
 * and in sorted order.
 * 
 * Return the intersection of these two interval lists.
 * 
 * Answer:
 * 
 * Loop through both interval sets; 2 intervals, "interval1" and "interval2" are overlapping if:
 * 
 *    max(lower_range1, lower_range2) < min(upper_range1, upper_range2);
 *    
 * If an overlap is found, it's possible that the overlap spans multiple intervals; therefore we  
 * advance to look at the next interval based on whichever had a higher upper_range.
 * 
 * Complexity:  O(m) where m = the max elements between the first and second list
 * Storage: O(m) worst case every interval in {firstList,secondList} overlaps.
 */
public class Intervals_2IntervalIntersection {
    public static void main(String[] args) { 
        Intervals_2IntervalIntersection test = new Intervals_2IntervalIntersection();
        test.intervalIntersection(new int[][] { {0,2},{5,10},{13,23},{24,25}}, 
                                  new int[][] { {1,5},{8,12},{15,24},{25,26}});
    }    
    
    //for intersections on 2 intervals
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        //store all found intersections
        List<int[]> intersections = new ArrayList<>();
        
        //pointers to the first or second list
        int j = 0, i = 0;        
        
        //does this intersection have overlap with the next?  (see rules in class comments)
        while(i < firstList.length && j < secondList.length) {
          
          int maxLowRange = Math.max(firstList[i][0], secondList[j][0]);
          int minHighRange = Math.min(firstList[i][1], secondList[j][1]);
          
          //interval overlap
          if(maxLowRange <= minHighRange) {
              intersections.add( new int[] { maxLowRange, minHighRange} );             
          }
          
          //intersections from the first list can span multiple on the second list (and vice-versa)
          //the intersection pointer to compare next should be whichever has the smaller end range.
          if(firstList[i][1] > secondList[j][1]) {  //compare first interval with a new second interval
              j++;
          } else if(firstList[i][1] == secondList[j][1]) {   //range ends are exactly the same, compare 2 new intervals 
              j++;
              i++;
          } else { //compare second interval with a new first interval
              i++;
          }
        }
        
        //convert overlapping intersections back to native array
        int intersectionsFound = intersections.size();
        int[][] intervalsFound = new int[intersectionsFound][1];
        for(int q = 0; q < intersectionsFound; q++) {
            intervalsFound[q] = intersections.get(q);
        }
        return intervalsFound;
    }
}
