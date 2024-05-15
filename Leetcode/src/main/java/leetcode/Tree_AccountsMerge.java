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
 * an account, attempt to use union find
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
        test.accountsMerge(testData);
    }

    //map of email address to group index in "accountToGroup"    
    private Map<String, Integer/*group index*/> emailToAccountIndex;
   
    //group index -> group value
    //the group value may be modified without changing the map of email->group index
    //this is the "root id" of the parent.
    private List<Integer> accountToGroup;
    
    //map of group value to email addresses; this is the "unioned" view
    //of email addresses.
    private Map<Integer/*group value*/, List<String> /*emailAddress*/> accountToEmailIndex; 

    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
        //insert and find groupings for the input accounts:
        for(int i = 0; i < accounts.size(); i ++) {
            List<String> account = accounts.get(i);
             
            String name = account.get(0);
            
            //add this entry to itself
            int accountId = i;
            accountToGroup.add(i, i); //initially point this group to itself
            
            //loop through and group all email addresses
            for(int j = i+1; j < account.size(); j++) {
                String email = account.get(j);
                
                //look for the existence of this email address in another account
                int existingGroup = emailToAccountIndex.getOrDefault(email, accountId);
                
                //if this email address was found in another group,
                //update the pointer of this group to the containing group
                if(existingGroup != accountId) {
                    accountId = unionBySize(i, existingGroup);                    
                }
                
                //add this email address to index
                emailToAccountIndex.put(email, accountId); 
            } //end email address lookup
        }
    }
    
    /**
     * looks at the size of group 1 and group 2, joining the two based on
     * whichever group has less elements.
     * 
     * @param group1
     * @param group2
     * @return
     */
    private int unionBySize(int group1, int group2) {        
        if(accountToEmailIndex.get(group1).size() < accountToEmailIndex.get(group2).size()) {
            
            //decouple all elements from group 1 and insert into group 2
            List<String> emails = accountToEmailIndex.get(group2);
            emails.addAll(accountToEmailIndex.get(group1));
            
            //group 1 data is no longer needed since values were moved
            accountToEmailIndex.remove(group1);
            
            //make sure that group 1 index points to group 2 so this index
            //is no longer referenced
            accountToGroup.set(group1, group2);
            
            return group1;
        } else { 
            //decouple all elements from group 2 and insert into group 1
            return group2;
        }
    }
}
