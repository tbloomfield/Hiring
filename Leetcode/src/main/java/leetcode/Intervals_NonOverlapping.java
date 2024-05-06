package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 435. Non-overlapping Intervals - return minimum number of intervals to remove 
 * to make the rest non-overlapping.
 * 
 * https://leetcode.com/problems/non-overlapping-intervals/description/
 * 
 * Given an array of intervals intervals where intervals[i] = [starti, endi], 
 * return the minimum number of intervals you need to remove to make the rest of the 
 * intervals non-overlapping.
 */
public class Intervals_NonOverlapping {
    class Pair { 
        int high;
        int low;
        
        Pair(int high, int low){ 
            this.high = high;
            this.low = low;
        }
    }
    
    //greedy approach: sort end_interval times to process in-order (nlogn sort)
    //compare each start 
    //if start of interval > end of previous interval, update the 
    //end time of previous interval to be the end time of this current interval.
    //otherwise this interval needs to be removed
    
    // runtime: O(nlogn)
    // storage: O(logn) - Arrays.sort uses Quicksort which has space complexity of O(logn)
    // 
    int identifyOverLappingSpace(Pair[] intervals) {
        Arrays.sort(intervals, new Comparator<Pair>() {
            @Override
            public int compare(Pair pair1, Pair pair2) {
                return pair1.high - pair2.high;
            }
        });
        
        //loop through all pairs
        int lastHighest = Integer.MIN_VALUE;
        List<Pair> intervalsToRemove = new ArrayList<>();
        for(Pair p : intervals) {
            if(p.low >= lastHighest) {
                lastHighest = p.high;
            } else  { 
                intervalsToRemove.add(p);
            }            
        }
        
        return intervalsToRemove.size(); 
    }
    
    /**
     * Return in leetcode expected output format - array of high/low values
     * @param intervals
     * @return
     */
    int identifyOverLappingSpace(int[][] intervals) {
        Pair[] pairs = new Pair[intervals.length];
        for(int i = 0 ; i < intervals.length; i++) { 
            pairs[i] = new Pair(intervals[i][1], intervals[i][0]);
        }
        
        return identifyOverLappingSpace(pairs);
    }
}
