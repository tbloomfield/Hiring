package leetcode.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Node<T> {
    @ToString.Include @EqualsAndHashCode.Include public T val;
    public Node left;
    public Node right;
    public Node parent;
    
    public Node(T val) { 
        this.val = val;
    }
    
    public Node(T val, Node left, Node right) { 
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
