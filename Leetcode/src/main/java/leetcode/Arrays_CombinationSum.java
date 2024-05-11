package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. Combination Sum
 * 
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen
 * numbers sum to target. You may return the combinations in any order.
 * 
 * Answer: This is simple backtracking where we need to look for all
 * combinations.
 * 
 * Complexity: O(n^target) where n is the number of times we recurse and . The
 * total number of steps during the backtracking would be the number of nodes in
 * the tree. 
 * 
 * Storage: O(target/minimalvalue); The number of recursive calls can
 * pile up to O(t/m) where we keep on adding the smallest element to the
 * combination. In addition, we keep a combination of numbers during the
 * execution, which requires at most O(T/M) space as well
 * space as well.
 * 
 * Example:
 * [2,3,6,7] target = 7 Combinations: [2,2,3], [7]
 */
public class Arrays_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList();
        //Arrays.sort is useful when detecting duplicates:
        //Arrays.sort(candidates);
        backtrack(results, new ArrayList(), candidates, target, 0);
        return results;
    }
    
    private void backtrack( 
            List<List<Integer>> results,
            List<Integer> currentCombination,
            int[] candidates, int remain, 
            int startOffset){
        
        if(remain < 0) { 
            return;            
        } else if (remain == 0) { 
            results.add(new ArrayList<>(currentCombination));
        } else {
            for(int i = startOffset; i < candidates.length; i++) {
                currentCombination.add(candidates[i]);
                backtrack(results, currentCombination, candidates, remain-candidates[i], i+1);
                currentCombination.remove(currentCombination.size() -1);
            }
        }
    }   
}
