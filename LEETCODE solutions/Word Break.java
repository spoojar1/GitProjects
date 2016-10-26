public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        //logic: to prevent calculation of overlapping sub-problems again during recursion, 
        //we keep track of the prefix already evaluated.
        //eg: for string "carsabac" and dict =["cars","ca","rs","ab""ac"]
        //recursive solution will evaluate "cars" using dict "cars" and then go ahead and evaluate "abac" in one path
        //another path will evaluate "cars" using dict "ca" and "rs" and then go ahead and evaluate "abac" in one path
        //this overhead of reevaluating "abac" twice can be avoided if we keep track that cars was already evaluated.
        
        //boolean array to keep track of prefix evaluated, in the above eg, once "cars" is evaluated, we make status[4] as true, remember status[0] is dummy true(initial state)
        
        boolean status[] = new boolean[s.length() + 1];
        status[0] = true; //initial state
        
        for(int i=0;i<s.length();i++){
            
            //only explore positions that have prefixes already evaluated as true; false indicates prefix position not evaluated as true, hence skipped.
            if(!status[i])
                continue;
                
            for(String str: wordDict){
                int end = i + str.length();
                
                //if string remaining to be evaluated is smaller than word to be compared, skip the word
                if(end > s.length())
                    continue;
                
                //if prefix till this end position has already been evaluated, skip
                if(status[end])
                    continue;
                
                if(s.substring(i,end).equals(str))
                    status[end]=true;    //important step
            }
        }
        
        return status[s.length()];
    }
}