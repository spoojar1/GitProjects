import java.util.*;
public class Solution {
	static void maxSubArray(int a[]){
		int b[]=Arrays.copyOf(a,a.length);
		Arrays.sort(b);
		int max_end_here1=a[0],max_end_here2=b[0],max_so_far1=a[0],max_so_far2=b[0];
		if(max_end_here1<0)
			max_end_here1=0;
		if(max_end_here2<0)
			max_end_here2=0;
		
		for(int i=1;i<a.length;i++){
			max_end_here1+=a[i];
			max_end_here2+=b[i];
			
			if(max_end_here1>max_so_far1)
				max_so_far1=max_end_here1;

			if(max_end_here2>max_so_far2)
				max_so_far2=max_end_here2;
			
			if(max_end_here1<0)
				max_end_here1=0;
			if(max_end_here2<0)
				max_end_here2=0;
		}
		System.out.println(max_so_far1+" "+max_so_far2);
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
