public class Solution {
    public String longestPalindrome(String s) {
        /*Logic:
        To avoid overlapping palindrome checks, we use a boolean table which stores whether for a particular index k, substring (j,k) is a palindrome or not.
        eg: for string "dabcba", the table is as below
        
        d a b c b a
        1 0 0 0 0 0 
        0 1 0 0 0 1 <- longest palindrome substring is "abcba"
        0 0 1 0 1 0 
        0 0 0 1 0 0 
        0 0 0 0 1 0 
        0 0 0 0 0 1 
        */
        
        if(s.length()==1)
            return s;
            
        int len=s.length(), max_len=0;
        String result="";
        
        boolean dp[][] = new boolean[len][len];
        
        for(int i=0; i<len; i++){
            for(int j=0; j<len-i; j++){
                int k = i+j;
                
                //for abcba, only a is checked with a, and validity of bcb being a palindrome is fetched from the boolean table
                //k-j<=2: once end character match, strings of size 1,2 and 3 are always a palindrome, no need to lookup in the table
                //eg: "a", "aa", "aba"
                if(s.charAt(j)==s.charAt(k) && (k-j<=2 || dp[j+1][k-1]) ){
                    dp[j][k] = true;
                    
                    if(k-j+1>max_len){
                        max_len = k-j+1;
                        result = s.substring(j, k+1);
                    }
                }
            }
        }
        
        return result;
    }
}