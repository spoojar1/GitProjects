public class Solution {
    public boolean canPermutePalindrome(String s) {
        /*Logic:
        Keep track of character frequencies i.e. off or even. We can use boolean to keep track of this. False is even freq and True is odd freq. If there are more than 1 odd frequencies, then a palindrome is not possible.
        */
        
        HashMap<Character,Boolean> map = new HashMap<Character,Boolean>();
        for(int i=0;i<s.length();i++){
            if(map.get(s.charAt(i))==null)
                map.put(s.charAt(i),true);
            else
                map.put(s.charAt(i),!map.get(s.charAt(i)));
        }
        
        int count = 0;
        for(Character c: map.keySet()){
            if(map.get(c))  //count odd frequency
                count++;    
            if(count>1)     //no need to count further once count goes beyond 1
                break;
        }
        
        return count<=1;
    }
}