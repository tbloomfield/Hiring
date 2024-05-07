package leetcode;

import java.util.LinkedList;

/**
 * 346. Moving Average from Data Stream
 * https://leetcode.com/problems/moving-average-from-data-stream/description/
 * Given a stream of integers and a window size, calculate the moving average of
 * all integers in the sliding window.
 * 
 * Implement the MovingAverage class.
 * 
 * Complexity: O(1) as pollFirst and offerLast are O(1) operation
 * Storage: O(n) where n is the number of "next" nodes inserted.
 */
public class Math_MovingAverageInInteger {
    
    public static void main(String[] args) {       
        MovingAverage test = new MovingAverage(3);
        System.err.println(test.next(1)); // return 1.0 = 1 / 1
        System.err.println(test.next(10)); // return 5.5 = (1 + 10) / 2
        System.err.println(test.next(3)); // return 4.66667 = (1 + 10 + 3) / 3
        System.err.println(test.next(5)); // return 6.0 = (10 + 3 + 5) / 3        
    } 
}

class MovingAverage {
    int maxSize;
    LinkedList<Integer> ll = new LinkedList<>();
    double currentSum;

    public MovingAverage(int size) {
        maxSize = size;
    }

    public double next(int val) {
        // if at capacity, remove the head of this list
        if (ll.size() == maxSize) {
            int firstVal = ll.pollFirst();
            currentSum -= firstVal;
        }

        // add the new val
        ll.offerLast(val);
        currentSum += val;

        return currentSum / ll.size();
    }
}
