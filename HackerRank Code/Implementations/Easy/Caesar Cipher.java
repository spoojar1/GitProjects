import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt(),k=0;
    	char c[]=new char[n];
    	String str="";
    	str=s.next();
    	k=s.nextInt();
    	c=str.toCharArray();
    	int tmp;
    	for(int j=0;j<n;j++){
    		if(c[j]>=65 && c[j]<=90){
    			tmp=c[j]+k;
    			if(tmp>90){
    				tmp-=90;
    				if(tmp%26==0)
    					c[j]='Z';
    				else
    					c[j]=(char)((tmp%26)+64);
    			}
    			else
    				c[j]=(char)tmp;	
    		}
    		if(c[j]>=97 && c[j]<=122){
    			tmp=c[j]+k;
    			if(tmp>122){
    				tmp-=122;
    				if(tmp%26==0)
    					c[j]='z';
    				else
    					c[j]=(char)((tmp%26)+96);
    			}
    			else
    				c[j]=(char)tmp;		
    		}
    	}
    	System.out.println(c); 
    }
}