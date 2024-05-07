package leetcode;

import leetcode.dto.Node;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/description/
 * 
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in
 * place.
 * 
 * You can think of the left and right pointers as synonymous to the predecessor
 * and successor pointers in a doubly-linked list. For a circular doubly linked
 * list, the predecessor of the first element is the last element, and the
 * successor of the last element is the first element.
 * 
 * We want to do the transformation in place. After the transformation, the left
 * pointer of the tree node should point to its predecessor, and the right
 * pointer should point to its successor. You should return the pointer to the
 * smallest element of the linked list.
 * 
 * Answer:
 * Basically this is a question about how to traverse a BST, outputting elements in order which is represented
 * by a linked list.  Since we want to preserve ordering, we need to iterate over the list inorder (left, current, right) 
 * 
 * Complexity: O(n) each node is processed once.
 * Space: O(n) for an inbalanced tree and O(logN) for a perfectly balanced tree
 */
public class BST_ToSortedLinkedList {
    
    //a doubly linked list needs a head and a tail.
    Node head;
    Node tail;
    
    public Node treeToDoublyList(Node root) {
        //do in-order traversal to create the linkedList
        if(root == null){ 
            return null;
        }
        //recurse left
        if(root.left != null){ 
            treeToDoublyList(root.left);
        }

        //current value
        if(tail == null) {
            //first node, set it to head.
            head = root;
        } else { 
            //second node onward we'll be appending this node
            root.left = tail;
            //successor
            tail.right = root;
        }
        //current node is now the tail node
        tail = root;

        //traverse right
        if(root.right != null){ 
            treeToDoublyList(root.right);
        }
        //connect head and tail so it's a circular linked list.
        tail.right = head;
        head.left = tail;
        return head;
    }
}
