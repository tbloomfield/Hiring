package leetcode;
import java.util.HashMap;
import java.util.Map;

/**
 * 691.Stickers to Spell Word
 * https://leetcode.com/problems/stickers-to-spell-word/description/
 * 
 * We are given n different types of stickers. Each sticker has a lowercase
 * English word on it.
 * 
 * You would like to spell out the given string target by cutting individual
 * letters from your collection of stickers and rearranging them. You can use
 * each sticker more than once if you want, and you have infinite quantities of
 * each sticker.
 * 
 * Return the minimum number of stickers that you need to spell out target. If
 * the task is impossible, return -1.
 * 
 * Answer:
 * 
 * Complexity: O(m*2^N) where m = number of stickers and N is size of the target string.  2^n since
 *   we calculate all subsequences.
 * Storage: O(2^n) Taken by the cache, is the max of all subsequences of the target string
 */
public class Stickers {
    public static void main(String[] args) { 
        Stickers test = new Stickers();
        var results = test.minStickers( new String[] {"with","example","science"}, "thehat");
        System.err.println(results);
    }
    
    Map<String/*sticker*/, Map> stickerCharacterCounts = new HashMap<>();
    Map<String/*target subsequence*/, Integer/*min # of stickers  to make this subsequence*/> cache 
    = new HashMap<>();
        
    public int minStickers(String[] stickers, String target) {              
        //convert each sticker to a hashmap of character counts
        for(int i = 0; i < stickers.length; i++) {
            stickerCharacterCounts.put(stickers[i], createCharacterCountMap(stickers[i]));
        }
        int minimumStickers = dfs(target);        
        return minimumStickers == Integer.MAX_VALUE - 1 ?  -1 : minimumStickers ;
    }
    
    /**
     * Creates a character map from target string.
     * @param sticker
     * @return
     */
    private Map<Character, Integer> createCharacterCountMap(String sticker) { 
        Map<Character, Integer> characterCounts = new HashMap<>();            
        for(char c: sticker.toCharArray()) { 
            int existingCount = characterCounts.getOrDefault(c,0);
            characterCounts.put(c, existingCount+1);
        }        
        return characterCounts;
    }
    
    /**
     * Calculates a string remaining after applying a character countmap
     * @param characterMapForSticker
     * @param targetString
     * @return
     */
    private String returnReminaingCharacters(Map<Character,Integer> characterMapForSticker, String targetString) { 
        StringBuilder charactersNotFoundInSticker = new StringBuilder();
        for(char c: targetString.toCharArray()) { 
            if(characterMapForSticker.containsKey(c)) { 
                int existingCount = characterMapForSticker.get(c);
                characterMapForSticker.put(c, existingCount-1);
            } else { 
                //track characters not found within this sticker.
                charactersNotFoundInSticker.append(c);
            }
        }
        return charactersNotFoundInSticker.toString();
    }
    
    private int dfs(String targetString) { 
        //if the target string was already computed, return it
        if(cache.containsKey(targetString)) { 
            return cache.get(targetString);
        } else if(targetString.isEmpty()) {  //no more letters are needed. 
            return 1;
        }
        
        int numberStickers = Integer.MAX_VALUE-1;
        
        //go through every character in the targetString, looking into the sticker character 
        //count to see if it has it
        for(var entry : stickerCharacterCounts.entrySet()) {
            //entry.getKey() //the sticker text
            Map<Character, Integer> stickerCharacterCounts = entry.getValue();
            
            //skip this sticker if it doesn't have at least the first letter in the sticker characters
            //remaining.
            if(stickerCharacterCounts.getOrDefault(targetString.charAt(0), -1) <= 0) {
                continue;
            }
            
            //a substring of characters remaining; create a clone of the hashmap so that modifications
            //don't propogate to the next call.
            String charactersNotFoundInSticker = returnReminaingCharacters(new HashMap(stickerCharacterCounts), targetString);
        
            //this sticker has no usable letters in it
            if(charactersNotFoundInSticker.equals(targetString)) {
                continue;                  
            }

            //if this sticker didn't contain all of the characters needed, recursively search
            //for additional stickers to satisfy.
            numberStickers = Math.min(numberStickers, 1 + dfs(charactersNotFoundInSticker.toString()));            
        }

        //cache results for this target
        cache.put(targetString, numberStickers);
        
        return numberStickers;
    }

}
