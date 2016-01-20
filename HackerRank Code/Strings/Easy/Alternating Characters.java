import java.util.*;

public class Solution {
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int count=0;
    	String str;
    	for(int i=0;i<n;i++){
    		count=0;
    		str=s.next();
    		for(int j=1;j<str.length();j++){
    			if(str.charAt(j)==str.charAt(j-1))
    				count++;
    		}
    		System.out.println(count);
    	}
    }
}