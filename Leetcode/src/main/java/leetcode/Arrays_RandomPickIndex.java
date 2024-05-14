package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 398. Random Pick Index
 * https://leetcode.com/problems/random-pick-index/description/
 * 
 * Given an integer array nums with possible duplicates, randomly output the
 * index of a given target number. You can assume that the given target number
 * must exist in the array.
 * 
 * Answer:
 * Store a map of values to a list of indexes that value occurs in.  When a desired
 * target is requested, find it's key, then randomly generate a value between 0->value.length
 * 
 * Complexity: O(n) - we loop through every element in the number array
 * Storage: O(n) - in the worst case every element could have unique values, requiring O(n) space
 * 
 */
public class Arrays_RandomPickIndex {
    public static void main(String[] args) { 
        Arrays_RandomPickIndex test = new Arrays_RandomPickIndex();
        test.runTest();
    }
    
    public void runTest() { 
        Solution s = new Solution(new int[] { 1,2,3,3,3});
        System.err.println(s.pick(3));
        System.err.println(s.pick(1));
        System.err.println(s.pick(3));
    }    
    
    class Solution {
        int[] nums;
        Map<Integer/*number*/, List<Integer/*indexes*/>> numIndexMap;
        Random r = new Random();

        public Solution(int[] nums) {
            this.nums = nums;
            
            //create an index of each number for fast lookups
            numIndexMap = new HashMap<>();
            for(int i = 0; i < nums.length; i++) { 
                int val = nums[i];
                if(numIndexMap.containsKey(val)) { 
                    numIndexMap.get(val).add(i);
                } else { 
                    List<Integer> indexes = new ArrayList<>();
                    indexes.add(i);
                    numIndexMap.put(val, indexes);
                }
                
            }
        }
        
        public int pick(int target) {
            var targetIndexes = numIndexMap.get(target);
            int randomPosition = r.nextInt(0, targetIndexes.size());
            return targetIndexes.get(randomPosition);            
        }
    }
}
