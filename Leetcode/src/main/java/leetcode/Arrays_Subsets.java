package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 78. Subsets https://leetcode.com/problems/subsets/description/
 * 
 * Given an integer array nums of unique elements, return all possible subsets.
 * 
 * The solution set must not contain duplicate subsets. Return the solution in
 * any order.
 * 
 * Answer:
 * 
 * Similar to 39. Combination Sum, we use backtracking to find all subsets.  Since we
 * don't allow duplicates, we sort the array to easily compare if we've previously
 * seen the integer element.
 * 
 * Complexity: O(2^N), there are n numbers in array and 2 decisions (to include or not) a number
 * during each recursive call.
 * Storage: O(2^N), 1 entry for each (n) number
 */
public class Arrays_Subsets {
    
    public static void main(String[] args) { 
        Arrays_Subsets test = new Arrays_Subsets();
        var results = test.subsets(new int[] { 1,2,3} );
        System.err.println(results); 
        //[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    }
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList();
        //Arrays.sort is useful when detecting duplicates:
        Arrays.sort(nums);
        backtrack(results, new ArrayList(), nums, 0);
        return results;
    }
    
    private void backtrack( 
            List<List<Integer>> results,
            List<Integer> currentSubset,
            int[] nums, int startOffset){
        
       //create a shallow copy since the subset will mutate on subsequent calls.
       results.add(new ArrayList<>(currentSubset));
       
       for(int i = startOffset; i < nums.length; i++) {
           //skip duplicate elements
           if(i > startOffset && nums[i] == nums[i-1]) { 
               continue;
           }
           currentSubset.add(nums[i]);
           backtrack(results, currentSubset, nums, i+1);
           currentSubset.remove(currentSubset.size() -1);
        }
    }  
}
