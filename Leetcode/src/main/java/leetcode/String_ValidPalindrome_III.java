package leetcode;

/**
 * 1216. Valid Palindrome III
 * 
 * https://leetcode.com/problems/valid-palindrome-iii/description/
 * 
 * Given a string s and an integer k, return true if s is a k-palindrome.
 * 
 * Given a string s, return true if the s can be palindrome after deleting at most k
 * characters from it.
 * 
 * Answer:
 * This question differs from Valid Palindrome II in that we're allowed to delete up to k
 * characters to make it valid, instead of only deleting a single character. 
 */
public class String_ValidPalindrome_III {
    
    //a simple cache of left and right pointer indexes
    //which have been visited, along with the number of deletions needed
    //at this index in order to make the target string a valid palindroms
    Integer[][]memo;
    
    //copy input string to evaluate into a global to clean up the recursive 
    //call to count()
    String s;

    public boolean isValidPalindrome(String s, int k) {
        this.s = s;
        memo = new Integer[s.length()][s.length()];

        //count the number of recursive iterations needed to split the string
        //until left and right sides matched
        int changes = count(0, s.length() -1);
        return changes <=k;
    }

    //decision matrix; for each character which doesn't match, recursively call count
    //to delete one character from the left or the right, then repeat the process.    
    private int count(int lpos, int rpos){ 
        //odd length middle
        if(lpos == rpos){ 
            return 0;
        }
        //even length middle
        if(lpos + 1 == rpos){ 
            return s.charAt(lpos) == s.charAt(rpos) ? 0 : 1;            
        }
        //don't re-evaluate previous branches.
        if(memo[lpos][rpos] != null){ 
            return memo[lpos][rpos];
        }
        //no deletion, continue traversal without incrementing count.
        if(s.charAt(lpos) == s.charAt(rpos)) {
            return count(lpos+1, rpos-1);
        }
        //characters don't match, increment the number of possible deletions
        //take the minimum of removing the left and right characters
        memo[lpos][rpos] = Math.min(1+ count(lpos+1, rpos), 1 + count(lpos, rpos-1));
        return memo[lpos][rpos];
    }
}
