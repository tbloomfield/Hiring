package leetcode;

/**
 * 50. Pow(x, n)
 * 
 * https://leetcode.com/problems/powx-n/
 * 
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
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
    public double myPow(double x, int n) {        
      if(n < 0){
          n = -n;
          x = 1 / x;
      }
      
      double pow = 1;
      
      while(n != 0){
          if((n & 1) != 0){ //equivelant to n%2 - only multiply when the number is odd
              pow *= x;
          } 
              
          x *= x;
          n >>>= 1;  //unsigned right shift ; equivalant to n = n/2... keep dividing the number by 2.
      }
      
      return pow;
    }
}
