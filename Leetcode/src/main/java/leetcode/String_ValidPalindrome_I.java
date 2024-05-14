package leetcode;

/**
 * 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/description/
 * 
 * A phrase is a palindrome if, after converting all uppercase letters into
 * lowercase letters and removing all non-alphanumeric characters, it reads the
 * same forward and backward. Alphanumeric characters include letters and
 * numbers.
 * 
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 
 * Answer:
 * Use 2 pointer to compare strings.
 * 
 * Complexity: O(n) where n is the length of the string to check
 * Storage: O(1) no additional storage is needed.
 */
public class String_ValidPalindrome_I {
    public static void main(String[] args) { 
        String_ValidPalindrome_I test = new String_ValidPalindrome_I();
        var result = test.isPalindrome("A man, a plan, a canal: Panama"); //true
        
        
    }
    public boolean isPalindrome(String s) {
        
        int left = 0;
        int right = s.length() - 1;
        
        if(right < 0) { 
            return true;
        }
        
        boolean result = true;
        while(left <= right) {
            if(!Character.isLetterOrDigit(s.charAt(right))) {
                right --;
            } else if(!Character.isLetterOrDigit(s.charAt(left))) {
                left ++;
            } else if(Character.toLowerCase(s.charAt(right)) != Character.toLowerCase(s.charAt(left))) {
                result=false;
                break;
            } else {             
                left ++;
                right --;
            }
        }
        return result;
    }
}
