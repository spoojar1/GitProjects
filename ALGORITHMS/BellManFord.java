import java.util.*;
class BellMan
{
	Scanner s=new Scanner(System.in);
	int v,e,g[][],dist[],source,min[],count=-1,distp[];
	void creategraph()
	{
		int a,b;
		System.out.println("Enter no of vertices");
		v=s.nextInt();
		System.out.println("Enter no of edges");
		e=s.nextInt();
		g=new int[v+1][v+1];
		dist=new int[v+1];
		distp=new int[v+1];
		min=new int[v];

		for(int i=1;i<=v;i++)
		for(int j=1;j<=v;j++)
			if(i!=j)
				g[i][j]=32767;   //infinite
			else
				g[i][j]=0;

		for(int i=1;i<=e;i++)
		{
			System.out.println("Enter starting node and ending node of edge "+i);
			a=s.nextInt();
			b=s.nextInt();
			System.out.println("Enter cost of the path from node "+a+" to node "+b);
			g[a][b]=s.nextInt();
		}

		System.out.println("Enter source vertex");
		source=s.nextInt();
	}
	void BMF()
	{
		for(int i=1;i<=v;i++)
			distp[i]=g[source][i];     //dist's  row no source is same as G's row no source
		for(int k=2;k<=v-1;k++)     //executes v-2 times  just a count loop
									//can be 1 to v-2 also doesn't matter
		{
			for(int u=1;u<=v;u++)
			{
				if(u!=source)
				{
					for(int i=1;i<=v;i++)
					{
						if(distp[u]>(distp[i]+g[i][u]))
						{

							count++;
							min[count]=distp[i]+g[i][u];
						}
					}
					if(count==-1)
						dist[u]=distp[u];
					else
						dist[u]=minimum();
				}
			}
			for(int j=1;j<=v;j++)   //copying dist[] to distp[]  don't do distp=disp
				distp[j]=dist[j];
		}
		display();
	}
	void display()
	{
		System.out.println("SOURCE  DESTINATION  MIN_COST");
		for(int i=1;i<=v;i++)
			System.out.println("   "+source+"         "+i+"          "+distp[i]);
	}
	int minimum()
	{
		for(int i=count-1;i>=0;i--)   //(count+1)-2
		{
			for(int j=0;j<=i;j++)
			{
				if(min[j]>min[j+1])
				{
					int t=min[j];
					min[j]=min[j+1];
					min[j+1]=t;
				}
			}
		}
		count=-1;
		return min[0];
	}
}
class BellManFord
{
	public static void main(String str[])
	{
		BellMan b=new BellMan();
		b.creategraph();
		b.BMF();
	}
}
/*
OUTPUT
Enter no of vertices
7
Enter no of edges
10
Enter starting node and ending node of edge 1
1
2
Enter cost of the path from node 1 to node 2
6
Enter starting node and ending node of edge 2
1
3
Enter cost of the path from node 1 to node 3
5
Enter starting node and ending node of edge 3
1
4
Enter cost of the path from node 1 to node 4
5
Enter starting node and ending node of edge 4
3
2
Enter cost of the path from node 3 to node 2
-2
Enter starting node and ending node of edge 5
4
3
Enter cost of the path from node 4 to node 3
-2
Enter starting node and ending node of edge 6
2
5
Enter cost of the path from node 2 to node 5
-1
Enter starting node and ending node of edge 7
3
5
Enter cost of the path from node 3 to node 5
1
Enter starting node and ending node of edge 8
4
6
Enter cost of the path from node 4 to node 6
-1
Enter starting node and ending node of edge 9
5
7
Enter cost of the path from node 5 to node 7
3
Enter starting node and ending node of edge 10
6
7
Enter cost of the path from node 6 to node 7
3
Enter source vertex
1
SOURCE  DESTINATION  MIN_COST
   1         1          0
   1         2          1
   1         3          3
   1         4          5
   1         5          0
   1         6          4
   1         7          3

*/
