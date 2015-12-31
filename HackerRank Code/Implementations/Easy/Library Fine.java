import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int d1,d2,m1,m2,y1,y2;
    	d1=s.nextInt();
    	m1=s.nextInt();
    	y1=s.nextInt();
    	
    	d2=s.nextInt();
    	m2=s.nextInt();
    	y2=s.nextInt();
    	
    	int fine=0;
    	if(y1!=y2){
    		if(y1>y2)
    			fine=10000;
    	}else if(m1!=m2){
    		if(m1>m2)
    			fine=(m1-m2)*500;
    	}else if(d1!=d2){
    		if(d1>d2)
    			fine=(d1-d2)*15;
    	}
    	System.out.println(fine);
    }
}