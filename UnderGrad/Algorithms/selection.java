import java.util.Scanner;
class selection
{
	static void selectionsort(int a[])
	{
		int min,p,j,t;
		for(int i=0;i<a.length-1;i++)
		{
			min=a[i];p=i;
			for(j=i+1;j<a.length;j++)
			{
				while(a[j]<min)
				{
					min=a[j];	p=j;
				}
			}
			t=a[i];
			a[i]=a[p];
			a[p]=t;
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
		selectionsort(a);
		System.out.println("SORTED ARRAY");
		for(int i=0;i<n;i++)
		{
			System.out.println(a[i]);
        }
	}
}
/*OUTPUT
Enter array size
5
Enter element 1
0
Enter element 2
7
Enter element 3
1
Enter element 4
9
Enter element 5
2
07192
01792
01297
01279
SORTED ARRAY
0
1
2
7
9
*/
