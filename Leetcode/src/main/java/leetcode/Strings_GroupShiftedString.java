package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Evaluate the relative distance between letters in the alphabet, supporting a cycle.
 * 
 * (26+26) % 26
 * 
 * ie : "az" = 0 | 4
 *      "ba" = | 25
 * 
 * calculate the numeric value of the characters in the string
 * Any answers which are within 25 characters of each other 
 * 
 * Complexity: O(n) n = number or strings * (O(c)) where c is number of characters in each string
 *             So overall complexity is O(n*c)
 * Storage: O(n) where n = number of strings + O(c)) where c is the number of character in each string
 *             So overall storage is O(n*c)
 */
public class Strings_GroupShiftedString {
    
    public static void main(String[] args) { 
        Strings_GroupShiftedString test = new Strings_GroupShiftedString();
        var results = test.groupStrings(new String[] { "abc","am" } );
        System.err.println(results);
    }
    
    /**
     * a hash function that results in string collisions if the distance
     * between characters is the same, IE:
     * a b = c d
     * @param s
     * @return
     */
    private final String getHash(String s) {
        char firstChar = s.charAt(0);
        StringBuilder sb = new StringBuilder(s.length());
        //append the length to make this unique to the number of character in the string
        //IE, without this, the following hashes would be the same:
        //abc = 012 and am=012
        sb.append(s.length());
        for(int i = 0; i < s.length(); i++) { 
            int val = (s.charAt(i) - firstChar + 26) % 26;
            sb.append(val);
        }
        return sb.toString();
    }
    
    public List<List<String>> groupStrings(String[] strings) {
        if(strings.length == 0) { 
            return List.of();
        }
        
        Map<String/*hashCode*/, List<String>> groupings = new HashMap<>();
        for(String s: strings) {
            String hash = getHash(s);
            List<String> stringsFromHash = groupings.getOrDefault(hash, new ArrayList<>());
            System.err.println("Hash=" + hash);
            stringsFromHash.add(s);
            groupings.put(hash, stringsFromHash);
        }
        
        //convert to list
        return new ArrayList<>(groupings.values());
    }
}
