package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1762. Buildings with an ocean view
 * https://leetcode.com/problems/buildings-with-an-ocean-view/
 * 
 * There are n buildings in a line. You are given an integer array heights of
 * size n that represents the heights of the buildings in the line.
 * 
 * The ocean is to the right of the buildings. A building has an ocean view if
 * the building can see the ocean without obstructions. Formally, a building has
 * an ocean view if all the buildings to its right have a smaller height.
 * 
 * Return a list of indices (0-indexed) of buildings that have an ocean view,
 * sorted in increasing order.
 * 
 * 
 * Answer:
 * There are couple of key concepts here: 
 *   1) the ocean is on the right, so the last element in heights() has a guaranteed view; since we're starting
 *      with the rightmost entry we'll iterate right to left comparing heights.
 *   2) we need to return indexes (left to right) in ascending order.
 *   3) we aren't returning the actual heights, we're only returning indexes
 * 
 * Complexity: O(n) where n are the number of buildings array
 * Storage: O(n) to output indexes ascending, where n are the number of buildings in the array
 */
public class Arrays_BuildingsWithAView {
    
    
    public int[] findBuildings(int[] heights) {
        
        //use a queue to reverse the order from smallest to tallest
        Deque<Integer> indexes = new ArrayDeque<>();
        
        //since the ocean is on the right, we know the rightmost 
        //entry in heights array can view it.
        indexes.offerFirst(heights.length-1);
        int maxHeight = heights[heights.length-1];

        //moving from left->right in the array
        for(int i = heights.length-2; i>=0; i --){
            
            //even thought we're iterating right to left, insert from left to right
            //so indexes are in ascending order.
            if(heights[i] > maxHeight){ 
                indexes.offerFirst(i);
            }
            maxHeight = Math.max(maxHeight, heights[i]);
        }

        //return results in ascending order
        int[] results = new int[indexes.size()];        
        for(int i = 0; i < results.length; i++) {
            results[i] = indexes.pollFirst();
        }
        return results;
    }
}