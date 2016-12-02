public class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        int max_prod = 0;
        
        if(n==0 || n==1)
            return max_prod;
        
        int bitRep[] = new int[n];
    
        for(int i=0;i<n;i++)
            bitRep[i] = bitRep(words[i]);
        
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if( (bitRep[i] & bitRep[j]) == 0)
                    max_prod = Math.max(max_prod, words[i].length()*words[j].length());
            }
        }    
        
        return max_prod;
    }
    
    public int bitRep(String word){
        int bitrep = 0;
        for(int i=0;i<word.length();i++){
            int mask = 1 << ((int)word.charAt(i)-97);
            bitrep = bitrep | mask; 
        }
        return bitrep;
    } 
}