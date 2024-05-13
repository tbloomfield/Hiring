package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 249. Group Shifted Strings
 * https://leetcode.com/problems/group-shifted-strings/description/
 * 
 * We can shift a string by shifting each of its letters to its successive
 * letter.
 * 
 * For example, "abc" can be shifted to be "bcd". We can keep shifting the
 * string to form a sequence.
 * 
 * For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd"
 * -> ... -> "xyz". Given an array of strings strings, group all strings[i] that
 * belong to the same shifting sequence. You may return the answer in any order.
 * 
 * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"] Output:
 * [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 * 
 * Answer:
 * 
 * abc -> bcd after 1 shift
 * 'a'+'b'+'c'
 *  0 + 1 + 2 = 3
 *  
 * 'b'+'c'+'d'
 *  1 + 2 + 3 = 6  
 *  6%3 == 0 so bcd is a shift from abc.
 *  
 *  'x'+'y'+'z'
 *  23 +24 +25 = 72
 *  72%3 == 0 so xyz is a shift from abc.  72%6==0 so xyz is also a shift from bcd.
 *  
 *  'a' + 'z'
 *  0   + 25 = 25
 *  
 *  'b' + 'a' 
 *   1  + 0 = 1  
 * 
 * calculate the numeric value of the characters in the string
 * Any answers which are within 25 characters of each other 
 * 
 */
public class Strings_GroupShiftedString {
    
    public static void main(String[] args) { 
        Strings_GroupShiftedString test = new Strings_GroupShiftedString();
    }
    
    public List<List<String>> groupStrings(String[] strings) {
        
        //step 1: sort strings by length; shifting operations can only be grouped
        //by length
        Arrays.sort(strings, (s1, s2) -> s1.length() - s2.length());
        
        List<List<String>> groupings = new ArrayList<>();
        
    }

}
