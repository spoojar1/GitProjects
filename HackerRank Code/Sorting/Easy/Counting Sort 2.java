import java.io.*;
import java.util.*;

public class Solution {
	static void countingSort(int a[]){
		int c[]=new int[101];
		int b[]=new int[a.length+1];
		for(int i=0;i<a.length;i++)
			c[a[i]]++;
		for(int i=1;i<c.length;i++){
			c[i]+=c[i-1];
			//System.out.println(i+" "+c[i]);
		}
			
		for(int i=a.length-1;i>=0;i--){
			//System.out.println(a[i]+" "+c[a[i]]+" "+b[c[a[i]]]);
			b[c[a[i]]]=a[i];
			c[a[i]]--;
		}
		for(int i=1;i<b.length;i++)
			System.out.print(b[i]+" ");
	}
	
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++)
        	a[i]=s.nextInt();
        countingSort(a);
    }
}