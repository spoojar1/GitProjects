import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int N=s.nextInt();
    	int t=s.nextInt();
    	int array[]=new int[N],a,b,min;
    	
    	for(int i=0;i<N;i++)
    		array[i]=s.nextInt();
    		
    	for(int i=0;i<t;i++){
    		a=s.nextInt();
    		b=s.nextInt();
    		min=4;
    		for(int j=a;j<=b;j++){
    			if(array[j]<min){
    				min=array[j];
    			}
    		}
    		System.out.println(min);
    	}
    }
}