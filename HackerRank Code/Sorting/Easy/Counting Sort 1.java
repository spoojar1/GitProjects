import java.io.*;
import java.util.*;

public class Solution {
	static void countingSort(int a[]){
		int c[]=new int[100];
		for(int i=0;i<a.length;i++)
			c[a[i]]++;
		for(int i=0;i<c.length;i++)
			System.out.print(c[i]+" ");
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