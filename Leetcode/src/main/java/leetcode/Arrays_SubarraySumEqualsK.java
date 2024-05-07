package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals k
 * https://leetcode.com/problems/subarray-sum-equals-k/description/
 * 
 * Given an array of integers and an integer k, return the total number of subarrays whose sum equals k
 * 
 * Answer:
 * Somewhat to TwoSum we can keep a map of cumulutive sums and the number that
 * sum occurs.  Then for each index value we can look to see if the value - k = an entry
 * in the map.  If so, we can add the count to our total found. 
 * 
 * Complexity: O(n) we have to traverse through each numbers element
 * Storage: O(n) we store the cumulative sum up to nums.length
 */
public class Arrays_SubarraySumEqualsK {
    
    Map<Integer/*sum*/, Integer/*countOfTimesSeen*/> sumCounts =  new HashMap<>();
    public int subarraySum(int[] nums, int k) {
        
        sumCounts.put(0, 1);
        int cumulativeSum = 0;
        int numberOfSubarray = 0;
        
        for(int i = 0; i < nums.length; i ++) { 
            cumulativeSum += nums[i];
            
            int previousSums = sumCounts.getOrDefault( cumulativeSum - k , 0);
            numberOfSubarray += previousSums;
            sumCounts.put(cumulativeSum, sumCounts.getOrDefault(cumulativeSum, 0) + 1);         
        }
        
        return numberOfSubarray;
    }
}
