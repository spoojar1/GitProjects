import java.util.*;

public class FindSortedRotated {
	static int find(int a[],int x,int p,int r){
		if(p<=r){
			int mid=(p+r)/2;
			if(a[mid]==x)
				return mid;
			if(a[p]<=a[mid]){
				if(x>=a[p] && x<a[mid])
					return find(a,x,p,mid-1);
				else
					return find(a,x,mid+1,r);
			}else{
				if(x>a[mid] && x<a[p])
					return find(a,x,mid+1,r);
				else
					return find(a,x,p,mid-1);
			}
		}else
			return -1;
	}
    public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		int a[]=new int[n];
		for(int i=0;i<n;i++)
			a[i]=s.nextInt();
		System.out.println(find(a,s.nextInt(),0,n-1));
    }
}