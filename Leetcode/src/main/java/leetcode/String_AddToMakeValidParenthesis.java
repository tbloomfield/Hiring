package leetcode;

/**
 * 921. Minimum Add to Make Valid Parenthesis
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/
 * 
 * A parentheses string is valid if and only if:
 * 
 * - It is the empty string, 
 * - It can be written as AB (A concatenated with B), where A and B are valid strings,
 * - It can be written as (A), where A is a valid string. 
 *  
 *  You are given a parentheses string s. In one move, you can
 * insert a parenthesis at any position of the string.
 * 
 * For example, if s = "()))", you can insert an opening parenthesis to be
 * "(()))" or a closing parenthesis to be "())))". Return the minimum number of
 * moves required to make s valid.
 * 
 * Answer: 
 * Since we only need to know a count of imbalances, track the amount of open parenthesis '('.
 * When we see a closed parenthesis, and there is an open (, decrement the amount of "('
 * If we see a closed parenthesis, and there is no open ( preceeding it, increment the ')' imbalance.
 * 
 * At the end return both the '(' and ')' imbalance sum.
 * 
 * Runtime: O(n) all characters in the string need to be traversed. 
 * Storage: O(1) we only keep a running sum primitive types.
 */
public class String_AddToMakeValidParenthesis {
    
public int minAddToMakeValid(String s) {
        int lParentImbalance = 0;
        int rParentImbalance = 0;

        //O(n) time where n = length of string        
        for(int i = 0; i < s.length(); i ++){ 
            char c = s.charAt(i);

            if(c == '(' ) {
                lParentImbalance ++;
            } else {
                //close ( parenthesis 
                if(lParentImbalance > 0){ 
                    lParentImbalance --;
                } else {
                    //no '(' preceeds this, we would need to add one for this to be balanced
                    rParentImbalance ++;
                }                
            }
        }
        return lParentImbalance + rParentImbalance;
    }
}
