import java.util.*;

public class Solution {
	static String twoArrays(int a[],int b[],int k){
		Arrays.sort(a);
		Arrays.sort(b);
		for(int i=0;i<a.length;i++){
			if(a[i]+b[a.length-1-i]<k)
				return "NO";
		}
		return "YES";
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int t=s.nextInt(),n,k,a[],b[];
    	for(int i=0;i<t;i++){
    		n=s.nextInt();
    		k=s.nextInt();
    		a=new int[n];
    		b=new int[n];
	    	for(int j=0;j<n;j++)
	    		a[j]=s.nextInt();
	    	for(int j=0;j<n;j++)
	    		b[j]=s.nextInt();
	    	System.out.println(twoArrays(a,b,k));
    	}
    }
}