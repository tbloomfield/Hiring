package leetcode;

import java.util.HashSet;
import java.util.Set;

import leetcode.dto.ListNode;

/**
 * 160. Intersection of Two linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 * 
 * Given the heads of two singly linked-lists headA and headB, return the node
 * at which the two lists intersect. If the two linked lists have no
 * intersection at all, return null.
 * 
 * Solution:
 * This is a tortoise / hare problem which can be solved by circularly looping through each 
 * list, comparing node values.
 * 
 * Create 2 pointers, and iterate to the next value in each list.  
 */
public class LinkedList_Intersection {
    
    public static void main(String[] args) { 
        LinkedList_Intersection test = new LinkedList_Intersection();
        //[4,1,8,4,5], listB = [5,6,1,8,4,5]
        var results = test.getIntersectionNode(
                new ListNode(4, new ListNode(1, new ListNode(8, new  ListNode(4, new ListNode(5))))),
                new ListNode(5, new ListNode(6, new ListNode(1, new  ListNode(8, new ListNode(4, new ListNode(5)))))));
        System.err.println(results);
    }
    
    //O(max|mn|) solution - we will traverse the lists at most (n) times
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //no intersection if one of our inputs is empty.
        if(headA == null || headB == null) return null;
        
        ListNode a = headA;
        ListNode b = headB;
        

        //case 1: lists are the same size - pointers will meet if there's an intersection, else a&b will be null, returning null
        //case 2:two lists may have different sizes; if so, swap the pointers when they hit the end
        //   of the list.  On the second pass the pointers will have offsets that will lead to the same
        //case 3: list are the same size with no intersection - both pointers will be  null as they hit the
        //end of the list
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;    
        }

        return a;
    }
    
    //Floyds cycle finding algorithm (tortoise and hare) can also be used to find the intersecting
    //node:
    public ListNode getIntersectionNode_FloydsCycle(ListNode headA, ListNode headB) {
        
        //make the linked lists a cycle:
        ListNode temp=headA;
        while(temp.next!=null){
            temp=temp.next;
        }
        
        ListNode tail=temp;
        temp.next=headA;
        
        ListNode slow=headB; //moves by 1 position each iteration
        ListNode fast=headB; //moves by 2 positions each iteration.  

        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            
            if(slow==fast){ //detected a cycle
                slow=headB;
                
                while(slow!=fast){
                    slow=slow.next;
                    fast=fast.next;
                }
                tail.next=null;
                return fast;
            }
        }
        
        tail.next=null;
        return null;
    }
}
