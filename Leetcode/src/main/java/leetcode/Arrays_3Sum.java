package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum 
 * 
 * https://leetcode.com/problems/3sum/description/
 * 
 * Given an integer array nums, return all the triplets [nums[i], nums[j],
 * nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] +
 * nums[k] == 0.
 * 
 * Notice that the solution set must not contain duplicate triplets.
 */
public class Arrays_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();

        if(nums == null || nums.length == 0){ 
          return triplets;
        }

        //sort input array O(nlogn); this helps us determine
        //if an element is a duplicate by putting duplicate
        //numbers side-by-side
        Arrays.sort(nums);      
        
        
        for(int i = 0; i < nums.length; i ++){            
          //skip duplicate items
          if(i > 0 && nums[i] == nums[i-1]) {
              continue;
          }

          //now use 2 pointers to compare the triplet to sum to zero 
          int lpointer = i+1, rpointer = nums.length-1;
          
          while(lpointer < rpointer){ 
              int candidate1 = nums[lpointer];
              int candidate2 = nums[rpointer];

              int sum = nums[i] + candidate1 + candidate2;              
              if(sum == 0){
                  triplets.add(List.of(nums[i], candidate1, candidate2));

                  //advance pointers until we encounter a different number than what
                  //was previously evaluated so we don't end up with the same 
                  //triplet multiple times.
                  while(lpointer < rpointer && nums[lpointer] == nums[lpointer+1]){
                      lpointer++;
                  }
                  while(lpointer < rpointer && nums[rpointer] == nums[rpointer-1]){
                      rpointer --;
                  }

                  lpointer++;
                  rpointer--;
              } else if(sum > 0){ 
                  //sum is too large - move right pointer
                  rpointer --;
              } else {                
                  //sum is too small - move left pointer.
                  lpointer ++;
              }         
          }
        }
        return triplets;
      }
}
