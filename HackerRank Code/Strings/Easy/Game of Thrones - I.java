import java.util.*;

public class Solution {
	static String GOT(String str){
		HashMap<Character,Integer> h=new HashMap<Character,Integer>();
		for(int i=0;i<str.length();i++){
			if(h.get(str.charAt(i))==null)
				h.put(str.charAt(i),1);
			else
				h.put(str.charAt(i),h.get(str.charAt(i))+1);
		}
		int countOdd=0;
		for(Map.Entry<Character,Integer> entry : h.entrySet()){
			if(entry.getValue()%2==1)
				countOdd++;
		}
		if(countOdd>1)
			return "NO";
		else
			return "YES";
	}
	
    public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
    	System.out.println(GOT(s.next()));
    }
}