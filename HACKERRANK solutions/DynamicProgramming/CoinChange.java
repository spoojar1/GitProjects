import java.util.*;

public class CoinChange{
	public static long makeChange(int n, int c[],int denom,long store[][]) {
		//System.out.println(n+" "+c[denom]+"entry");
		if(store[n][denom]!=-1){
			//System.out.println(n+" "+c[denom]+"memoized exit");
			return store[n][denom];
		}
		if(denom==0){
			if(n%c[denom]==0){
				//System.out.println(n+" "+c[denom]+"exit premature");
				return 1;
			}else
				return 0;
		}
		//int next_denom = c[denom-1];
		long ways = 0;
		//System.out.println(n+" "+c[denom]+" "+c[denom-1]+" "+ways);
		for (int i = 0; i * c[denom] <= n; i++) {
			.
			ways += makeChange(n - i * c[denom], c, denom-1,store);
		}
		//System.out.println(n+" "+c[denom]+" "+c[denom-1]+" "+ways+" exit");
		store[n][denom]=ways;
		return ways;
	}
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=s.nextInt(), m=s.nextInt(), c[]=new int[m];
        for(int i=0;i<m;i++)
        	c[i]=s.nextInt();
        Arrays.sort(c);
        long store[][]=new long[n+1][m];
        for(long a[]:store)
        	Arrays.fill(a,-1);
        System.out.println(makeChange(n,c,m-1,store));
    }
}