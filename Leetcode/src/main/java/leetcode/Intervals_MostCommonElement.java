package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * (Meta question)
 * 
 * Given (n) intervals / ranges of numbers, find the most commonly occuring element in the total 
 * set of elements.
 * 
 * Answer:
 * Use a combination sum to determine frequency of each number.  The start of the interval
 * will add 1 to array position (start).  The end of the interval will subtract -1 to 
 * array position(end+1)
 * 
 * For example
 * {1,3}, {2,5}, {0,10} ->
 *  {1,3} = 0 1 0 0 -1
 * +{2,5} = 0 1 1 0 -1 0 -1
 * +{0,10}= 1 1 1 0 -1 0 -1 0 0 0 -1
 * 
 *  Then calculate the cumulutive sum:
 *          1 2 3 3  2 2  1 1 1 1  0
 *  And pick the max occurance.  In this case 2 and 3 are the most commonly found
 *  elements in the interval.
 *  
 *  Runtime:
 *  O(i) where i = the number of intervals + O(n) where n = the number of elements 
 *  in every interval.  Therefore the total runtime is O(n).
 *  Storage: O(n) where n = the number of elements in every interval.
 */
public class Intervals_MostCommonElement {
    
    public static void main(String[] args) {
        Intervals_MostCommonElement test = new Intervals_MostCommonElement();
        int mostCommon = test.findCommon(new int[][] {{1,3}, {2, 5}, {0, 10}} );
        System.err.println(mostCommon);
    }
    
    public int findCommon(int[][] intervals) {
        //a list of numbers from min start...  max end
        //the position in the array equals the number in the range
        List<Integer> numberOccurances = new ArrayList<>();
        
        for(int[] interval: intervals) {
            int min = interval[0];
            int max = interval[1];
            
            //resize array
            while(numberOccurances.size() < max+1) {
                numberOccurances.add(0);
            }
            
            numberOccurances.add(min, numberOccurances.get(min) + 1);
            numberOccurances.add(max+1, numberOccurances.get(max+1) -1);                     
        }
                
        int rollingSum = -1;
        int maxSum = -1;
        int maxFoundElement = -1;
                
        //loop through number list creating a cumulative sum of elements
        //the element with the highest sum is the most frequently found.
        for(int i = 0; i < numberOccurances.size(); i++) {
            rollingSum += numberOccurances.get(i);
            if(rollingSum > maxSum) { 
                maxFoundElement = i;
                maxSum = rollingSum;
            }
        }        
        return maxFoundElement;
    }
}
