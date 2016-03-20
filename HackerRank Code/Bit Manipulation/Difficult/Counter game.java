import java.math.BigInteger;
import java.util.*;

public class Solution {
	public static String counterGame(BigInteger b1){
		int n;
		BigInteger b2;
		boolean flag=false;
		while(true){
			if(b1.compareTo(new BigInteger("1"))==0)
				return flag==false?"Richard":"Louise";
			n = b1.bitLength();
			b2 = new BigInteger("2").pow(n-1);
			if (b1.compareTo(b2) == 0) {
				b1 = b1.divide(new BigInteger("2"));
			} else {
				b1 = b1.subtract(b2);
			}
			flag=!flag;
		}
	}
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	int t=s.nextInt();
    	BigInteger b1;
    	for(int i=0;i<t;i++){
			b1 = new BigInteger(s.next());
			System.out.println(counterGame(b1));
    	}
    }
}