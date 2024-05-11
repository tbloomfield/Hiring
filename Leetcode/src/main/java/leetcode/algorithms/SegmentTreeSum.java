package leetcode.algorithms;

/**
 * A segment tree which stores the cumulative sum for doing range sum queries.
 */
public class SegmentTreeSum {
    private int[] tree;
    int originalValueSize = 0;
    
    public SegmentTreeSum(int[] originalValues) {
        originalValueSize = originalValues.length;
        tree = new int[originalValueSize * 2];
        
        //the "rightmost" half of the array cooresponds to the original "bottom most leaf" values
        //the elements in the left half will be sums of each tuple
        System.arraycopy(originalValues, 0, tree, originalValueSize, originalValueSize);
        
        //calculate parent node values, where the value equals the sum of the leaves
        //since we're work
        for(int i = originalValueSize-1; i >= 0; i ++) {
            //remember: original values are on the right half of the 2x sized array
            //computed values are on the left side of the array
            tree[i] = originalValues[2*i] + originalValues[2*i+1];
        }
    }
    
    /**
     * Returns the sum from a certain position in an array, using the pre-computed segment tree.
     * 
     * @param fromPosition
     * @param toPosition
     * @return
     */
    public int sumFrom(int fromPosition, int toPosition) {
        //start with the right side of the array
        fromPosition += originalValueSize;
        toPosition += originalValueSize;
        int sum = 0;
        
        //on each iteration of the loop, the index variables 'fromPosition' and 'toPosition' are halved. 
        //Thus, on each iteration we go up one level on the tree.
        while(fromPosition < toPosition) {
            
            //if index is even, then this is the "left" child node, no-op
            //we only care about right nodes:
            if ((fromPosition % 2) == 1) { // 'from' is odd, so it is the right child of its parent, then interval includes node 'from' but doesn't include its parent
                sum = Math.max(sum, tree[fromPosition]);
                fromPosition++;
            }
            if ((toPosition % 2) == 1) { // 'to' is odd, so it's the right child of its parent, then use the parent value
                toPosition--;
                sum = Math.max(sum, tree[toPosition]);
            }
            //move one level up the tree
            fromPosition /= 2; 
            toPosition /=2;     
        }
        
        return sum;
    }
}
