import java.io.*;
import java.util.*;

public class TaumNBday {
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	long x,y,z,b,w,tmp1,tmp2,tmp3;
    	for(int i=0;i<n;i++){
    		b=s.nextLong();
    		w=s.nextLong();
    		x=s.nextLong();
    		y=s.nextLong();
    		z=s.nextLong();
    		
    		tmp1=b*x+w*y;
    		tmp2=b*x+w*(x+z);
    		tmp3=b*(y+z)+w*y;
    		if(tmp1<=tmp2 && tmp1<=tmp3)
    			System.out.println(tmp1);
    		else if(tmp2<tmp1 && tmp2<=tmp3)
    			System.out.println(tmp2);
    		else
    			System.out.println(tmp3);
    	}
    }
}