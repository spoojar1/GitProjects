import java.io.*;
import java.util.*;

public class CutTheSticks {
	static int calMin(int a[]){
		int min=1001;
		for(int i=0;i<a.length;i++){
			if(a[i]!=0 && a[i]<min)
				min=a[i];
		}
		return min;
	}
	
	static boolean checkEmpty(int a[]){
		boolean flag=true;
		for(int i=0;i<a.length;i++){
			if(a[i]!=0)
				flag=false;
		}
		return flag;
	}
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int a[]=new int[n];
    	for(int i=0;i<n;i++)
    		a[i]=s.nextInt();
    	
    	int count=1001,min=0;
    	while(!checkEmpty(a)){
    		count=0;
    		min=calMin(a);
    		for(int i=0;i<n;i++){
    			if(a[i]!=0){
    				a[i]-=min;
    				count++;
    			}
    		}
    		System.out.println(count);
    	}
    }
}