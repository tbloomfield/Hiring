package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

import leetcode.dto.TreeNode;

public class Tree_AllNodesDistanceKFromTarget {
    
    TreeNode src;
    TreeNode target;
    HashMap<TreeNode, TreeNode> backward;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        this.backward = new HashMap<>();
        this.target = target;
        dfs(root);

        int distance = 0;
        ArrayList<Integer> results = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        HashSet<TreeNode> visited = new HashSet<>();
        queue.add(src);
        visited.add(src);

        while (!queue.isEmpty()){
            int size = queue.size();
            if (distance > k)
                break;
            while (size-- > 0){
                TreeNode node = queue.remove();
                if (distance == k){
                    results.add(node.val);
                }

                if (node.left != null && !visited.contains(node.left)){
                    queue.add(node.left);
                    visited.add(node.left);
                }
                if (node.right != null && !visited.contains(node.right)){
                    queue.add(node.right);
                    visited.add(node.right);
                }
                if (backward.containsKey(node) && !visited.contains(backward.get(node))){
                    queue.add(backward.get(node));
                    visited.add(backward.get(node));
                }
            }
            distance++;
        }
        return results;
    }

    //build parent node list allowing us to identify the previous nodes - necessary since the root
    //node will become the target node
    private void dfs(TreeNode root){
        if (root == null)
            return;
        if (root.val == target.val)
            src = root;
        backward.put(root.left, root);
        backward.put(root.right, root);
        dfs(root.left);
        dfs(root.right);
    }
}
