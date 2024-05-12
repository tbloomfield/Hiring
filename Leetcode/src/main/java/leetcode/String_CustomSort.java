package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 791. Custom Sort String
 * https://leetcode.com/problems/custom-sort-string/description/
 * 
 * You are given two strings order and s. All the characters of order are unique
 * and were sorted in some custom order previously.
 * 
 * Permute the characters of s so that they match the order that order was
 * sorted. More specifically, if a character x occurs before a character y in
 * order, then x should occur before y in the permuted string.
 * 
 * Return any permutation of s that satisfies this property.
 * 
 * Answer:
 * A basic solution is to create a custom comparator using the order specified in 
 * the input.  Convert the string to a character array, then sort the character array using a comparator.
 * 
 * Complexity: O(nlogn) for sorting the array
 * Storage: O(n) to convert the input to a character array for modification.
 * 
 * Answer 2 (better answer):
 * Since the order is specified in the ordered string, and any characters not appearing in the ordered
 * string can be output in any order, we can get better efficiency by not comparing each
 * character in the input string.
 * 
 * - Create a map, counting the occurrences of each character.
 * - Iterate through each character in the order array.  For each, lookup the occurrences of the character
 * - in the input string and output those characters.
 * - For any remaining characters not in the order list, output those characters in a random order.
 * 
 * Complexity: O(max(n or m) ) to iterate through each character in the ordered array or string array 
 * Storage: O(n) to store each input character in a hashmap.  
 */
public class String_CustomSort {
    public static void main(String[] args) { 
        String_CustomSort test = new String_CustomSort();
        String results = test.customSortString("cba", "abcd");
        System.err.println(results);
        results = test.customSortString_Frequency("bcafg", "abcd");
        System.err.println(results);
        assert results.equals("cdba");
    }
    
    //O(nlogn)
    public String customSortString(String order, String s) {        
        //create a custom comparator for the target string "s" based on
        //an ordering map of character -> index
        Character[] charArray = new Character[s.length()];
        for(int i = 0; i < s.length(); i ++) {            
            charArray[i] = s.charAt(i);
        }
        
        Arrays.sort(charArray, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return order.indexOf(o1) - order.indexOf(o2);
            } 
        });
        
        StringBuilder builder = new StringBuilder();
        for (Character c : charArray) {
            builder.append(c);
        }
        return builder.toString();
    }
     
    public String customSortString_Frequency(String order, String s) {        
        //loop through s string, updating frequency count in a map
        Map<Character, Integer/*count*/> characterOccuranceMap = new HashMap<>();        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            characterOccuranceMap.put(c, characterOccuranceMap.getOrDefault(c, 0)+1);            
        }        
        
        //create our output string
        StringBuilder sb = new StringBuilder();
        
        //ordered characters come first:
        for(int i = 0; i < order.length(); i++) {
            char character = order.charAt(i);            
            
            //lookup the frequency of this character.
            int appendCharCount = characterOccuranceMap.getOrDefault(character,0);
            for(int j = 0; j < appendCharCount; j++) { 
                sb.append(character);                
            }
            characterOccuranceMap.remove(character);
        }
        
        //all other characters not specified in the order come last
        for(var entry : characterOccuranceMap.entrySet()) { 
            int appendCharCount = entry.getValue();
            for(int j = 0; j < appendCharCount; j++) {
                sb.append(entry.getKey());                
            }
        }
        
        return sb.toString();
    }
}
