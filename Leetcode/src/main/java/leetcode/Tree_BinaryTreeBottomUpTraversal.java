package leetcode;

import java.util.ArrayList;
import java.util.List;

import leetcode.dto.TreeNode;

/**
 * 107. Binary Tree Level Order (Bottom Up) Traversal II
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
 * 
 * Given the root of a binary tree, return the bottom-up level order traversal
 * of its nodes' values. (i.e., from left to right, level by level from leaf to
 * root).
 * 
 * Answer:
 * 
 * Doing a post order traversal will enable us to add values from the "bottom-up".
 * We'll add nodes to each row using a "reverse index":
 *   1. determine the max height of the tree
 *   2. initialize the list of rows equal to the height of the tree.
 *   3. as we increase in depth during our DFS, subtract 1 from the max tree height.
 *   4. at the bottom-most leaf nodes, the row index will be zero.  The height will be max tree height.
 * 
 * Complexity: O(n) as we traverse through every element in the tree.
 * Storage: O(n) we add 1 value to a result set for every node in the tree.
 */
public class Tree_BinaryTreeBottomUpTraversal {
    
    public static void main(String[] args) { 
        Tree_BinaryTreeBottomUpTraversal test = new Tree_BinaryTreeBottomUpTraversal();
        var results = test.levelOrderBottom(new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7))));
        System.err.println(results);
    }
    
    
    public List/*row*/<List/*column*/<Integer>> levelOrderBottom(TreeNode root) {
        //approach: traverse DFS to the bottom of the tree, then return nodes
        //to be added to a list of (row, column) on the way back to the root.        
        //get the tree height
        int treeHeight = treeHeight(root);  //zero-indexed height
        List<List<Integer>> results = new ArrayList<>(treeHeight);
        for(int i = 0; i < treeHeight;i++) { 
            results.add(new ArrayList<>());
        }
        
        reversePostOrderIterate(root, results, treeHeight-1);
        return results;
    }
    
    //recursively count height of the tree
    private int treeHeight(TreeNode node) { 
        if(node == null) { 
            return 0;
        }
        int leftHeight = treeHeight(node.left);
        int rightHeight= treeHeight(node.right);
        return Math.max(leftHeight, rightHeight)+1;
    }
    
    //left, right, root
    private void reversePostOrderIterate(TreeNode root, List/*row*/<List/*column*/<Integer>> results, int rowIndex){        
        //null 
        if(root == null) { 
            return;
        }
        if(root.left != null) { 
            reversePostOrderIterate(root.left, results, rowIndex-1);
        }
        if(root.right != null) { 
            reversePostOrderIterate(root.right, results, rowIndex-1);
        }             
        results.get(rowIndex).add(root.val);
    }
}
