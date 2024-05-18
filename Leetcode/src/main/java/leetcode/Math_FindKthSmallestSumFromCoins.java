package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 3116. Kth Smallest amount with single denomination combination.
 * https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/description/
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
 * 
 * Solution: Binary search allowing multiple values
 */
public class Math_FindKthSmallestSumFromCo1ins {
    public long findKthSmallest(int[] coins, int k) {
        List<Long> lcms = new ArrayList<>();
        int n = 1 << coins.length;
        
        for (int i = 1; i < n; ++i) {
            long lcm = 1;
            
            for (int j = 0; j < coins.length; ++j) {
                if ((i & (1 << j)) > 0) {
                    lcm = lcm(lcm, coins[j]);
                }
            }
            
            lcms.add(lcm * (Integer.bitCount(i) % 2 == 1 ? 1 : -1));
        }
        
        long low = 1;
        long high = Long.MAX_VALUE;
        
        while (low < high) {
            long mid = low + (high - low) / 2;
            
            if (count(mid, lcms) < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        
        return low;
    }
    
    private long count(long target, List<Long> lcms) {
        long count = 0;
        
        for (long lcm : lcms) {
            count += target / lcm;
        }
        
        return count;
    }
    
    private long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    
    private long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }
}
