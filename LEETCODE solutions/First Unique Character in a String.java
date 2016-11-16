public class Solution {
    public int firstUniqChar(String s) {
        if(s==null || s.length()==0)
            return -1;
            
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(map.get(c)==null)
                map.put(c,i);
            else
                map.put(c,-1);
        }
        
        int min_index = Integer.MAX_VALUE;
        for(char c:map.keySet()){
            int index = map.get(c);
            if(index!=-1)
                min_index = Math.min(index, min_index);
        }
        
        return min_index==Integer.MAX_VALUE?-1:min_index;
    }
}