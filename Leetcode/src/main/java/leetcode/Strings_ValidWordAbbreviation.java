package leetcode;

/**
 * 408. Valid Word Abbreviation
 * https://leetcode.com/problems/valid-word-abbreviation/description/ A string
 * can be abbreviated by replacing any number of non-adjacent, non-empty
 * substrings with their lengths. The lengths should not have leading zeros.
 * 
 * For example, a string such as "substitution" could be abbreviated as (but not
 * limited to):
 * 
 * "s10n" ("s ubstitutio n") "sub4u4" ("sub stit u tion") "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on") "substitution" (no substrings replaced)
 * 
 * Answer: use character arrays and convert numbers in the abbreviation into pointer lookaheads in the
 * string.
 * 
 * Complexity: O(n+m) where n = length of word and m = length of abbreviation.  In worst case 
 * scenario they're both the same
 * Storage: O(1) no new storage is created.
 */
public class Strings_ValidWordAbbreviation {
    public static void main(String[] args) { 
        Strings_ValidWordAbbreviation test = new Strings_ValidWordAbbreviation();
        System.err.println(test.validWordAbbreviation("internationalization", "i5a11o1"));
    }
    
    public boolean validWordAbbreviation(String word, String abbr) {
        int wp = 0, i = 0;

        while(i < abbr.length() && wp < word.length()) {            
            char abbrChar = abbr.charAt(i);
            
            //character match if the abbreviation is a character
            if (Character.isAlphabetic(abbrChar)) {
                if (abbrChar != word.charAt(wp)) {
                    return false;
                }
                wp ++;
                i ++;                
            }

            // hit a number... look ahead until the first non number to parse the number of
            // digits to skip
            else {
                // parse the number; this is slightly tricky since we have to handle digits that
                // are greater than 9               
                if(abbrChar == '0') { 
                    return false;
                }
                int lookAheadNum = 0;
                while (i < abbr.length() && Character.isDigit(abbr.charAt(i))) {
                    lookAheadNum *= 10;
                    lookAheadNum += abbr.charAt(i) - '0';
                    i++;
                }
                wp += lookAheadNum;
            }
        }
        return wp == word.length() && i == abbr.length();
    }
}
