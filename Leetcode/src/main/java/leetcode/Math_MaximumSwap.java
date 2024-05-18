package leetcode;

/**
 * 670. Maximum Swap 
 * https://leetcode.com/problems/maximum-swap/description/
 * 
 * You are given an integer num. You can swap two digits at most once to get the
 * maximum valued number.
 * 
 * Return the maximum valued number you can get.
 * 
 * Complexity: O(n) as we have to iterate through each element in our number array
 * Storage: O(n) where n is the number of digits in the input number
 */
public class Math_MaximumSwap {
    
    public static void main(String[] args) { 
        Math_MaximumSwap test = new Math_MaximumSwap();
        int results = test.maximumSwap(2736);
        //7236
        System.err.println(results);
    }
    public int maximumSwap(int num) {
        char[] numberCharacters = Integer.toString(num).toCharArray();
        
        //for each integer in the number, update it with the highest-bit index(IE this allows
        //us to track the order of digits if there are multiple of the same digits, so we 
        //know what the highest digit to swap is)
        int[] digitOrder = new int[10];
        System.err.println("\n");
        for (int i = 0; i < numberCharacters.length; i++) {
            System.err.println(i + "/" + (numberCharacters[i]-'0'));
            //each of the digits in the number being passed is stored with an index 
            digitOrder[numberCharacters[i] - '0'] = i;
        }

        //starting with the maximum place value, look for the maximum lowest-place
        //order value to swap with.
        for (int i = 0; i < numberCharacters.length; i++) {
            
            //since we're looking for the largest number, start with 9 first
            for (int digit = 9; digit > numberCharacters[i] - '0'; digit--) {
                
                int lowestPlacedDigit = digitOrder[digit];
                
                //swap characters
                if (lowestPlacedDigit > i) {
                    char tmp = numberCharacters[i];
                    numberCharacters[i] = numberCharacters[digitOrder[digit]];
                    numberCharacters[digitOrder[digit]] = tmp;
                    return Integer.valueOf(new String(numberCharacters));
                }
            }
        }
        return num;
    }
}
