package leetcode;

/**
 * 7. Reverse Integer https://leetcode.com/problems/reverse-integer/
 * 
 * Given a signed 32-bit integer x, return x with its digits reversed. If
 * reversing x causes the value to go outside the signed 32-bit integer range
 * [-231, 231 - 1], then return 0.
 * 
 * Assume the environment does not allow you to store 64-bit integers (signed or
 * unsigned).
 * 
 * Complexity: O(
 */
public class Math_ReverseInteger {
    public static void main(String [] args) { 
        Math_ReverseInteger test = new Math_ReverseInteger();
        int results = test.reverse(1463847412);
        System.err.println(results);
        results = test.reverse_usingLong(1463847412);
        System.err.println(results);
        
        
    }
    
    public int reverse(int x)  {
        int reversedDigits = 0;
        
        while(x!=0) { 
          //pop least significant digit
          int ones = x % 10;
          
          //check if shifting our existing digits by one to the left
          //is still within bounds.
          //max is 2147483647
          //min is -2147483648
          if(
                  (reversedDigits < Integer.MAX_VALUE / 10 ||
                  (reversedDigits == Integer.MAX_VALUE / 10 && ones <=7 )) &&
                  (reversedDigits > Integer.MIN_VALUE / 10 ||
                  (reversedDigits == Integer.MIN_VALUE / 10 && ones >=-8))
             ) {
              reversedDigits *= 10; 
              reversedDigits += ones;
          } else { 
              return 0;
          } 
           //shift x down one place
           x /= 10;
        }
        
        return reversedDigits;
    }
    
    /**
     * assuming we COULD store overflow in a long, it would look like this:
     * 
     * @param x
     * @return
     */
    public int reverse_usingLong(int x)  {
        
        //holder of our reversed digit; make sure that it's of long 
        //type so we can detect integer overflow when reversing.
        long reversedDigits = 0;  
        
        //make our number positive        
        while(x != 0){ 
            //pop least significant digit
            int ones = x % 10;

            //any existing digits get shifted left one.
            reversedDigits *= 10;
            reversedDigits += ones;
            x = x / 10; //shift the original number right one.
        }

        //ensure that our number is in the 32 bit range
        if(reversedDigits > Integer.MAX_VALUE || reversedDigits < Integer.MIN_VALUE){ 
            return 0;
        }
        
        //downcast back to our integer type
        return (int)reversedDigits;
    }
}
