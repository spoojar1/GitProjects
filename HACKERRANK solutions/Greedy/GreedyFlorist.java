import java.util.*;

public class GreedyFlorist {
	static int flowers(int c[],int f[]){
		int cost=0;
		Arrays.sort(c);
		int j=-1;
		for(int i=c.length-1;i>=0;i--){
			cost+=(f[(++j)%f.length]+1)*c[i];
			f[j%f.length]++;
		}
		return cost;
	}
    public static void main(String[] args){
    	Scanner s=new Scanner(System.in);
    	int n=s.nextInt();
    	int k=s.nextInt();
    	int c[]=new int[n];
    	int f[]=new int[k];
    	for(int i=0;i<n;i++)
    		c[i]=s.nextInt();
    	System.out.println(flowers(c,f));
    }
}