import java.io.*;
import java.util.*;

public class ChocolateFeast {

    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt(),N,C,M,w,count=0;
    	for(int i=0;i<n;i++){
    		N=s.nextInt();
    		C=s.nextInt();
    		M=s.nextInt();
    		count=w=N/C;
    		while(w/M>0){
    			count+=w/M;
    			w=(w/M)+(w%M);
    		}
    		System.out.println(count);
    		count=0;
    	}    
    }
}