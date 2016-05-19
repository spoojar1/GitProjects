import java.util.*;
public class JimAndTheOrders {
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		int a[][]=new int[n][4];
		for(int i=0;i<n;i++){
			a[i][0]=i;
			a[i][1]=s.nextInt();
			a[i][2]=s.nextInt();
			a[i][3]=a[i][1]+a[i][2];
		}
		java.util.Arrays.sort(a,new java.util.Comparator<int[]>(){
			public int compare(int a1[],int a2[]){
				return Integer.compare(a1[3],a2[3]);
			}
		});
		for(int i=0;i<n;i++)
			System.out.print((a[i][0]+1)+" ");
   }
}
