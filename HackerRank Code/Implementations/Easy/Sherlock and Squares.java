import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	double n1,n2,count=0;
    	//double tmp1,tmp2;
    	for(int i=0;i<n;i++){
    		n1=s.nextDouble();
    		n2=s.nextDouble();
    		n1=Math.sqrt(n1);
    		n2=Math.sqrt(n2);
    		if(n1-(long)n1==0)
    			count++;
    		count+=(long)n2-(long)n1;
    		System.out.println((long)count);
            count=0;
    	}    
    }
}