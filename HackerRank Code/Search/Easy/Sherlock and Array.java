import java.util.*;
public class Solution {
	static long flip(String str){
		String tmp=str;
		for(int i=0;i<32-str.length();i++)
			tmp="0"+tmp;
		str=tmp;
		char c[]=str.toCharArray();
		for(int i=0;i<c.length;i++){
			if(c[i]=='1')
				c[i]='0';
			else
				c[i]='1';
		}
		return Long.parseLong(String.valueOf(c),2);
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	long a;
    	int n=s.nextInt();
    	for(int i=0;i<n;i++){
    		a=s.nextLong();
    		System.out.println(flip(Long.toBinaryString(a)));
    	}
    }
}