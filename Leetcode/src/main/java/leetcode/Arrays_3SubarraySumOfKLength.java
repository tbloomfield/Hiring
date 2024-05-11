package leetcode;

/**
 * 689. Maximum Sum of 3 Non-overlapping subarrays.
 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
 * 
 * Given an integer array nums and an integer k, find three non-overlapping
 * subarrays of length k with maximum sum and return them.
 * 
 * Return the result as a list of indices representing the starting position of
 * each interval (0-indexed). If there are multiple answers, return the
 * lexicographically smallest one.
 *
 * Answer:
 * 
 * The question is asking us to find 3 subarrays or length K that results in the maximum sum between the 3.
 * This matches closely a "find maximum sum across a range".. IE segment tree.
 * 
 * 1) Input nums into a sum segment tree.  O(nlogn)
 * 2) Set range to K length.  IE index of K=2 will be {0,2},{1,3},{2,4} etc.
 * 3) For each range, query the segment tree to get the sum for each range: O(logn)
 * 4) Input each range sum into a priority queue to get the top 3 sums
 * 4) Return the lowest index for the top 3 ranges
 */
public class Arrays_3SubarraySumOfKLength {

}
