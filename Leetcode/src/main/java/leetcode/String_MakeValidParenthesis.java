package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/description/
 * 
 * Given a string s of '(' , ')' and lowercase English characters.
 * 
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any
 * positions ) so that the resulting parentheses string is valid and return any
 * valid string.
 * 
 * Formally, a parentheses string is valid if and only if:
 * 
 * It is the empty string, contains only lowercase characters, or It can be
 * written as AB (A concatenated with B), where A and B are valid strings, or It
 * can be written as (A), where A is a valid string.
 * 
 * Answer:
 * Use a stack, pushing indexes of open parenthesis on to the stack, and removing parenthesis index on closed parenthesis.
 * Upon not having an open parenthesis match, delete the parenthesis since it's invalid.
 * 
 * Cast the incoming string into a stringbuilder to perform in-place operations. 
 * 
 * Runtime: O(n) where n is the number of characters in String s.
 * Storage: O(n) where the stack can have up to the number of characters enqueued in the worst case scenario.
 */
public class String_MakeValidParenthesis {
    public String minRemoveToMakeValid(String s) {
        Deque<Integer> validParentStack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < sb.length(); i++) {
            char charAt = sb.charAt(i);

            if (charAt == '(') {
                validParentStack.offerLast(i);
            } else if (charAt == ')') {
                if (!validParentStack.isEmpty()) {
                    validParentStack.pollLast();
                } else {
                    // this is invalid
                    sb.deleteCharAt(i);
                    i--;  //re-evaluate the character in the new position.
                }
            }
        }

        //any remaining parenthesis in the stack will be invalid.
        while (!validParentStack.isEmpty()) {
            sb.deleteCharAt(validParentStack.pollLast());
        }
        return sb.toString();
    }
}
