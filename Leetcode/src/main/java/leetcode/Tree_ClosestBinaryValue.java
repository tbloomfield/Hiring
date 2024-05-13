package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

import leetcode.dto.TreeNode;

/**
 * 270. Closest Binary Search Tree Value
 * https://leetcode.com/problems/closest-binary-search-tree-value/description/
 * 
 * Given the root of a binary search tree and a target value, return the value
 * in the BST that is closest to the target. If there are multiple answers,
 * print the smallest.
 * 
 * Input: root = [4,2,5,1,3], target = 3.714286 Output: 4
 * 
 */
public class Tree_ClosestBinaryValue {
    public static void main(String[] args) { 
        Tree_ClosestBinaryValue test = new Tree_ClosestBinaryValue();
        var result = test.closestValue(new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(5)), 3.714286d);
        System.err.println(result);
    }
    
    public int closestValue(TreeNode root, double target) {
        
        //traverse our binary search tree looking for the target value        
        Deque<TreeNode> nodes = new ArrayDeque<>();
        nodes.offer(root);
        
        boolean found = false;
        int closest = Integer.MAX_VALUE;
        
        while(!nodes.isEmpty() && !found) {
            TreeNode node = nodes.pop();
            
            //compare this node difference to the last captured node difference
            //store the smaller value if the difference is the same
            if(Math.abs(node.val - target) == Math.abs(closest-target)) {
                closest = Math.min(closest, node.val);
            }
            
            //if it's lower, update the closest node
            else if(Math.abs(node.val - target) < Math.abs(closest-target)) { 
                closest = node.val;
            }
            
            //attempt to find a better value:
            if(node.val < target && node.right != null) { 
                //go right
                nodes.offerLast(node.right);
            } else if(node.val > target && node.left != null) { 
                //go left
                nodes.offerLast(node.left);
            }
        }        
        
        return closest;
    }
}
