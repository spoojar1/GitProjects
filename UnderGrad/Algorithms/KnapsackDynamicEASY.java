/*
o/1 Knapsack By Dynamic Programming
*/
import java.util.*;
public class KnapsackDynamicEASY
{
	public static void main(String str[])
	{
		Scanner s=new Scanner(System.in);
		int p[],w[],c[][],n,capacity,i,j;
		System.out.print("Enter no of objects: ");
		n=s.nextInt();
		System.out.print("Enter knapsack capacity: ");
		capacity=s.nextInt();
		p=new int[n+1];
		w=new int[n+1];
		c=new int[n+1][capacity+1];
		for(i=1;i<=n;i++)
		{
			System.out.println("\nFor object "+i+" : ");
			System.out.print("Enter profit: ");
			p[i]=s.nextInt();
			System.out.print("Enter weight: ");
			w[i]=s.nextInt();
		}
		for(i=1;i<=n;i++)
			c[i][0]=0;
		for(i=1;i<=n;i++)
		{
			for(j=1;j<=capacity;j++)
			{
				if(j-w[i]>=0)
					c[i][j]=Math.max(c[i-1][j],p[i]+c[i-1][j-w[i]]);
				else
					c[i][j]=c[i-1][j];
			}
		}
		System.out.println("\nMaximum profit: "+c[n][capacity]);
		i=n;j=capacity;
		while(i!=0&&j!=0)
		{
			if(c[i][j]!=c[i-1][j])
			{
				System.out.println("Object "+i+" is added");
				j=j-w[i];
				i--;
			}
			else
				i--;
		}
	}

}
/*
OUTPUT
Enter no of objects: 4
Enter knapsack capacity: 8

For object 1 :
Enter profit: 1
Enter weight: 2

For object 2 :
Enter profit: 2
Enter weight: 3

For object 3 :
Enter profit: 5
Enter weight: 4

For object 4 :
Enter profit: 6
Enter weight: 5

Maximum profit: 8
Object 4 is added
Object 2 is added
*/