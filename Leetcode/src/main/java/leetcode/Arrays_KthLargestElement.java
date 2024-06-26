package leetcode;

import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in An Array
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 * 
 * Given an integer array nums and an integer k, return the kth largest element
 * in the array.
 * 
 * Note that it is the kth largest element in the sorted order, not the kth
 * distinct element.
 * 
 * Can you solve it without sorting?
 * 
 * MinHeap Solution:
 * Runtime: O(nlogk) using a minHeap - heap operations take O(logk) time and we're inserting n elements.
 * Storage: O(k) since we only keep the (k) most elements.
 * 
 * QuickSelect solution:
 * Runtime: O(n) using quickselect
 * Space: O(1) if using a while loop to find the partition.  
 *        If using recursion then space is O(depth) since space is proportional to maximum depth of the tree generated by recursion
 */
public class Arrays_KthLargestElement {
    
    public static void main(String[] args) { 
        Arrays_KthLargestElement test = new Arrays_KthLargestElement();
        int[] testArray = {3,2,1,5,6,4};
        System.err.println("heap=" + test.findKthLargest(testArray, 4));
        System.err.println("quickselect=" + test.findKthLargest_QS(testArray, 4));
    }
    
    /**
     * Use a minimum heap.
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {        
        // O(nlogk) if we were manually calling this on PriorityQueue
        // n elements, each heap O(log k) since we don't load all entries of n
        PriorityQueue<Integer> pk = new PriorityQueue<>(k + 1); // head will always have the smallest value
        for (int i : nums) {
            pk.offer(i);
            if (pk.size() > k) {
                pk.poll(); // remove the smallest element
            }
        }

        return pk.poll();
    }
    
    /*
     * Use quick select to find the element in O(n) time
     */
    public int findKthLargest_QS(int[] nums, int k) {        
        //start with one big partition
        int partitionRightIndex = nums.length - 1;
        int partitionLeftIndex = 0;
        
        //loop until the kth value is found
        while(true) {
          int pivotValue = nums[partitionRightIndex]; //pivot value is always the rightmost element in the partition
          int i = partitionLeftIndex;  //next potential spot for an element in the left partition
          int j = partitionLeftIndex;  //pointer to the element last traversed
          
          //swap values within our partition which are GREATER than than the given pivot 
          while(j < partitionRightIndex) {
              //normally quick select chooses the kth SMALLEST element based on:
              //if(nums[j] <= pivotValue)
              //since we want the kth LARGEST element, we change this to only swap
              //elements BIGGER than the pivot value.              
              if(nums[j] > pivotValue) { 
                  //swap i & j
                  int val = nums[i];
                  nums[i] = nums[j];
                  nums[j] = val;
                  i++;
              }
              j++;
          }
          //now swap pivot and i value
          int val = nums[i];
          nums[i] = pivotValue;
          nums[partitionRightIndex] = val;
          
          //if the pivot value is in the desired position, the desired element has been found
          if(i == k-1) { 
              return nums[i];
          } 
          else if(i > k - 1) {
              //the index is too large, do quick select on the left (smaller) side of i
              partitionRightIndex = i-1;
          } else { 
              //the index is too small, do quick select on the right (larger) side of i.
              partitionLeftIndex = i+1;
          }
        }
    }
}
