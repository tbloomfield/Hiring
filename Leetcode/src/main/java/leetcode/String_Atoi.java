package leetcode;

/**
 * 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 * 
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit
 * signed integer.
 * 
 * The algorithm for myAtoi(string s) is as follows:
 * 
 * - Whitespace: Ignore any leading whitespace (" "). 
 * - Signedness: Determine the sign by checking if the next character is '-' or '+', 
 * assuming positivity is neither present. 
 * - Conversion: Read the integer by skipping leading zeros until
 * a non-digit character is encountered or the end of the string is reached. 
 * If no digits were read, then the result is 0. 
 * - Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], 
 * then round the integer to remain in the range. Specifically, integers less than -231 
 * should be rounded to -231, and integers greater than 231 - 1 should be rounded 
 * to 231 - 1.
 * - Return the integer as the final result.
 * 
 * 
 */
public class String_Atoi {
    
    public static void main(String[] args) { 
        String_Atoi test = new String_Atoi();
        int result = test.myAtoi("");
        System.err.println("result = " + result + " expected=-2147483648");
    }
    
    public int myAtoi(String s) {        
        long result = 0;
        boolean negative = false;
        
        if(s.isBlank()) { 
            return 0;
        }
        
        int i = 0;        
        while(i < s.length() && Character.isWhitespace(s.charAt(i))) { 
          i++;
        }
        
        //now get any sign
        if(s.charAt(i) == '-' || s.charAt(i) == '+') {           
           negative = (s.charAt(i) == '-');
           i++;
        }
        
        while(i < s.length() && Character.isDigit(s.charAt(i))) {
            //parse digit
            result *= 10;
            result += s.charAt(i) - '0';
            
            //prevent a long overflow
            if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) { 
                break;
            }  
            i++;
        }
        
        result = (negative ? -result : result);
        if(result > Integer.MAX_VALUE) { 
            return Integer.MAX_VALUE;
        } else if(result < Integer.MIN_VALUE) { 
            return Integer.MIN_VALUE;
        } else { 
            return (int) (result);
        }
    }
}
