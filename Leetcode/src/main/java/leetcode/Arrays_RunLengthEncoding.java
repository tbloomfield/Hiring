package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1868. Products of Two Run-Length Encoded Arrays (array compression)
 * https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/description/
 * 
 * For example, nums = [1,1,1,2,2,2,2,2] is represented by the run-length
 * encoded array encoded = [[1,3],[2,5]]. Another way to read this is "three 1's
 * followed by five 2's". 
 * 
 * The NAIVE product of two run-length encoded arrays encoded1
 * and encoded2 can be calculated using the following steps:
 * 
 * 1. Expand both encoded1 and encoded2 into the full arrays nums1 and nums2
 * respectively. 
 * 2. Create a new array prodNums of length nums1.length and set
 * prodNums[i] = nums1[i] * nums2[i]. 
 * 3. Compress prodNums into a run-length encoded array and return it.
 * 
 * Answer:
 * In the best case we don't actually have to expand the encoded strings to calculate
 * the product.  We can use 2 pointers, one for elements in the first encoded array
 * and another for the second encoded array.  Calculate the product of both, then
 * decrement the count of the number of times each number repeats.  Advance the pointer
 * which doesn't have any repeating numbers remaining.
 */
public class Arrays_RunLengthEncoding {
    
    public static void main(String[] args) { 
        Arrays_RunLengthEncoding test = new Arrays_RunLengthEncoding();
        var results = test.findRLEArray_Naive(new int[][]{{1,3},{2,3}}, new int[][]{{6, 3}, {3,3 }});
        System.err.println(results);
        results = test.findRLEArray(new int[][]{{1,3},{2,3}}, new int[][]{{6, 3}, {3,3 }});
        System.err.println(results);

    }
    
    /**
     * Removes encoding and re-coding array results for better performance.
     * Lowers the overall storage cost since we don't need to hold
     * expanded results.  NOTE that this only works when encoded values can't include
     * zero, since zero in one encoding could result in an incorrect product. 
     * 
     * @param encoded1
     * @param encoded2
     * @return
     */
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        
        List<List<Integer>> results = new ArrayList<>();
        
        //zero length array
        if(encoded1.length == 0) { 
            return results;
        }
        
        int encoded1pointer = 0;
        int encoded2pointer = 0;
        
        while(encoded1pointer < encoded1.length && encoded2pointer < encoded2.length) {            

            int[] encodedTuple1 = encoded1[encoded1pointer];
            int[] encodedTuple2 = encoded2[encoded2pointer];
            
            //encoded numeric values
            int val1 = encodedTuple1[0];
            int val2 = encodedTuple2[0];
            
            //how many times should we calculate the product?
            int encodedTimes1 = encodedTuple1[1];
            int encodedTimes2 = encodedTuple2[1];
            int calculateTimes = Math.min(encodedTimes1, encodedTimes2);
            int product = val1 * val2;
            
            //calculate the encoded product and add to result list
            //this will already be in encoded format.
            //check to see that this product wasn't stored previously
            //this fixes issues like {1, 3}, {2, 3} and {6,3}, {3, 3} which would result in 
            //{6,3} {6,3}
            if(results.size() > 0 && results.get(results.size()-1).get(0) == product) { 
                //update the previous size.
                int previousCount = results.get(results.size()-1).get(1);
                results.get(results.size()-1).set(1, previousCount + calculateTimes);
            } else { 
                //otherwise add a new encoded product.
                results.add(Arrays.asList(product, calculateTimes));
            }            
            
            //advance pointers & update product remaining
            //whichever tuple has no elements to multiply left should have its pointer
            //advanced to the next element.  
            if( encodedTuple1[1] > encodedTuple2[1] ) {
                encodedTuple1[1] = encodedTuple1[1] - calculateTimes;
                encoded2pointer ++;
            } else if (encodedTuple1[1] < encodedTuple2[1] ) {
                encodedTuple2[1] = encodedTuple2[1] - calculateTimes;
                encoded1pointer ++;
            } else { //two are equal (zero)
                encoded1pointer ++;
                encoded2pointer ++;
            }
        }
        
        return results;
    }
    
    //O(n + m) runtime where n = size of encoded array 1 and m = size of encoded array 2.
    //Storage = O(n) 
    public List<List<Integer>> findRLEArray_Naive(int[][] encoded1, int[][] encoded2) {        
        //guaranteed to be the same sizes:
        List<Integer> expanded1 = expand(encoded1);
        List<Integer> expanded2 = expand(encoded2);
        
        List<Integer> product = new ArrayList<>();        
        for(int i = 0; i < expanded1.size(); i++) { 
            product.add(expanded1.get(i) * expanded2.get(i));
        }
        
        //now compress the results
        return compress(product);
    }
    
    private List<List<Integer>> compress(List<Integer> expanded){ 
        List<List<Integer>> results = new ArrayList();
        
        if(expanded.size() == 0) { 
            return results;
        }
        int lastNumber = expanded.get(0); //the last number encoded
        int count = 1; //a count of repeated numbers we can encoded
        for(int i = 1; i < expanded.size(); i++) {
            //the number sequence has changed.  
            if(lastNumber != expanded.get(i)) {
                results.add(List.of( lastNumber, count));
                lastNumber = expanded.get(i);
                count = 0;
            } 
            count ++;
        }
        
        //handle the last number in a list which won't be handled by the for 
        //loop above.
        if(count  > 0) { 
            results.add(List.of(lastNumber, count));
        }
        
        return results;
    }
    
    private List<Integer> expand(int[][] encoded){ 
        List<Integer> expanded = new ArrayList<>();
        for(int i = 0; i < encoded.length; i++) {
            int[] tuple = encoded[i];
            int val = tuple[0];
            int times = tuple[1];
            for(int j = 0; j < times; j++) {
                expanded.add(val);
            }
        }
        return expanded;
    }
}
