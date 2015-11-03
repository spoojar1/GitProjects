import java.util.*;
class RadixSort
{
	static void radixsort(int a[],int n)
	{
		int i,j,digit,e=1;
		int bucket[][]=new int[a.length][10];
		int c[]=new int[10];
		for(i=1;i<=n;i++)
		{
			for(j=0;j<=9;j++)
				c[j]=-1;
			for(j=0;j<a.length;j++)
			{
				digit=(a[j]/e)%10;
				c[digit]++;
				bucket[c[digit]][digit]=a[j];
			}
			int z=0;
			for(j=0;j<=9;j++)
			{
				if(c[j]!=-1)
				for(int k=0;k<=c[j];k++)
				{
					a[z]=bucket[k][j];
					z++;
				}
			}
			e=e*10;
		}
	}
	public static void main(String str[])
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter array size");
		int n=s.nextInt();
		int a[]=new int[n];
		System.out.println("Enter no of digits in largest no");
		int d=s.nextInt();
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter element "+(i+1));
			a[i]=s.nextInt();
        }
		radixsort(a,d);
		System.out.println("\nSORTED ARRAY");
		for(int i=0;i<n;i++)
			System.out.println(a[i]);
	}
}
/*
OUTPUT
Enter array size
9
Enter no of digits in largest no
4
Enter element 1
991
Enter element 2
568
Enter element 3
412
Enter element 4
1
Enter element 5
45
Enter element 6
0
Enter element 7
6547
Enter element 8
23
Enter element 9
58

SORTED ARRAY
0
1
23
45
58
412
568
991
6547

*/