import java.util.*;

public class ANDProduct {
	static long andProd(long a,long b,long d){
		long c=a;
		for(long j=a+1;j<=b;j++){
			c&=j;
			if(c==d)
				break;
		}
		return c;
	}
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		int t=s.nextInt(),atmp,btmp;
		long a,b;
		for(int i=0;i<t;i++){
			a=s.nextLong();
			b=s.nextLong();
			atmp=(int) Math.floor(Math.log(a)/Math.log(2));
			btmp=(int) Math.floor(Math.log(b)/Math.log(2));
			if(atmp==btmp)
				System.out.println(andProd(a,b,(long)Math.pow(2,atmp)));
			else
				System.out.println(0);
		}
   }
}
