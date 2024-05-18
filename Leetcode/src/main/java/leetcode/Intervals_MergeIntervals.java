package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 56. Merge Intervals
 * https://leetcode.com/problems/merge-intervals/description/
 * 
 * Given an array of intervals where intervals[i] = [starti, endi], merge all
 * overlapping intervals, and ***return an array of the non-overlapping intervals
 * that cover all the intervals in the input***.
 * 
 * Answer:
 * Note that the input provided is NON sorted.  Merging intervals only works on sorted input.
 * 
 * Step 1: Perform Quicksort on the input array O(nlogn) on the interval START / low value
 * Step 2: Loop through sorted array, merging intervals O(n)
 * 
 * Complexity: O(nlogn) for Quicksort
 * Storage: O(n) in the worst case scenario no intervals overlap so we return the same size as input array
 * 
 */
public class Intervals_MergeIntervals {
public static void main(String[] args) { 
    Intervals_MergeIntervals test = new Intervals_MergeIntervals();
    test.merge(new int[][] {{2,6},{8,10},{15,18},{1,3}} );    
    //Output: [[1,6],[8,10],[15,18]]
}

    public int[][] merge(int[][] intervals) {
        List<int[]> mergedIntervals = new ArrayList<>();  
        
        //Step 1: Sort interval input on start interval - note that at least 1 interval will be provided
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        
        //Step 2: loop though results, merging overlapping intervals into a "new"
        //interval that will contain all merged intervals.
        int[] comparisonInterval = { intervals[0][0], intervals[0][1]} ;
        for(int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            
            //check whether this interval matches the previous interval
            int maxLowValue = Math.max(interval[0], comparisonInterval[0]);
            int minLowValue = Math.min(interval[0], comparisonInterval[0]);
            int minHighValue = Math.min(interval[1], comparisonInterval[1]);
            int maxHighValue = Math.max(interval[1], comparisonInterval[1]);
            
            //intersection, combine the two - based on the question we're creating
            //an interval that covers BOTH of the intervals that intersect.
            if(maxLowValue <= minHighValue) { 
                comparisonInterval[0] = minLowValue;
                comparisonInterval[1] = maxHighValue;
            } else { 
                //no intersection between current and previous; add the previous comparison interval
                mergedIntervals.add(comparisonInterval);
                
                //add the current interval which didn't match anything
                comparisonInterval = interval;
            }
        }
        
        //add the last interval if it was populated        
        mergedIntervals.add(comparisonInterval);
        int intersectionsFound = mergedIntervals.size();
        int[][] intervalsFound = new int[intersectionsFound][1];
        for(int q = 0; q < intersectionsFound; q++) {
            intervalsFound[q] = mergedIntervals.get(q);
        }
        return intervalsFound;        
    }
}
