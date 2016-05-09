/*
SURAJ POOJARY	C22_109
Program Statement:Implementation of different sorting techniques
*/
import java.util.*;
class Mergesort
{
	static void simplemerge(int a[],int f,int s,int t)
	{
		int i,j,k,w;
		i=f;	j=s;	k=-1;
		int temp[]=new int[a.length];
		while(i<s&&j<=t)
		{
			if(a[i]<=a[j])
			{
				temp[++k]=a[i];
				i++;
			}
			else
			{
				temp[++k]=a[j];
				j++;
			}
		}
		if(i==s)
			for(w=j;w<=t;w++)
				temp[++k]=a[w];
		else
			for(w=i;w<s;w++)
				temp[++k]=a[w];
		for(w=0;w<=k;w++)
			a[f+w]=temp[w];
		//intermediate steps
		for(int y=0;y<a.length;y++)
			System.out.print(a[y]+"  ");
		System.out.println();
	}
	static void mergesort(int a[],int l,int r)
	{
		int mid;
		if(l!=r)
		{
			mid=(l+r)/2;
			mergesort(a,l,mid);
			mergesort(a,mid+1,r);
			simplemerge(a,l,mid+1,r);
		}
	}
	public static void main(String str[])
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter length of the array");
		int n=s.nextInt();
		int a[]=new int[n];
		System.out.println("Enter elements of the array");
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter element "+(i+1));
			a[i]=s.nextInt();
		}
		mergesort(a,0,n-1);
		System.out.println("Sorted array");
		for(int i=0;i<n;i++)
			System.out.print(a[i]+"  ");
	}
}
/*
OUTPUT
Enter length of the array
8
Enter elements of the array
Enter element 1
95
Enter element 2
50
Enter element 3
40
Enter element 4
5
Enter element 5
25
Enter element 6
9
Enter element 7
65
Enter element 8
8
50  95  40  5  25  9  65  8
50  95  5  40  25  9  65  8
5  40  50  95  25  9  65  8
5  40  50  95  9  25  65  8
5  40  50  95  9  25  8  65
5  40  50  95  8  9  25  65
5  8  9  25  40  50  65  95
Sorted array
5  8  9  25  40  50  65  95
*/