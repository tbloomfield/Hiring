package leetcode;

import leetcode.dto.TreeNode;

/**
 * 236. Lowest Common Ancestor of a Binary Tree (without parent node)
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * 
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes
 * in the tree.
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
 * Answer 1: 
 * Using recursion, we will do a DFS through all nodes; when we encounter either "p" or "q"
 * nodes, return true to the caller.  The first node which has both a "true" returned for one
 * of more DFS searches is the LCA.
 * 
 * Complexity: O(n) in the worst case we'll traverse through all nodes. 
 * Space: We're using recursion O(p) where p is the depth of the tree being searched
 */
public class Tree_LowestCommonAncestor_I {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //do a DFS returning true when node p OR node q is found.  When we've recursed and have 
        //2 true values, this is the common node.
        
        return dfs(root, p, q);
    }
    
    public TreeNode dfs(TreeNode currentNode, TreeNode p, TreeNode q) {
        if(currentNode == null) { 
            return currentNode;
        }
        if(currentNode == p || currentNode == q) { 
            return currentNode;
        }
        
        TreeNode foundNodeOne = dfs(currentNode.left, p, q);
        TreeNode foundNodeTwo = dfs(currentNode.right, p, q);
        
        //this is the lowest common ancestor
        if(foundNodeOne != null && foundNodeTwo != null) { 
            return currentNode;
        } else { 
            
            //if we've found at least one non-null node, return it.
            if(foundNodeOne != null) { 
                return foundNodeOne;
            } else if(foundNodeTwo != null) { 
                return foundNodeTwo;
            }
            return null;
        }
    }
        

}
