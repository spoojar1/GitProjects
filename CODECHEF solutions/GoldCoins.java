import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class GoldCoins {
	static HashMap<Long, Long> h;
	
	public static long calcChange(long n){
		if(h.containsKey(n))
			return h.get(n);
		if(n==0 || n==1)
			return n;
		long tmp1=n/2,tmp2=n/3,tmp3=n/4,result=0;
		
		if(h.containsKey(tmp1)){
			if(h.get(tmp1)>tmp1)
				result+=h.get(tmp1);
			else
				result+=tmp1;
		}else
			result+=calcChange(tmp1);
		
		if(h.containsKey(tmp2)){
			if(h.get(tmp2)>tmp2)
				result+=h.get(tmp2);
			else
				result+=tmp2;
		}else
			result+=calcChange(tmp2);
		
		if(h.containsKey(tmp3)){
			if(h.get(tmp3)>tmp3)
				result+=h.get(tmp3);
			else
				result+=tmp3;
		}else
			result+=calcChange(tmp3);
		
		if(result<n)
			result=n;
		
		h.put(n,result);

		return result;
	}
	public static void main(String[] args) throws IOException{
		Scanner s=new Scanner(System.in);
    	String str="";
    	h=new HashMap<Long, Long>();
    	while (s.hasNextLong()) {
    		long n=s.nextLong();
 			System.out.println(calcChange(n));
 			h.clear();
        }
   }
}