package leetcode;

/**
 * 2.  Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/description/
 * 
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * 
 * Answer:
 * For this solution we iterate over both lists, adding elements in order; the trick here is to identify a
 * carry operation between nodes.
 * 
 * Runtime: O(n) where n is the number of elements in L1 and L2, whichever is larger.
 * Storage: O(n) the result node is equal to the amount of entries in L1 or L2, whichever is greater
 */
public class LinkedList_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode results = new ListNode(0); // a dummy node
        ListNode current = results;
        
        int carry = 0;
        while(l1 != null || l2 != null) {
            
            int sum = (l1 != null ? l1.val : 0) + (l2!=null ? l2.val : 0) + carry;
            carry = sum/10;
            
            current.next = new ListNode(sum % 10);  //ignore the carry
            current = current.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        
        if(carry != 0) {       
            current.next = new ListNode(carry);
        }
        
        
        return results.next;
    }
    
    
    //provided by leetcode:
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
