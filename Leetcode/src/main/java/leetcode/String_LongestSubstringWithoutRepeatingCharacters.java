package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. Longest Substring without repeating characters.
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * Given a string s, find the length of the longest substring without repeating
 * characters.
 * 
 * Use a sliding window and a set of seen characters to determine the largest
 * window. This is similar to "76. Minimum Window Substring containing duplicate
 * letters"
 * 
 * Runtime: O(2n) = O(n), in the worst case we'll visit each character by both the right and left pointer
 * Storage: O(k) where k is the number of unique characters we encounter.
 */
public class String_LongestSubstringWithoutRepeatingCharacters {
    
    public static void main(String[] args) { 
        String_LongestSubstringWithoutRepeatingCharacters test = new String_LongestSubstringWithoutRepeatingCharacters();
        System.err.println(test.lengthOfLongestSubstring("aabaab!bb"));
    }
    
    public int lengthOfLongestSubstring(String s) {

        int maxSubstringLen = 0;
        
        //a set of all seen characters
        Set<Character> characterSet = new HashSet<>();

        int lpointer = 0;
        int rpointer = 0;

        for (; rpointer < s.length(); rpointer++) {
            char rPointerChar = s.charAt(rpointer);
            
            //returns false if the character is already in our set, meaning we have a duplicate.
            //advance the left pointer until all of the seen characters are unique again
            while(!characterSet.add(rPointerChar)) {                
                characterSet.remove(s.charAt(lpointer));
                lpointer ++;
            }
            
            maxSubstringLen = Math.max(maxSubstringLen, characterSet.size());
        }

        return maxSubstringLen;
    }
}
