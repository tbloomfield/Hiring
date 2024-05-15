package leetcode;

import leetcode.dto.ListNode;

/**
 * 708. Insert into a Sorted Circular Linked List
 * https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/description/
 * 
 * Given a Circular Linked List node, which is sorted in ascending order,
 * write a function to insert a value insertVal into the list such that it
 * remains a sorted circular list. The given node can be a reference to any
 * single node in the list and may not necessarily be the smallest value in the
 * circular list.
 * 
 * Answer:
 * Loop through the list, tracking the current and next values to determine an insertion point.
 * 
 * Complexity: O(n) where n is the number of items in the linked list
 * Storage: O(1), items are modified in-place.
 */
public class LinkedList_InsertIntoCircular {
    
    public static void main(String[] args) { 
        LinkedList_InsertIntoCircular test = new LinkedList_InsertIntoCircular();
        
        //create test node
        //ListNode testNode = new ListNode(3, new ListNode(4, new ListNode(1)));
        //testNode.next.next.next = testNode;
        
        ListNode testNode = new ListNode(1);
        testNode.next = testNode;
        //make the last node point to head.
        
        
        var results = test.insert(testNode, 0);
        System.err.println("done");
    }
    
    public ListNode insert(ListNode head, int insertVal) {
        
        if(head == null) {
            ListNode n = new ListNode(insertVal);
            n.next = n;
            return n;                
        }
        
        ListNode current = head;
        ListNode next = head;
        
        //track the node position where order resets.  For example:
        //1, 2, 3, 0 
        //the ascendingTail element would be "3", as everything prior to 3 is ascending
        ListNode ascendingTail = head;
        
        do {
            next = current.next;            
            //the position for our value 
            if(current.val <= insertVal && next.val > insertVal) {                
                //insert between the last and current nodes
                ListNode n = new ListNode(insertVal);
                n.next = next;
                current.next = n;
                return head;
            } else if(current.val > next.val) { 
                ascendingTail = current;
            }
            current = next;
            
        } while(current != head);
        
        //if getting here we made a complete cycle which means the head value should be 
        //appended to the ascending tail.  For example:
        //1, 2, 3 insert "4", the end of our ascending values would be "3"
        ListNode n = new ListNode(insertVal);
        n.next = ascendingTail.next;
        ascendingTail.next = n;
        
        return head;
    }
}
