package leetcode;

import leetcode.dto.TreeNode;

/**
 * 129. Sum Root to Leaf Numbers
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
 * 
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * 
 * Each root-to-leaf path in the tree represents a number.
 * 
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so
 * that the answer will fit in a 32-bit integer.
 * 
 * A leaf node is a node with no children.
 * 
 * Answer:
 * Perform a preorder traversal, summing node values as we go.  For every level depth increased,
 * increase the previous total by 10 place.  IE  1 -> 2 -> 3 = 12 + 13
 * 
 * Complexity: O(n) as we traverse every node in the tree to create a sum
 * Storage: O(1) as we only return primitive int sums.
 */
public class Tree_SumRootToLeaf {
    
    public static void main(String[] args) { 
        Tree_SumRootToLeaf test = new Tree_SumRootToLeaf();
        //int sum = test.sumNumbers(new TreeNode(1, new TreeNode(2), new TreeNode(3)));
        int sum = test.sumNumbers(new TreeNode(4, new TreeNode(9, new TreeNode(5), new TreeNode(1)), new TreeNode(0)));
        System.err.println(sum);
    }

    //postorder traversal to p
    public int sumNumbers(TreeNode root) {
        int total = preOrderTraverse(root, 0, 1);
        return total;
    }
    
    private int/*total*/ preOrderTraverse(TreeNode root, int rollingSum, int depth) {
        if(root == null) { 
            return 0;
        }
        
        //every depth we multiply by 10 to keep digits in their correct place       
        rollingSum *= 10;
        rollingSum += root.val;
        
        int leftSum = 0;
        int rightSum = 0;        
        if(root.left != null) { 
            leftSum  = preOrderTraverse(root.left, rollingSum, depth+1);            
        }
        if(root.right != null) { 
            rightSum = preOrderTraverse(root.right, rollingSum, depth+1);
        }
        //only update our sum if a leaf node has a non-zero value
        if(leftSum + rightSum != 0) { 
            rollingSum = leftSum+rightSum;
        }
        
        return rollingSum;
    }
}
