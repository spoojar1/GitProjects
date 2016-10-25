import java.util.HashMap;
import java.util.Scanner;

public class Strobogrammatic {
	public static boolean isStrobogrammatic(String num) {
		//edge case for 1 digit number
        if(num.length()<2)
            if(num.equals("1") || num.equals("8"))
                return true;
            else
                return false;
        
		//lookup for Strobogrammatic numbers
        HashMap<Character,Character> map = new HashMap<Character,Character>();
	    map.put('6','9');
	    map.put('9','6');
	    map.put('1','1');
	    map.put('8','8');
	    map.put('0','0');
	    
		//palindrome logic but with a slight twist
	    for(int i=0;i<num.length()/2;i++){
	        if(map.get(num.charAt(num.length()-1-i)) != null){
	            if(num.charAt(i)!=map.get(num.charAt(num.length()-1-i)))
	                return false;
	        }else
	            return false;
	    }
		
		//to handle middle element of odd length string
	    if(num.length()%2!=0){
	        char temp = num.charAt(num.length()/2);
	        if(temp!='0' && temp!='8' && temp!='1')
	            return false;
	    }
	            
	    return true;
    }
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println(isStrobogrammatic(s.next()));
	}
}
