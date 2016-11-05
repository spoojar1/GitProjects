public class Solution {
    public boolean wordPattern(String pattern, String str) {
        /*LOGIC:
        for each character in pattern, one token need to be associated AND one token should be associated with only one pattern character
        */
        
        HashMap<Character,String> map = new HashMap<Character,String>();
        
        String arr[]=str.split(" ");
        if(arr.length!=pattern.length())
            return false;
        
        for(int i=0;i<pattern.length();i++){
            char c = pattern.charAt(i);
            if(map.containsKey(c)){
                if(!map.get(c).equals(arr[i]))
                    return false;
            }
            else{
                if(map.containsValue(arr[i]))   //for a new pattern character, if current token already present/associated to some other character, then return false.
                    return false;
                else
                    map.put(c,arr[i]);
            }
        }
        return true;
    }
}