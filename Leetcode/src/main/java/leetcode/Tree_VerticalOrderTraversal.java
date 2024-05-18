package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import leetcode.dto.TreeNode;
import leetcode.dto.Pair;

/**
 * 987. Vertical Order Traversal of a Binary Tree
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/
 * 
 */
public class Tree_VerticalOrderTraversal {
    
    public static void main(String[] args) { 
        Tree_VerticalOrderTraversal test = new Tree_VerticalOrderTraversal();
        var results = 
                test.verticalTraversal(new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(6)), new TreeNode(3, new TreeNode(5), new TreeNode(7) )));
        System.err.println(results);
    }
    
    //keep start / end indexes
    int minColumn, maxColumn = 0;
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //sort elements within a column by their rows (lower = first)
        //PriorityQueue<Pair<Integer>> sortedRows = new PriorityQueue<>( (p1, p2) -> p1.leftSide - p2.leftSide);        
        Map<Integer/*column*/, List<Pair<Integer/*row->nodevalue*/>>> columnValues = new HashMap<>();
        
        //do a dfs, inserting elements into the map
        dfs(columnValues, root, 0, 0);
        
        //loop through resulting columns and sort by row (lower first)
        List<List<Integer>> results  = new ArrayList<>();
        
        for(int i = minColumn; i <= maxColumn; i++) {
            List<Pair<Integer/*row->nodevalue*/>> pairs = columnValues.get(i);
            
            //sort results from row.min to row.max(the left value in the pair)
            //O(p log p)
            Collections.sort(pairs, (p1, p2) -> {
                //sort ascending by row, high (zero) to low (deeper rows)
                int calc = p2.leftSide - p1.leftSide;
                
                //for ties, use node value ordering
                if(calc ==0) { 
                    //low to high
                    return p1.rightSide - p2.rightSide;
                }
                
                return calc;
            });
            
            //convert sorted collection to individual node values
            //O(p)
            List<Integer> nodeValues = new ArrayList(pairs.size());
            for(Pair<Integer> p : pairs) { 
                nodeValues.add(p.rightSide);
            }
            results.add(nodeValues);
        }
        
        return results;
    }
    
    //O(vertex + edge) = O(n) - linear
    private void dfs(Map<Integer/*column*/, List<Pair<Integer/*row->nodevalue*/>>> columnValues, TreeNode node, int column, int row) {
        
        if(node == null) { 
            return;
        }
        
        //add the current value
        List<Pair<Integer>> values = columnValues.getOrDefault(column, new ArrayList<Pair<Integer>>());
        values.add(new Pair(row, node.val));
        columnValues.put(column, values);
        
        //track range of columns
        minColumn = Math.min(minColumn, column);
        maxColumn = Math.max(maxColumn, column);
        
        if(node.left != null) { 
            dfs(columnValues, node.left, column-1, row-1);
        }
        if(node.right != null) { 
            dfs(columnValues, node.right, column+1, row-1);
        }
    }
}
