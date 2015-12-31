import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	char comp;
    	boolean flag;
    	for(int j=0;j<n;j++){
    		flag=false;comp=97;
    		String str1=s.next();
        	String str2=s.next();
        	while(comp<=122 && flag==false){
        		if(str1.indexOf(comp)!=-1 && str2.indexOf(comp)!=-1)
        			flag=true;
        		comp++;
        	}
        	if(flag==true)
        		System.out.println("YES");
        	else
        		System.out.println("NO");
    	}
    }
}