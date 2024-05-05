package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 * https://leetcode.com/problems/minimum-window-substring/description/
 * 
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring
  of s such that every character in t (including duplicates) is included in the window. 
  If there is no such substring, return the empty string "".
 *
 * Runtime: O(t) where t is # of characters in T + O(s) where s is the length of the string to search
 *        = O(t+s)
 * Space: O(t) - keep a map of characters in (t).
 *   
 */
public class String_MinimumWindowSubstring {
    
    public static void main(String[] args) { 
        String_MinimumWindowSubstring s = new String_MinimumWindowSubstring();
        System.err.println(s.minWindow("ADOBECODEBANC", "ABC"));
    }
    
    /*
     * In this implementation we use a sliding window which grows and contracts as we find the desired
     * target letters
     */
    public String minWindow(String s, String t) {
        Map<Character, /*count in t*/ Integer> targetCharacters = new HashMap<>();
        
        //O(t)
        for(char c: t.toCharArray()) {
            int count = targetCharacters.getOrDefault(c, 0);
            targetCharacters.put(c, count+1);
        }
        
        //current L and R pointers
        int lPointer = 0;
        //int rPointer = 0;
        
        //track the current minimum L and R pointers
        int smallestLPointer = -1;
        int smallestRPointer = -1;
        int smallestWindow = Integer.MAX_VALUE;
        
        //when counter goes to 0 we have a match
        int neededCharacters = t.length();
        
        for(int rPointer = 0; rPointer < s.length(); rPointer++) { 
            char c = s.charAt(rPointer);
            
            if(targetCharacters.containsKey(c)) {
                int currentCount = targetCharacters.get(c);
                if(currentCount > 0) { 
                    neededCharacters --;
                }
                targetCharacters.put(c, currentCount-1);
            }
            
            //when target characters is empty, then we have a valid match; store the match
            //and try to move the left pointer to the right to see if we can make the window even smaller.
            while(neededCharacters == 0) {
                
                //found window is currently the smallest.  Update it: 
                if(smallestWindow > (rPointer - lPointer)) {
                    smallestLPointer = lPointer;
                    smallestRPointer = rPointer;
                    smallestWindow = (rPointer - lPointer);
                }
                
                //now advance the lpointer to see if we can make this window smaller
                //each advancement of lPointer to the right adds the character at lpointer
                //potentially back into the map of needed characters.
                char toAdd = s.charAt(lPointer);
                if(targetCharacters.containsKey(toAdd)) {
                    int currentCount = targetCharacters.get(toAdd) + 1;
                    if(currentCount>0) { 
                        neededCharacters ++;
                    }
                    targetCharacters.put(toAdd, currentCount); 
                }
                lPointer++;
            }
        }
        
        if(smallestLPointer != -1 && smallestRPointer != -1) { 
            return s.substring(smallestLPointer, smallestRPointer+1);
        } else  { 
            return "";
        }
    }
}
