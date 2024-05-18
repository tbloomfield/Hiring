package leetcode;

/**
 * 65.  Valid Number
 * https://leetcode.com/problems/valid-number/
 * 
 * Given a string s, return whether s is a valid number.
 * 
 * Answer:
 * Loop through each character in the string, tracking various cases to determine
 * if a number is valid or not.
 * 
 * Complexity: O(n)
 * Storage: O(n) since we're tracking the previous character in the algorithm
 */
public class String_IsNumber {
    
    /**
     * runtime is O(n)
     * storage is O(n) since we're tracking the previous character in the algorithm
     * 
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        //first character can be +-
        //must contain at least one decimal
        //"E" or "e" can be present after one digit
        //number may contain one dot
        boolean numberFound = false;
        boolean dotFound = false;
        boolean exponentFound = false;
        char previousChar = ' ';
        
        for(int i = 0; i < s.length() ; i++) { 
            char c = s.charAt(i);
            
            if(c == 'E' || c ==  'e' ) {
                //exponents must follow an integer & we can't have 2 exponents
                if(exponentFound || !numberFound) {
                    return false;
                }                
                exponentFound = true;
                numberFound = false; //we  need a number after this
                
            } else if(c == '.') {
                
                //can't have multiple decimals, can't have a decimal after an exponent.
                if(dotFound  || exponentFound) { 
                    return false;
                }
                dotFound = true;
            } else if(c == '+' || c == '-') {
                //signs can be at the 0 position or immediately following an E value
                if( i != 0 && (previousChar != 'E' && previousChar != 'e' 
                        && previousChar != '-')) {
                    return false;
                }
            } else if(c >= '0' && c <= '9') { 
                numberFound = true;
            } else { 
                return false;
            }
            previousChar = c;
        } 
        
        return numberFound;
    }
}
