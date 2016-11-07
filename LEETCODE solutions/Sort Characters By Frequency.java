public class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(map.containsKey(c))
                map.put(c,map.get(c)+1);
            else
                map.put(c,1);
        }

        Map<Integer,String> tmap = new TreeMap<Integer,String>(); 
        for(Character c : map.keySet()){
            int count = map.get(c);
            if(tmap.containsKey(count))
                tmap.put(count, tmap.get(count) + help(count,c));
            else
                tmap.put(count, help(count,c));
        }
        
        String result="";
        for(int key : tmap.keySet())
            result = tmap.get(key) + result;
            
        return result;
    }
    
    public String help(int count, char c){
        StringBuilder str = new StringBuilder();
        while(count > 0){
            str.append(String.valueOf(c));
            count--;
        }
        return str.toString();
    }
}