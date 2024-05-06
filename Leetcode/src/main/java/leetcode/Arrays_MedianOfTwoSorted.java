package leetcode;

/**
 * 4. Median of Two Sorted Arrays
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * 
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return
 * the median of the two sorted arrays.  Involves merging two arrays then finding the median 
 * values.
 * 
 * Answer:
 * 
 * Time: O(logmn) Since the arrays are sorted and we only want the median, we only need to evaluate
 *     HALF  of the entries.  Thus we should be able to do this in log(nm) time where n = number
 *     of entries in array 1 and m = number of entries in array2
 * Storage: O(1) no additional storage is needed. 
 * 
 * 
 * Array 1: {-1,1,4,7,9}  median = 4
 * Array 2: {2,4,5,6,7}   median = 5
 * Merged:  {-1,1,2,4,4,5,6,7,7,9}
 * 
 * 1,2,3,4
 */
public class Arrays_MedianOfTwoSorted {
    
    public static void main(String[] args) { 
        Arrays_MedianOfTwoSorted test = new Arrays_MedianOfTwoSorted();
        System.err.println(test.findMedianSortedArrays(new int[]{3,4}, new int[]{1,2}));
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //determine the median point of the "combined" array (add 1 to make this a 1-based offset)
        int combinedArrayPivot = (nums1.length + nums2.length + 1) / 2;
        
        //having the smaller of the 2 arrays as the first array makes for easier logic and edge case
        //handling.  If the smaller array could be either nums1 or nums2 we'd end up with a lot of 
        //extra conditional logic.
        int[] array1, array2;
        if(nums1.length >  nums2.length) {
            array1 = nums2;
            array2 = nums1;
        } else {
            array1 = nums1;
            array2 = nums2;
        }
        
        //calculate the median "pivot" point of the first array
        int array1Pivot = array1.length / 2;
        
        //the pivot for the second array should be half of the combined array size - array1 pivot
        int array2Pivot = combinedArrayPivot - array1Pivot;  //subtract 2 since the length of array1pivot and combined pivot is 1 based.
        
        //we now have pivots to create partitions in both arrays.  Check that the pivots
        //are correct; the maximum value on the left side of the pivot should be less than the 
        //minimum value on the right side;  if not, adjust until they are
        while( arrayVal(array1,array1Pivot-1) > arrayVal(array2 ,array2Pivot) ||
                arrayVal(array2,array2Pivot-1) > arrayVal(array1 ,array1Pivot)) { 
            
            if(array1[array1Pivot-1] > arrayVal(array2 ,array2Pivot)) {
                //move pivot back 1
                array1Pivot --;
                array2Pivot ++;
            } else { 
                array2Pivot --;
                array1Pivot ++;
            }            
        }
        
        //now that we've ensured we have the proper pivots for each array, calculate the median:
        double median;
      
        //get the actual values:
        int maxArray1L = array1Pivot >=0 && array1Pivot < array1.length ? array1[array1Pivot] : Integer.MIN_VALUE;
        int maxArray2L = array2Pivot >=0 && array2Pivot < array2.length ? array2[array2Pivot] : Integer.MIN_VALUE;
        int minArray1R = array1Pivot+1 >=0 && array1Pivot +1 < array1.length ? array1[array1Pivot+1] : Integer.MAX_VALUE;
        int minArray2R = array2Pivot+1 >=0 && array2Pivot +1 < array2.length ? array2[array2Pivot+1] : Integer.MAX_VALUE;
        
        //for even lengths we need 2 median values averaged
        if( (nums1.length + nums2.length) % 2 == 0) {
            //get the max of the left partition
            median = (
                    Math.max(maxArray1L, maxArray2L) + 
                    Math.min(minArray1R, minArray2R))  / 2d;
        }
        //for odd lengths we know the median is exactly the halfway point (which will be rounded up)
        else { 
            median = Math.max(maxArray1L, maxArray2L);
        }
        return median;
    }
    
    public int arrayVal(int[] array, int position) {
        if(position < 0) { 
            return Integer.MIN_VALUE;
        }
        if(position >= array.length) {
            return Integer.MAX_VALUE;
        } else  { 
            return array[position];
        }
    }
}