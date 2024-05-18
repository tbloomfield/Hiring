package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 721. Accounts Merge (email accounts)
 * https://leetcode.com/problems/accounts-merge/description/
 * 
 * Given a list of accounts where each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name, and the rest of the
 * elements are emails representing emails of the account.
 * 
 * Now, we would like to merge these accounts. Two accounts definitely belong to
 * the same person if there is some common email to both accounts. Note that
 * even if two accounts have the same name, they may belong to different people
 * as people could have the same name. A person can have any number of accounts
 * initially, but all of their accounts definitely have the same name.
 * 
 * Answer:   
 * Since we're merging (n) elements, and they're 'joinable' based on a value within
 * an account, attempt to use union find.
 * 
 * Complexity: O(n) looping through account details)  + O(nlogn) to sort emails + union find is O(n)
 * at worst case every email is inserted in order that it's a child of the next entry.
 *             Overall O(nlogn)
 * 
 * 
 */
public class Tree_AccountsMerge {
    
    public static void main(String[] args) { 
        Tree_AccountsMerge test = new Tree_AccountsMerge();        
        var testData = 
          List.of(
                  Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"),
                  Arrays.asList("John","johnsmith@mail.com","john00@mail.com"),
                  Arrays.asList("Mary","mary@mail.com"),
                  Arrays.asList("John","johnnybravo@mail.com")                
                 );
        var results = test.accountsMerge(testData);
        System.err.println(results);
    }

    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
        //initialize our union find
        //in the worst case scenario we'll have accounts.size() unique accounts
        //if there is no overlap.
        UnionFind uf = new UnionFind(accounts.size());        

        //map of email address to group index in "accountToGroup"    
        Map<String, Integer/*group index*/> emailToAccountIndex = new HashMap<>();
        
        //Step 1: insert and find "index" groupings for the input emails
        for(int i = 0; i < accounts.size(); i ++) {
            List<String> accountDetails = accounts.get(i);             
            //loop through and group all email addresses
            for(int j = 1; j < accountDetails.size(); j++) {
                String email = accountDetails.get(j);
                
                //if this email was already found in another account, attempt
                //to union the two accounts
                if(emailToAccountIndex.containsKey(email)) { 
                    //by default "i" is the value of this account
                    uf.union(emailToAccountIndex.get(email), i);
                } else { 
                    //add this email and its account
                    emailToAccountIndex.put(email, i);
                } 
            } //end email address lookup
        }
        
        //step 2: pivot: previously we mapped emails to grouped indexes, relating them.  Now loop through
        //indexes, adding emails to a list for each index, which is how we want to group.
        Map<Integer/*id*/, List<String>/*emails*/> idToEmail  = new HashMap<>();
        for(String email : emailToAccountIndex.keySet()) { 
            //get the root for this email
            int parent = uf.root(emailToAccountIndex.get(email));
            List<String> emails = idToEmail.getOrDefault(parent, new ArrayList<>());
            emails.add(email);
            idToEmail.put(parent, emails);
        }
        
        //step 3: loop through all groupings, sorting the email addresses (per instructions)
        //and returning the results
        List<List<String>> results = new ArrayList<>();
        for(var entry: idToEmail.entrySet()) {
            List<String> emails = entry.getValue();
            Collections.sort(emails); //n logn
            //re-add the account name - in the original input, name on the account is 
            //coded into position 0
            emails.add(0, accounts.get(entry.getKey()).get(0));
            results.add(emails);
        }
        
        return results;
    }
    
    
    class UnionFind {
        //index of the root node for this element
        int[] parent;
        //size of each index ; used when determining
        //the root in union() operations
        int[] size;
        
        public UnionFind(int num) {
            parent = new int[num];
            size = new int[num];
            
            //initialize parents to point to themselves 
            for(int i =  0; i < num; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        //attempt to join 2 parents if possible.
        public void union(int a, int  b) {
            int rootA = root(a);
            int rootB = root(b);
            
            if (rootA == rootB) {
                return;
            }
            
            //merge based on size
            //make B a child of A
            if (size[rootA] > size[rootB]) {
                parent[rootB] = rootA;
                size[rootA] += size[rootB];
            } else {
            //make A index a child of B 
                parent[rootA] = rootB;
                size[rootB] += size[rootA];
            }
        }
        
        //get the root node for each element. Recursive since
        //we can traverse through multiple nodes to get to the root.   
        //if compression is on, this would be O(1) time, otherwise this is O(n)
        public int root(int a) {
            
            //found root
            if (parent[a] == a) {
                return a;
            }
            
            //points to another parent node as a possible root
            parent[a] = root(parent[a]);
            return parent[a];
        }
        
        //not needed for this solution, but including it here anyway since it demonstrates
        //path compression
        public int find(int a) {

            // Find the root of the component/set
            int root = a;
            while (root != parent[root]) root = parent[root];

            // Compress the path leading back to the root.
            // Doing this operation is called "path compression"
            // and is what gives us amortized time complexity.
            while (a != root) {
              int next = parent[a];
              parent[a] = root;
              a = next;
            }

            return root;
          }
    }
}
