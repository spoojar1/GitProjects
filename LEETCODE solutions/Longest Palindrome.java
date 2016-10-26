public class Solution {
    public int longestPalindrome(String s) {
        /*Logic:
        Track frequency of each character. The number of even frequencies will contribute to the final count. If there is even a single odd frequency, 1 will be added to the count.
        eg: aabbc will set count = 4(aabb) + 1(c) = 5
            aabcd will set count = 2(aa) + 1(b/c/d) = 3
        */
        
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        
        for(int i=0;i<s.length();i++){
            if(map.get(s.charAt(i))==null)
                map.put(s.charAt(i), 1);
            else
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        }
        
        int count=0;    //keep track of even frequency
        boolean flag=false; //to track odd frequency
        for(Character c: map.keySet()){
            count += (map.get(c)/2)*2;  //if freq is 5, it contributes 4 to the count i.e (5/2)*2
            if(map.get(c)%2==1) //check for odd frequency
                flag=true;
        }
        
        return flag==false?count:count+1;
    }
}