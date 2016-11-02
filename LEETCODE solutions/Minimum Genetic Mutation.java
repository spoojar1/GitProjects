public class Solution {
    /*LOGIC: Brute Force
    Compare start string with each string in the bank, FOR ALL strings that match the mutation condition (differ by 1), recursively start the process with the matched string
    On successfully finding the end string, update the min mutation counter.
    */
    
    int min_count = Integer.MAX_VALUE;
    boolean success = false;
    public int minMutation(String start, String end, String[] bank) {
        HashSet<String> set = new HashSet<String>();
        for(int i=0;i<bank.length;i++)
            set.add(bank[i]);
        
        if(!set.contains(end))
            return -1;
        
        rec_minMutation(start, end, set, 0);
        
        if(success)
            return min_count;
        else
            return -1;
    }
    
    public void rec_minMutation(String start, String end, HashSet<String> tmp, int count){
        HashSet<String> set = new HashSet<String>();
        Iterator j = tmp.iterator();
        while(j.hasNext()){
            String temp = (String)j.next();
            if(!temp.equals(start))
                set.add(temp);
        }
        
        Iterator i = set.iterator();
         while(i.hasNext()){
             String e = (String)i.next();
             if(checkValidMutation(start,e)){
                 if(e.equals(end)){
                     success = true;
                     if(count+1 < min_count)
                         min_count = count + 1;
                 }else
                     rec_minMutation(e, end, set, count + 1);
             }
         }
    }
    public boolean checkValidMutation(String s1, String s2){
        int count=0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i))
                count++;
        }
        return count==1?true:false;
    }
}