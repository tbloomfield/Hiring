package leetcode;

import java.util.PriorityQueue;

import leetcode.dto.ListNode;

/**
 * 23. Merge K sorted Lists 
 * 
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * 
 * You are given an array of k linked-lists lists, each linked-list is sorted in
 * ascending order.
 * 
 * Merge all the linked-lists into one sorted linked-list and return it.
 * 
 * Answer:
 * use a priority queue to order each element in the list.
 * 
 * Complexity: O(n logn) - each element in the lists are inserted into the priority queue.
 *   The priority queue inserts in (logn) time.
 * Space: O(n) - each element from the linked list is inserted into the priority queue.
 */
public class LinkedList_MergeSortedLists {
    
    public static void main(String[] args) { 
        LinkedList_MergeSortedLists test = new LinkedList_MergeSortedLists();
        ListNode results = 
            test.mergeKLists(new ListNode[]{
            new ListNode(1, new ListNode(4, new ListNode(5))), 
            new ListNode(1, new ListNode(3, new ListNode(4))),
            new ListNode(2, new ListNode(6))
        });
        
        System.err.println(results);
    }
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);

        //O(n log n)
        //sort the *first* element in each list
        for(ListNode list : lists) {  
            if(list != null){            
                pq.offer(list);  //1,1,2
            }
        }
        
        //loop through PQ list, constructing a mega list node:
        ListNode mergedRoot = new ListNode(0);
        ListNode current = mergedRoot;

        //sort the next element in each list by inserting into the priority queue:
        //O(n log n);
        while(!pq.isEmpty()) {
            ListNode val = pq.poll();
            current.next = val;
            current = current.next;
            
            //sort the next element in each list
            if(val.next != null) { 
                pq.add(val.next);
            }
        }

        return mergedRoot.next;
    }
}
