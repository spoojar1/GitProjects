public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null)
            return 0;
        if(s.length()==0 || s.length()==1)
            return s.length();
        
        int len = 1;
        StringBuilder result = new StringBuilder(String.valueOf(s.charAt(0)));
        
        for(int i=1;i<s.length();i++){
            int index = result.indexOf(String.valueOf(s.charAt(i)));
            if(index != -1)
                result.delete(0, index+1);
            result.append(s.charAt(i));
            len = Math.max(len, result.length());
        }
        
        return len;
    }
}