package leetcode;

import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

/**
 * 146. Implement a LRU cache
 * 
 * https://leetcode.com/problems/lru-cache/description/
 * 
 * The functions get and put must each run in O(1) average time complexity.
 * 
 * ANSWER:
 * A LRU cache consists of 2 data structures:
 * A simple Hashmap to store key/values
 * Values in the hashmap are doubly linked list nodes where the tail is the most recent accessed
 * and the head is the least recently accessed.
 * 
 * 
 * We could use a Java LinkedList type, however, insertion and access
 * runs in O(n) time.  getLast and getFirst run in O(1) time but removal runs in O(n) time.
 * 
 * Runtime: O(1) since we're using a map to lookup nodes and values.
 */
public class LinkedList_LRUCache { 
    
    public static void main(String[] args) { 
        LinkedList_LRUCache cache = new LinkedList_LRUCache(2);
        cache.put(1, 0);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.put(4, 4);
        System.err.println(cache.get(1));
        System.err.println(cache.get(3));
        System.err.println(cache.get(4));
    }
    
    @ToString
    class Node {
        int key;
        int val;
        Node next;
        Node previous;
    }

    Map<Integer, Node> cache = new HashMap<>();
    Node head;
    Node tail;    
    int capacity;

    public LinkedList_LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
      if(cache.containsKey(key)) {
          Node n = cache.get(key);
          removeNode(n);
          addNode(n);
          return n.val;
      } else { 
          return -1;
      }
    }
    

    public void put(int key, int value) {
        Node node = null;
        if(cache.containsKey(key)) {
          //updates the LRU ordering of this node
          node = cache.get(key);
          removeNode(node);
        } else {
          node = new Node();
          node.key = key;
        }
        node.val = value;
        
        addNode(node);
        cache.put(key, node);
        enforceCacheSize();        
    }
   
   private void removeNode(Node n) {
       //remove the node N from its current position
       if(n == head) { 
           head = head.next;
       } else if (n == tail) { 
           tail = tail.previous;
       }  else { 
           //update the double linked list for the previous and next nodes 
           //a->b->c  becomes a->c
           n.previous.next = n.next;
           n.next.previous = n.previous;
       }
   }
   
   private void addNode(Node n) { 
       if(head == null) { 
           head = n;
           tail = n;
       }  else { 
         //add the node N to the tail
         tail.next = n;     //tail entry next points to this node
         n.previous = tail; //this node points to previous tail entry
         n.next = null;     //the last node has nothing after it
         tail = n;          //update tail pointer to this node
       }
   }
   
   private void enforceCacheSize() { 
     if(cache.size() > capacity) {
       //get the head of our node
       int valToRemove = head.key;
       Node toRemove = cache.get(valToRemove);
       removeNode(toRemove);
       cache.remove(valToRemove);
     }
   }
}
