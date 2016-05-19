import java.io.*;
import java.util.*;

public class FunnyStrings {
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
		String str,rstr;
		StringBuilder sb;
		char cstr[],crstr[];
		boolean flag;
    	for(int j=0;j<n;j++){
    		flag=true;
    		str=s.next();
    		sb = new StringBuilder(str);
    		rstr=sb.reverse().toString();
    		cstr=str.toCharArray();
    		crstr=rstr.toCharArray();
    		for(int i=1;i<cstr.length;i++){
    			if(Math.abs(cstr[i]-cstr[i-1])!=Math.abs(crstr[i]-crstr[i-1]))
    				flag=false;
    		}
    		if(!flag)
    			System.out.println("Not Funny");
    		else
    			System.out.println("Funny");	
    	}
    }
}