public class Solution {
    public boolean isSubsequence(String s, String t) {
        /*LOGIC
        2 pointers, 1 for s and 1 for t. Advance s only if there is a match in t.
        
        Follow up:  For the string t, save the first occurence of each letter in an array of 26. Traverse every incoming string s, for each character, find the occurence in the array of 26. With every letter, the occurrence should increase.
        eg: for string t = "mahbgdc" and s="bgd", b occurs at 3, g occurs at 4 and d occurs at 5, hence present.
        
        */
        
        if(s.length()==0)
            return true;
            
        int s_pos = 0, t_pos = 0;
        while(t_pos!=t.length()){
            if(s.charAt(s_pos)==t.charAt(t_pos))
                s_pos++;
            
            t_pos++;
            
            if(s_pos==s.length())
                return true;
        }
        
        if(s_pos==s.length())
            return true;
        else
            return false;
    }
}