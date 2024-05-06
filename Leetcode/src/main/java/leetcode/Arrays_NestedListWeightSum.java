package leetcode;

import java.util.List;

import leetcode.dto.NestedInteger;

/**
 * 339. Nested List Weight Sum
 * https://leetcode.com/problems/nested-list-weight-sum/description/ You are
 * given a nested list of integers nestedList. Each element is either an integer
 * or a list whose elements may also be integers or other lists.
 * 
 * The depth of an integer is the number of lists that it is inside of. For
 * example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to
 * its depth.
 * 
 * Return the sum of each integer in nestedList multiplied by its depth.
 * 
 * Answer:
 * Use recursion (DFS) when we encounter a nested list.  The "depth" which we multiply nested list
 * values by is equivelant to the depth of the recursive call stack.  
 * 
 * We could also use BFS, which has the same storage and time complexity.
 *  
 * Complexity: O(n) where (n) is the number of items in the list and we know that there's one recursive call per nested iteration
 * Storage: O(n) where (n) is the possible number of recursive lists within the nested list
 * 
 * 
 */
public class Arrays_NestedListWeightSum {
    
    public int depthSum(List<NestedInteger> nestedList) {
        return calcTotal(nestedList, 1);
    }
    
    private int calcTotal(List<NestedInteger> current, int depth){ 
        int total = 0;

        for(int i = 0; i < current.size(); i++ ) { 
            NestedInteger next = current.get(i);
            if(next.isInteger()){ 
                total += next.getInteger() * depth;
            } 
            else {
                total += calcTotal(next.getList(), depth +1);
            } 
        }
        return total;
    }
}
