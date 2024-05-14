package leetcode;


/**
 * 227. Basic Calculator
 * https://leetcode.com/problems/basic-calculator-ii/description/ Given a string
 * s which represents an expression, evaluate this expression and return its
 * value.
 * 
 * The integer division should truncate toward zero.
 * 
 * Answer:
 * Simple string traversal and parsing.  For each position of "s", check:
 *   If digit, store, allowing for any subsequent digits to be added.  This variable is "found Number"
 *   If operator (+-/*) follow order of operations. Store operator in a variable "last operator" *   
 *      For division and multiplication, update the a variable "last number" /* foundNumber
 *      For addition and subtraction, update the currentSum +- the "last number"
 * 
 * Complexity: O(n) looping through each digit of "s"
 * Storage: O(1) as there is no real storage other than updating simple primitives.
 */
public class String_BasicCalculator {

    //O(n) storage = O(1)
    public int calculate(String s) {
        int foundNumber = 0;
        char lastOperator = '+';
        int lastNumber = 0, curSum = 0;
        
        for(int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            
            if(Character.isDigit(c)) {
                int digitVal = c - '0';
                foundNumber *= 10;
                foundNumber += digitVal;                
            }

            // special characters AND if
            if (isOperator(c) || i == s.length() - 1) {                
                if (lastOperator == '+') {
                    curSum += lastNumber;
                    lastNumber = foundNumber;
                } else if (lastOperator == '-') {
                    curSum += lastNumber;
                    lastNumber = -foundNumber;
                } else if (lastOperator == '*') {
                    lastNumber *= foundNumber;
                } else if (lastOperator == '/') {
                    lastNumber /= foundNumber;
                }
                lastOperator = c;
                foundNumber = 0;
            }
        }
        int grandTotal = curSum += lastNumber;
        return grandTotal;
    }
    boolean isOperator(char c) { 
        return c == '+' || c == '-' || c == '/' || c == '*';
    }
}
