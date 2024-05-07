package leetcode;

/**
 * 50. Pow(x, n) https://leetcode.com/problems/powx-n/
 * 
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * 
 * Answer:
 * 
 * The naive way is to loop through, multiplying x (n) times - for the case of a
 * very large number this would be a lot of iterations.
 * 
 * Binary exponentiation, also known as exponentiation by squaring, is a
 * technique for efficiently computing the power of a number. By repeatedly
 * squaring "x" and halving "power", we can quickly compute xnx^nx using a
 * logarithmic number of multiplications.
 * 
 * The rule is that x^n can be expressed as:
 *  (x^2)^(n/2) if n is even
 *  x * (x^2)^(n-1)/2 if n  is odd.
 * 
 * Complexity: O(log n), each iteration reduces the "power" by 1's. Storage:
 * O(1) no additional storage is needed
 */
public class Math_Power {
    
    /**
     * In order to improve efficiency of looping:
     *    x *=x (n) times, which results in (n) loops, 
     * use the binary representation of the exponent (n):
     *     only multiply the power by x if the bit is odd,
     *     and multiplying x by itself if the bit is even (or 0).
     *     
     *     THe binary representation of every even number ends with zero and for 
     *     odd numbers it ends with 1.
     * 
     * O log2(N)
     * @param x
     * @param n
     * @return
     */
    public double myPow(double num, int power) {
        
      //any num^0 is always 1, short circuit  
      if(power == 0) { 
          return 1;
      }
      
      //handle negative powers
      if(power < 0){
          power = -power; //make power positive
          num = 1 / num;
      }
      
      double result = 1;
      
      while(power != 0){
          if(power % 2 == 1){ //power is odd
              result *= num;
              power -= 1;
          } 
          //square 'x' and reduce 'n' by 1/2 (per rule)
          num *= num;
          power = power / 2;
      }
      
      return result;
    }
}
