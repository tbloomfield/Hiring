package leetcode.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Node {
    @ToString.Include @EqualsAndHashCode.Include public int val;
    public Node left;
    public Node right;
    public Node parent;
    
    public Node(int val) { 
        this.val = val;
    }
    
    public Node(int val, Node left, Node right) { 
        this.val = val;
        this.left = left;
        this.right = right;
        
        if(left != null) { 
            left.parent = this;    
        }        
        if(right != null) { 
            right.parent = this;
        }
    }
    
    
}
