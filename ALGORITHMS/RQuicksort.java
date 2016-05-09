/*
SURAJ POOJARY	C22_109
Program Statement:Implementation of different sorting techniques
*/
import java.util.*;
class RQuicksort
{
	static int partition(int a[],int l,int r)
	{
		int i,j,t,x;
		int rand=(int)Math.random()%(r-l+1)+l;
		i=l;	j=r;	x=a[rand];
		while(i<j)
		{
			while(i<=r&&a[i]<=x)
				i++;
			while(a[j]>x)
				j--;
			if(i<j)
			{
				t=a[i];
				a[i]=a[j];
				a[j]=t;
			}
		}
		t=a[rand];
		a[rand]=a[j];
		a[j]=t;
		for(int k=0;k<a.length;k++)
			System.out.print(a[k]+"  ");
		System.out.println();
		return j;
	}
	static void Quick(int a[],int l,int r)
	{
		int p;
		if(l<r)
		{
			p=partition(a,l,r);
			Quick(a,l,p-1);
			Quick(a,p+1,r);
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
		Quick(a,0,n-1);
		System.out.println("Sorted array");
		for(int i=0;i<n;i++)
			System.out.print(a[i]+"  ");
	}
}

/*
OUTPUT
Enter length of the array
6
Enter elements of the array
Enter element 1
1
Enter element 2
9
Enter element 3
4
Enter element 4
0
Enter element 5
5
Enter element 6
20
0  1  4  9  5  20
0  1  4  9  5  20
0  1  4  5  9  20
Sorted array
0  1  4  5  9  20
*/

