import java.util.*;
public class LargestContinuousSum {
	static void maxSubArray(int a[]){
		int b[]=Arrays.copyOf(a,a.length);
		Arrays.sort(b);
		int max_end_here=a[0],max_so_far=a[0];
		if(max_end_here<0)
			max_end_here=0;
			
		for(int i=1;i<a.length;i++){
			max_end_here+=a[i];
			
			if(max_end_here>max_so_far)
				max_so_far=max_end_here;
			
			if(max_end_here<0)
				max_end_here=0;
		}
		System.out.println(max_so_far);
	}
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		int t=s.nextInt(),n,a[];
		for(int i=0;i<t;i++){
			n=s.nextInt();
			a=new int[n];
			for(int j=0;j<n;j++)
				a[j]=s.nextInt();
			maxSubArray(a);
		}
   }
}
