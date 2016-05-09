/*
Aim:Implementation of All Pair Shortest Path Algorithm
*/
import java.util.*;
class Array
{
	int g[][];
	Array(int n)    //n=v+1
	{
		g=new int[n][n];
	}
}
class APSP   //all pair shortest path
{
	Scanner s=new Scanner(System.in);
	int v,e;
	void creategraph()
	{
		int a,b;
		System.out.println("Enter no of vertices");
		v=s.nextInt();
		System.out.println("Enter no of edges");
		e=s.nextInt();

		Array p[]=new Array[v+1];  //p0,p1,p2,p3.....pV
		for(int i=0;i<=v;i++)
			p[i]=new Array(v+1);

		for(int i=1;i<=v;i++)
		for(int j=1;j<=v;j++)
			if(i!=j)
				p[0].g[i][j]=32767;   //infinite
			else
				p[0].g[i][j]=0;

		for(int i=1;i<=e;i++)
		{
			System.out.println("Enter starting node and ending node of edge "+i);
			a=s.nextInt();
			b=s.nextInt();
			System.out.println("Enter cost of the path from node "+a+" to node "+b);
			p[0].g[a][b]=s.nextInt();
		}
		apsp(p);
	}
	void apsp(Array p[])
	{
		for(int k=1;k<=v;k++)
		{
			for(int i=1;i<=v;i++)
			{
				for(int j=1;j<=v;j++)
				{
					p[k].g[i][j]=min(p[k-1].g[i][j],p[k-1].g[i][k]+p[k-1].g[k][j]);
				}
			}

			System.out.println("Array   p"+k+"\n");
			for(int i=1;i<=v;i++)
			{
				for(int j=1;j<=v;j++)
					System.out.print(p[k].g[i][j]+"   ");
				System.out.println();
			}
			System.out.println();
		}

		//display
		System.out.println("SOURCE  DESTINATION  MIN_COST");
		for(int i=1;i<=v;i++)
		for(int j=1;j<=v;j++)
		{
			if(i!=j)
				System.out.println("  "+i+"         "+j+"       "+p[v].g[i][j]);
		}
	}
	int min(int a,int b)
	{
		if(a<b)
			return a;
		else
			return b;
	}
}
class AllPair
{
	public static void main(String str[])
	{
		APSP t=new APSP();
		t.creategraph();
	}
}
/*OUTPUT
Enter no of vertices
3
Enter no of edges
5
Enter starting node and ending node of edge 1
1
2
Enter cost of the path from node 1 to node 2
4
Enter starting node and ending node of edge 2
2
1
Enter cost of the path from node 2 to node 1
8
Enter starting node and ending node of edge 3
1
3
Enter cost of the path from node 1 to node 3
15
Enter starting node and ending node of edge 4
3
1
Enter cost of the path from node 3 to node 1
3
Enter starting node and ending node of edge 5
2
3
Enter cost of the path from node 2 to node 3
2
Array   p1

0   4   15
8   0   2
3   7   0

Array   p2

0   4   6
8   0   2
3   7   0

Array   p3

0   4   6
5   0   2
3   7   0

SOURCE  DESTINATION  MIN_COST
  1         2       4
  1         3       6
  2         1       5
  2         3       2
  3         1       3
  3         2       7

*/