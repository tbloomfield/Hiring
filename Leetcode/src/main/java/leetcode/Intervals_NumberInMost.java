package leetcode;

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
 * (From a Meta interview): Given a set of intervals, find the number that occurs in the most number of
 * intervals.
 * 
 * Find the greatest intersection between a set of intervals.
 * 
 * Complexity: 
 * Sort: O(nLogn)
 * Combine intervals: O(n^2) - for each element we need to check the interval of the elements after this
 */
public class Intervals_NumberInMost {
    public static void main(String[] args) { 
        
    }
    
    /*public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        //check to see if the intervals intersect
        
        //sort intervals by start time
        int i = 0, j = 0;
        
        //does this intersection have overlap with the next
        while(i < firstList.length && j < secondList.length) {
          int low = firstList[i][0];
          int maxLowRange = Math.max(firstList[i][0], secondList[j][0]);
          int minHighRange = Math.min(firstList[i][1], secondList[j][1]);
          
          //the interval fits, use it for the next comparison
          if(maxLowRange <= minHighRange) { 
              //new interval              
              
          } else { 
              
          }
        }          
    }*/
    
    public int[][] intervalIntersection(int[][] intervals) {
        //check to see if the intervals intersect
        
        //sort intervals by start time
        
        //create a dummy interval to do the first comparison:
        //comparison
        int[][] comparisonInterval = new int[1][1];
        comparisonInterval[0][0] = Integer.MIN_VALUE;
        comparisonInterval[0][1] = Integer.MAX_VALUE;
        
        int j = 0;
        int maximumFound = 0;
        
        //does this intersection have overlap with the next
        while(j < intervals.length) {          
          int maxLowRange = Math.max(comparisonInterval[0][0], intervals[j][0]);
          int minHighRange = Math.min(comparisonInterval[0][1], intervals[j][1]);
          
          //the interval fits, use it for the next comparison
          if(maxLowRange <= minHighRange) { 
              //new interval
              comparisonInterval[0][0] = maxLowRange;
              comparisonInterval[0][0] = minHighRange;
              maximumFound ++;
          } else { 
              
          }
        }          
    }
}
