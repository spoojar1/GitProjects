import java.io.*;
import java.util.*;

public class ManasaNStones {
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int t=s.nextInt(),n,a,b,count;
    	for(int i=0;i<t;i++){
    		n=s.nextInt();
    		a=s.nextInt();
    		b=s.nextInt();
    		if(a>b){
    			a=a+b;
    			b=a-b;
    			a=a-b;
    		}
    			
    		count=a*(n-1);
    		if(a==b)
    			System.out.print(count);
    		else{
    			System.out.print(count+" ");
    			for(int j=1;j<n;j++){
    				count+=(b-a);
    				System.out.print(count+" ");
    			}
    		}
    		System.out.println();
    	}
    }
}