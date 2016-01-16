import java.io.*;
import java.util.*;

public class Solution {
	static void closestNumbers(int a[]){
		Arrays.sort(a);
		int min=10000001;
		int b[]=new int[a.length-1];
		for(int i=0;i<b.length;i++){
			b[i]=Math.abs(a[i]-a[i+1]);
			if(b[i]<min){
				min=b[i];
			}
			//System.out.println(a[i]+" "+b[i]);
		}
		//System.out.println(a[a.length-1]);
		for(int i=0;i<b.length;i++){
			if(b[i]==min)
				System.out.print(a[i]+" "+a[i+1]+" ");
		}
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int a[]=new int[n];
    	for(int i=0;i<n;i++)
    		a[i]=s.nextInt();
    	closestNumbers(a);
    }
}