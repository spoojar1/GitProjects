import java.util.*;

public class PalindromeIndex {
	static boolean isPalin(String str){
		StringBuilder sb=new StringBuilder(str.substring(0,str.length()/2));
		if(str.length()%2==0)
			return sb.reverse().toString().equals(str.substring(str.length()/2));
		else
			return sb.reverse().toString().equals(str.substring(str.length()/2+1));
	}
	
	static int pIndex(String str){
		if(isPalin(str))
			return -1;
		for(int i=0;i<str.length()/2;i++){
			if(str.charAt(i)!=str.charAt(str.length()-1-i)){
				if(isPalin(str.substring(0,i)+str.substring(i+1)))
					return i;
				else
					return str.length()-1-i;
			}
		}
		return -1;
	}
	
    public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
    	for(int i=0;i<n;i++){
    		System.out.println(pIndex(s.next()));
    	}
    }
}