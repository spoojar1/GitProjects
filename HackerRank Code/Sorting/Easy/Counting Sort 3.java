import java.io.*;
import java.util.*;

public class Solution {
	static void countingSort(String a[][]){
		int c[]=new int[100];
		//int b[]=new int[a.length+1];
		for(int i=0;i<a.length;i++)
			c[Integer.parseInt(a[i][0])]++;
		for(int i=1;i<c.length;i++)
			c[i]+=c[i-1];
			
		for(int i=0;i<c.length;i++)
			System.out.print(c[i]+" ");
	}
	
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        String a[][]=new String[n][2];
        for(int i=0;i<n;i++){
        	a[i][0]=String.valueOf(s.nextInt());
        	a[i][1]=s.next();
        }
        countingSort(a);
    }
}