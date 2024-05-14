package leetcode;

import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

/**
 * 138. Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 * 
 * A linked list of length n is given such that each node contains an additional
 * random pointer, which could point to any node in the list, or null.
 * 
 * Answer:
 * Deep copy of the linked list is pretty simple; the gotcha here is the random Node
 * reference which possibly references a node we haven't copied yet
 * 
 *  Use a hashmap of (old node)->(new node) references.  Make 2 passes:
 *    1) loop through (n) original nodes, creating a shallow copy without next or random references
 *    2) loop through (n) original nodes, setting next/ random equal to the 
 *       shallow copies created in step 1.
 *  
 *  Complexity: O(n) where n is the number of elements in the original list to copy
 *  Storage: O(n) we store each node into a map while copying nodes.
 */
public class LinkedList_CopyListWithRandomPointer {

    public static void main(String[] args) { 
        LinkedList_CopyListWithRandomPointer test = new LinkedList_CopyListWithRandomPointer();
        test.runTests();
    }
    
    public void runTests() { 

        //create our test case:
        Node tail = new Node(1);
        Node head = new Node(7);        
        Node i1 = new Node(13);
        Node i2 = new Node(11);
        Node i3 = new Node(10);
        
        head.next = i1;
        i1.next = i2;
        i2.next = i3;
        i3.next = tail;
        
        i1.random = head;
        i2.random = tail;
        i3.random = i2;
        tail.random = head;
        
        Node results = copyRandomList(head);
        System.err.println(results);
    }

    public Node copyRandomList(Node head) {
        
        Node copy = head;
        Map<Node/*old*/, Node/*new*/> nodes = new HashMap<>();
        
        //create shallow copy values.
        while(copy != null) {
            nodes.put(copy, new Node(copy.val));
            copy = copy.next;
        }
        
        //create previous and next pointers, completing the deep copy       
        copy = head;
        while(copy != null) {
            nodes.get(copy).next = nodes.get(copy.next);
            nodes.get(copy).random = nodes.get(copy.random);
            copy = copy.next;
        }        
        
        return nodes.get(head);
    }

    @ToString
    public class Node {
        int val;
        Node next;
        Node random;
        
        public Node(int val) { 
            this.val = val;
        }
        
        public Node(int val, Node next, Node random) { 
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }
}
