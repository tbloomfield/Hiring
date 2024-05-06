package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. TwoSum
 * https://leetcode.com/problems/two-sum/
 * 
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You cannot use the same element twice.
 * 
 * Runtime: O(n) where n = number of elements in the array
 * Storage: O(n) where n = number of elements in the array.
 */
public class Arrays_TwoSum {
    
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mapIndexes =  new HashMap<>();
         for(int i = 0; i < nums.length; i++) {
             int value = nums[i];
             if(mapIndexes.containsKey(target - nums[i])) {
                 return new int[] { mapIndexes.get(target-nums[i]), i };
             } else {
                 mapIndexes.put(value, i);
             }
         }

         return new int[0];
     }
}
