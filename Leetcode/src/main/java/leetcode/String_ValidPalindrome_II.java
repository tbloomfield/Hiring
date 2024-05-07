package leetcode;

/**
 * 680. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome-ii/description/ Given a string
 * s, return true if the s can be palindrome after deleting at most one
 * character from it.
 * 
 * Solution: 
 * Use 2 pointers L and R, iterate toward the middle.  If we find a mismatched character, we need
 * to determine which character to delete (the left character or right character).  Therefore we 
 * need to try deleting both 
 * 
 * Complexity: O(n), we loop through all characters call checkSubPalindrome up to twice to see
 * which character to remove
 * Storage: O(1), no additional storage is used
 */
public class String_ValidPalindrome_II {
    public boolean validPalindrome(String s) {
        int lpointer = 0, rpointer = s.length() - 1;

        while (lpointer < rpointer) {
            
            //found a mismatch - check to see which side needs to have a character deleted
            if (s.charAt(lpointer) != s.charAt(rpointer)) {
                //if we delete the left character, is this a valid palindrome?
                return checkSubPalindrome(s, lpointer + 1, rpointer) || 
                        //if we delete the right character, is this a valid palindrome?
                        checkSubPalindrome(s, lpointer, rpointer - 1);
            }
            lpointer++;
            rpointer--;
        }
        return true;
    }

    private boolean checkSubPalindrome(String s, int lpointer, int rpointer) {
        while (lpointer < rpointer) {
            if (s.charAt(lpointer) != s.charAt(rpointer)) {
                return false;
            }
            lpointer++;
            rpointer--;
        }
        return true;
    }
}
