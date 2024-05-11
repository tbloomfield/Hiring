package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * 
 * Given a collection of candidate numbers (candidates) and a target number
 * (target), find all unique combinations in candidates where the candidate
 * numbers sum to target.
 * 
 * Each number in candidates may only be used once in the combination.
 * 
 */
public class Arrays_CombinationSumII {
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();        
        Arrays.sort(candidates); //sort to allow duplicate detection.  O(nlogn)        
        backtrack(results, new ArrayList<>(), candidates, 0, target);
        return results;
    }
    
    private void backtrack(List<List<Integer>> results, List<Integer> currentCombination, int[] candidates, int startPosition, int remaining) {
        if(remaining < 0) { // no solution
            return;
        } else if(remaining == 0) { 
            results.add(new ArrayList<>(currentCombination));
        } else { 
            for(int i = startPosition; i < candidates.length; i++) {
                //skip duplicate numbers
                if(i > startPosition && candidates[i] == candidates[i-1]) {
                    continue;
                }
                currentCombination.add(candidates[i]);
                backtrack(results, currentCombination, candidates, i+1, remaining - candidates[i]);
                currentCombination.remove(currentCombination.size()-1);  // like calling popo              
            }
        }
    }
}
