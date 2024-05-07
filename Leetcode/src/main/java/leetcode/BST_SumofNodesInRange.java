package leetcode;

/**
 * 938. Range Sum of BST
 * https://leetcode.com/problems/range-sum-of-bst/description/
 * 
 * Given the root node of a binary search tree and two integers low and high, return the 
 * sum of values of all nodes with a value in the inclusive range [low, high].
 * 
 * Complexity: O(n), loop through all nodes.
 * Storage: O(h) where H is the height of the tree since we're using recursion
 */
public class BST_SumofNodesInRange {
    public int rangeSumBST(TreeNode root, int low, int high) {        
        if(root == null) { 
            return 0;
        }
        
        int val = 0;        
        if(root.val <= high && root.val >= low) {
            val += root.val;
        }
        int left = rangeSumBST(root.left, low,  high);
        int right = rangeSumBST(root.right, low, high);
        return val + left + right;
     }
    
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
