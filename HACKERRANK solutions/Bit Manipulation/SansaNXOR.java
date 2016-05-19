import java.util.*;

public class SansaNXOR {
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int t=s.nextInt();
    	int n,xor;
    	long a[][],tmp;
    	for(int k=0;k<t;k++){
    		n=s.nextInt();
    		tmp=n;
    		xor=0;
    		a=new long[n][2];
	    	for(int i=0;i<n;i++){
	    		a[i][0]=s.nextInt();
	    		a[i][1]=((i+1)*tmp)%2;
	    		tmp--;
	    		if(a[i][1]==1)
	    			xor=(int) (xor^a[i][0]);
	    	}
	    	System.out.println(xor);
    	}
    }
}