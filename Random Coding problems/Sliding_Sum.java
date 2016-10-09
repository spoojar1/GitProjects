import java.util.Scanner;

class Sliding_Sum{
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("Enter length of array and window size separated by space");
		int n=s.nextInt(), k=s.nextInt();
		System.out.println("Enter elements of array separated by space");
		int a[]=new int[n];
		for(int i=0;i<n;i++)
			a[i]=s.nextInt();
		System.out.println("Sliding window sums are : ");
		int i=0, sum=0;
		while(i<n){
			if(i>=k){
				sum -= a[i-k];	
			}
			sum += a[i];
			if(i>=k-1)
				System.out.println((double)sum/k);
			else
				System.out.println((double)sum/(i+1));
			i++;
		}
	}
}