import java.math.BigInteger;
import java.util.*;
public class Solution {
	static BigInteger modiFibo(BigInteger a,BigInteger b,int n){
		BigInteger result=new BigInteger("0");
		for(int i=2;i<n;i++){
			result=a.add(b.multiply(b));
			a=b;
			b=result;
		}
		return result;
	}
		
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		BigInteger a=new BigInteger(s.next()),b=new BigInteger(s.next());
		int n=s.nextInt();
		System.out.println(modiFibo(a,b,n));
   }
}
