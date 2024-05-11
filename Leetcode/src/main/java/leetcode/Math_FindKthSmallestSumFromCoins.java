package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 3116. Kth Smallest amount with single denomination combination.
 * 
 * You are given an integer array coins representing coins of different
 * denominations and an integer k.
 * 
 * You have an infinite number of coins of each denomination. However, you are
 * not allowed to combine coins of different denominations.
 * 
 * Return the kth smallest amount that can be made using these coins.
 * 
 * Input: coins = [3,6,9], k = 3
 * Output: 9
 * Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.
 * Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.
 * Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.
 * All of the coins combined produce: 3, 6, 9, 12, 15, etc.
 */
public class Math_FindKthSmallestSumFromCoins {
    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);  //o(n logn)
        int minKValue = 0;
        for(int i = 0; i < coins.length; i++) {
            //if negative this indicates the insertion point
            //if positive the element was found
            if(Arrays.binarySearch(null, 0)) > 0) { 
                
            }
            
        }
    }
}
