import java.util.*;

public class Solution {
	static long sherlockPairs(int a[]){
		long c[]=new long[1000001];
		long count=0;
		for(int i=0;i<a.length;i++)
			c[a[i]]++;
		for(int i=0;i<c.length;i++)
			if(c[i]>1){
				count+=(long)(c[i]*(c[i]-1));
			}
		return count;
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int t=s.nextInt(),n;
    	int a[];
    	for(int j=0;j<t;j++){
    		n=s.nextInt();
    		a=new int[n];
    		for(int i=0;i<n;i++)
    			a[i]=s.nextInt();
    		System.out.println(sherlockPairs(a));
    	}
    }
}