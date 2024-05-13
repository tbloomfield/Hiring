package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import leetcode.dto.TreeNode;

/**
 * 199. Binary Tree Right Side View
 * https://leetcode.com/problems/binary-tree-right-side-view/description/
 * 
 * Given the root of a binary tree, imagine yourself standing on the right side
 * of it, return the values of the nodes you can see ordered from top to bottom.
 * 
 * Answer:
 * 
 * Using a pre-order traversal iterates top->bottom, left to right.  Using DFS, track the 
 * row index, where zero is the head and end row = height of highest node.  Insert the 
 * most recent  
 * 
 * 
 */
public class Tree_BinaryTreeRightSideView {    
    public static void main(String[] args) { 
        Tree_BinaryTreeRightSideView test = new Tree_BinaryTreeRightSideView();
        var results = test.rightSideView(new TreeNode(1, new TreeNode(2, null, new TreeNode(5)), new TreeNode(3, null, new TreeNode(4))));
        System.err.println(results);
    }
    
    public List<Integer> rightSideView(TreeNode root) {
        //index = the row we're on - using a pre-order traversal, the rightmost node value
        //of this column will be equal to the value.
        List/*rows*/<Integer/*node values*/> rows = new ArrayList<>();
        preOrderTraverse(root, rows, 0);
        return rows;
    }

    private void preOrderTraverse(TreeNode node, List<Integer> rows, int index) {
        if(node == null) { 
            return;
        }
        
        //add current value:
        if(index >= rows.size()) {
            //new row:
            rows.add(node.val);
        } else {
            //existing row, update the value:
            rows.set(index, node.val);
        }       
        
        preOrderTraverse(node.left, rows, index+1);
        preOrderTraverse(node.right, rows, index+1);
    }
}
