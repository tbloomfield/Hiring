package leetcode;

import java.util.ArrayList;
import java.util.List;

import leetcode.dto.TreeNode;

/**
 * 102. Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * 
 * Given the root of a binary tree, return the level order traversal of its
 * nodes' values. (i.e., from left to right, level by level).
 * 
 * 
 * 
 */
public class Tree_BinaryTreeLevelOrderTraversal {
    
    public static void main(String[] args) { 
        Tree_BinaryTreeLevelOrderTraversal test = new Tree_BinaryTreeLevelOrderTraversal();
        test.levelOrderTraversal(new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7))));
    }
    
    public void levelOrderTraversal(TreeNode root) {
       List/*rows*/<List<Integer/*node values*/>> rows = new ArrayList<>();
       preOrderTraverse(root, rows, 0);      
    }
    
    private void preOrderTraverse(TreeNode node, List<List<Integer>> rows, int index) {
        if(node == null) { 
            return;
        }
        
        //add current value:
        List<Integer> currentRow = null;
        if(rows.size() > index) {
            //existing row - add this value
            currentRow = rows.get(index);
            currentRow.add(node.val);
        } else {
            //new row - initialize with this value.
            currentRow = new ArrayList<>();
            currentRow.add(node.val);
            rows.add(currentRow);
        }       
        
        preOrderTraverse(node.left, rows, index+1);
        preOrderTraverse(node.right, rows, index+1);
    }
}
