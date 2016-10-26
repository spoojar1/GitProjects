public class Solution {
    List<String> result = new ArrayList<String>();
    
    public List<String> wordBreak(String s, Set<String> wordDict) {
        String words[] = new String[s.length()+1];
        
        //if(s.length()==1)
         //   return result;
            
        Arrays.fill(words,"");
        words[0]="dummy";
        
        for(int i=0;i<s.length();i++){
            if(words[i].equals(""))
                continue;
            
            for(String str: wordDict){
                int end = i + str.length();
                
                if(end > s.length())
                    continue;
                    
                if(s.substring(i,end).equals(str))
                    words[end]+=" "+str;
            }
        }
        
        //not possible to create strings using the given dict
        if(words[s.length()].equals(""))
            return result;
            
        helpWordBreak(words,"",s.length());
        return result;
    }
    
    public void helpWordBreak(String words[], String s, int k){
        if(k==0){
            result.add(s.trim());
            return;
        }
        String dfs[]=words[k].trim().split(" ");
        for(String str : dfs){
            helpWordBreak(words, str+" "+s, k-str.length());
        }
    }
}