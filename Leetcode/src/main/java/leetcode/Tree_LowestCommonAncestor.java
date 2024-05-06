package leetcode;

import java.util.HashSet;
import java.util.Set;

import leetcode.dto.Node;

/**
 * 1650. Lowest Common Ancestor of a Binary Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/description/
 * 
 * Given two nodes of a binary tree p and q, return their lowest common ancestor
 * (LCA).
 * 
 * Each node will have a reference to its parent node.
 * 
 * Definition of lowest common ancestor:
 * 
 * The lowest common ancestor of two nodes p and q in a tree T is the lowest
 * node that has both p and q as descendants (where we allow a node to be a
 * descendant of itself).
 * 
 * Put another way, what is the closest parent node which has both nodes "p" and
 * "q" in it?
 * 
 * Solution 1: Traverse through all the parents of node "p", starting with p
 * itself storing the parents into a set Traverse through all the parents of
 * node "q", starting with q itself - if the parent of "q" is found in the set
 * of parents for "p", it's the lowest ancestor.
 * 
 * Complexity: O(depth) where depth is the number of parent nodes. 
 * Space: O(p) where p  is the number of parent nodes
 * 
 * Solution 2: Given the heads of two singly linked-lists headA and headB,
 * return the node at which the two lists intersect. If the two linked lists
 * have no intersection at all, return null.
 * 
 * Complexity: O(depth) where d is the number of parent nodes.
 * Space: O(1) requires no additional data structures.
 */
public class Tree_LowestCommonAncestor {
    
    public static void main(String[] args) { 
        Tree_LowestCommonAncestor test = new Tree_LowestCommonAncestor();
        
        //creating a new node structure will set the parent references:
        Node node4 = new Node(4);
        Node node5 = new Node(5, new Node(6), new Node(2, new Node(7), node4));        
        //creating the main tree will set parent references for node 5.
        Node root = new Node(3, 
                   node5,
                   new Node(1, new Node(0), new Node(8))
                );        
        System.err.println(test.lowestCommonAncestor(node5, node4));
        System.err.println(test.lowestCommonAncestor_NoStorage(node5, node4));
    }
    
    
    public Node lowestCommonAncestor(Node p, Node q) {
        //store a list of visited parent nodes, starting with "p" for "p"
        Set<Node> visited = new HashSet<>();
        while (p != null) {
            visited.add(p);
            p = p.parent;
        }

        //loop through the parents of q, starting with q
        //the first parent node that is also found from P is the 
        //closest matching parent.
        while (q != null) {
            if (visited.contains(q)) {
                return q;
            } else {
                q = q.parent;
            }
        }
        return null;
    }
    
    public Node lowestCommonAncestor_NoStorage(Node p, Node q) { 
        var pParent = p;
        var qParent = q;
        
        while (pParent != qParent) {
          pParent = pParent.parent != null ? pParent.parent : q;
          qParent = qParent.parent != null ? qParent.parent : p;
        }
        return pParent;
    }
}
