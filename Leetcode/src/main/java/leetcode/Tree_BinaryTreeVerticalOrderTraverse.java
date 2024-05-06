package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leetcode.dto.TreeNode;

/**
 * 314. Binary Tree Vertical Order Traversal.
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/description/
 * 
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 * 
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Answer: 
 * Do a BFS traversal, keeping a map of key = column and value = node value. Iterate on column could based on left or right traversal.
 * Complexity: O(N) for BFS traversal, O(1) for lookup of keys
 * Storage: O(N) since we end up storing node values in a hashmap.
 */
public class Tree_BinaryTreeVerticalOrderTraverse {
    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> results = new ArrayList<>();
        if(root == null) {
           return results;          

        }
        Map<Integer/*column*/,List<Integer>/*nodes*/> columnNodes = new HashMap<>();
        Deque<Pair> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new Pair(0, root));
        
        //track the min/max column we've seen - this way we have a predictable iteration
        //order when getting values out of the columnNodes map.
        int minCol = 0, maxCol = 0;
        
        while(!bfsQueue.isEmpty()) { 
            Pair tn = bfsQueue.poll();
            
            minCol = Math.min(minCol, tn.column);
            maxCol = Math.max(maxCol, tn.column);
            
            //add the value to results
            List<Integer> nodes = columnNodes.getOrDefault(tn.column, new ArrayList<>());            
            nodes.add(tn.node.val);
            columnNodes.put(tn.column, nodes);
            
            if(tn.node.left != null) { 
                bfsQueue.offerLast(new Pair(tn.column-1, tn.node.left));
            }
            if(tn.node.right != null) { 
                bfsQueue.offerLast(new Pair(tn.column+1, tn.node.right));
            }
        }
        
        //sort keys        
        for(int i = minCol; i <= maxCol; i ++) {  
            results.add(columnNodes.get(i));
        }
        return results;
    }
    
    class Pair {
        int column;
        TreeNode node;
        
        public Pair(int column, TreeNode node) { 
            this.column = column;
            this.node = node;
        }    
    }
    
    
}
