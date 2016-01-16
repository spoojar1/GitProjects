import java.io.*;
import java.util.*;

public class Solution {
	static void QuicksortInPlace(int a[],int l,int r){
		if(l<r){
			int pivot=partition(a,l,r);
			QuicksortInPlace(a,l,pivot-1);
			QuicksortInPlace(a,pivot+1,r);
		}
	}
	static int partition(int a[],int l,int r){
		int j=l-1,tmp;
		for(int i=l;i<r;i++){
			if(a[i]<=a[r]){
				j++;
				tmp=a[j];
				a[j]=a[i];
				a[i]=tmp;
			}
		}
		tmp=a[j+1];
		a[j+1]=a[r];
		a[r]=tmp;
		display(a);
		return j+1;
	}
	
	static void display(int a[]){
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println();
	}
	
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++)
        	a[i]=s.nextInt();
        QuicksortInPlace(a,0,n-1);
    }
}