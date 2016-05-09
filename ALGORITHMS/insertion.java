/*
 Suraj Poojary	C22_109
 Program Statement:Implementation of different sorting techniques
 */
 import java.util.Scanner;
class insertion
{
	static void insertionsort(int a[])
	{
		int x,j;
		for(int i=1;i<=a.length-1;i++)
		{
			x=a[i];	j=i;
			while(j>0&&a[j-1]>x)
			{
				a[j]=a[j-1];
				j--;
			}
			a[j]=x;
			for(int k=0;k<a.length;k++)
				System.out.print(a[k]);
			System.out.println();
        }
	} 
	public static void main(String str[])
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter array size");
		int n=s.nextInt();
		int a[]=new int[n];
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter element "+(i+1));
			a[i]=s.nextInt();
        }
		insertionsort(a);
		System.out.println("\nSORTED ARRAY");
		for(int i=0;i<n;i++)
			System.out.print(a[i]);
    }
}
/*
OUTPUT
Enter array size
5
Enter element 1
5
Enter element 2
4
Enter element 3
3
Enter element 4
2
Enter element 5
1
45321
34521
23451
12345

SORTED ARRAY
12345
 */
 