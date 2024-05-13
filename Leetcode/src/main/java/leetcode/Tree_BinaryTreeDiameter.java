package leetcode;

import leetcode.dto.TreeNode;

/**
 * 543. Diameter of a Binary Tree
 * https://leetcode.com/problems/diameter-of-binary-tree/description/
 * 
 * Given the root of a binary tree, return the length of the diameter of the
 * tree.
 * 
 * The diameter of a binary tree is the length of the longest path between any
 * two nodes in a tree. This path may or may not pass through the root.
 * 
 * The length of a path between two nodes is represented by the number of edges
 * between them.
 * 
 * Answer: The diameter of a tree can be calculated as: Max( Height(left node) +
 * height(right node) )
 * 
 * Complexity: O(n) where n is the number of nodes in the tree. We visit each
 * node and perform constant time operations 
 * 
 * Storage: O(H), where H is the height of the tree: O(logn) for balanced trees and 
 * O(N) in the case of an imbalanced tree.
 */
public class Tree_BinaryTreeDiameter {
    public static void main(String[] args) { 
        Tree_BinaryTreeDiameter test = new Tree_BinaryTreeDiameter();
        int result = test.diameterOfBinaryTree(new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3)));
        System.err.println(result);        
    }
    
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {        
        totalLeafHeight(root);
        return diameter;
    }
    
    private int totalLeafHeight(TreeNode root) { 
        if(root == null) {
            return 0; //a null value doesn't count as a row
        }
        int rightHeight = totalLeafHeight(root.right);
        int leftHeight = totalLeafHeight(root.left);
        diameter = Math.max(diameter, leftHeight + rightHeight);
        return Math.max(rightHeight, leftHeight) + 1;        
    }
}
