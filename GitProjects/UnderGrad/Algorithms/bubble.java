/*
Suraj Poojary	C22_109
Program Statement:Implementation of different sorting techniques
*/
import java.util.Scanner;
class bubble
{
	static void bubblesort(int a[])
	{
		for(int i=a.length-2;i>=0;i--)
		{
			for(int j=0;j<=i;j++)
			{
				if(a[j]>a[j+1])
				{
					int t=a[j];
					a[j]=a[j+1];
					a[j+1]=t;
				}
			}
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
		bubblesort(a);
		System.out.println("SORTED ARRAY");
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
43215
32145
21345
12345
SORTED ARRAY
12345
*/