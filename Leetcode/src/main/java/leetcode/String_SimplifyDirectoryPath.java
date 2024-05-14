package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 71. Simplify path
 * https://leetcode.com/problems/simplify-path/description/
 * 
 * Given an absolute path for a Unix-style file system, which begins with a slash '/', transform this path into its simplified canonical path.
 * We need to consider the following cases:
 * ../
 * ... (keep)
 * removing trailing slashes 

 */
public class String_SimplifyDirectoryPath {
    
    public static void main(String [] args) {
        String_SimplifyDirectoryPath test = new String_SimplifyDirectoryPath();
        System.err.println("result = " + test.simplifyPath("/home/user/Documents/../Pictures/"));
    }
    
    //easiest method here is split on directory character then push the results
    //onto a queue
    public String simplifyPath(String path) {
        Deque<String> pathQueue = new ArrayDeque<>();
        String[] directories = path.split("/");
        for(String directory : directories) { 
            if(directory.equals(".") || directory.isEmpty()) {
                //no-op this entry
            } else if(directory.equals("..")) {
                // if this is a "/../" input, there's nothing to remove, but we shouldn't
                // add anything either.
                if(!pathQueue.isEmpty()) { 
                    pathQueue.pollLast();
                }
            } else { 
                pathQueue.offerLast(directory);                
            }
        }
        
        //now loop through the stack creating the canonical path
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        while(!pathQueue.isEmpty()) {
            sb.append(pathQueue.pollFirst());
            if(!pathQueue.isEmpty()) { 
                sb.append("/");
            }
        }
        
        return sb.toString();   
    }
}
