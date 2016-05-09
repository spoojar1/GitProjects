import java.util.*;
class Graph
{
	int v,e,g[][];
	Scanner s=new Scanner(System.in);
	void creategraph()
	{
		int a,b;
		System.out.println("Enter no of vertices");
		v=s.nextInt();
		System.out.println("Enter no of egdes");
		e=s.nextInt();
		g=new int[v+1][v+1];
		for(int i=1;i<=e;i++)
		{
			System.out.println("Enter starting and ending node of edge "+i);
			a=s.nextInt();
			b=s.nextInt();
			System.out.println("Enter weight of the edge");
			g[a][b]=g[b][a]=s.nextInt();
		}
	}
	void dijkshtra()
	{
		int d[],p[],visited[],current;
		d=new int[v+1];
		p=new int[v+1];
		visited=new int[v+1];
		for(int i=1;i<=v;i++)
			d[i]=32767;

		int source,dest;
		System.out.println("Enter source and destination nodes");
		source=s.nextInt();
		dest=s.nextInt();
		current=source;
		d[current]=0;
		visited[current]=1;
		while(visited[dest]!=1)
		{
			int dc=d[current];
			for(int i=1;i<=v;i++)
				if(g[current][i]!=0&&visited[i]!=1)
					if(g[current][i]+dc<d[i])
					{
						d[i]=g[current][i]+dc;
						p[i]=current;
					}
			int min=32767;
			for(int i=1;i<=v;i++)
				if(visited[i]!=1)
					if(d[i]<min)
					{
						min=d[i];
						current=i;
					}
			visited[current]=1;
		}

		System.out.println("\nThe min_cost of the span tree is "+d[dest]);
		System.out.println("\nThe shortest path is:\n");
		int i=dest;
		while(i!=source)
		{
			System.out.println("Node "+i+" is connected to node "+p[i]);
			i=p[i];
		}
	}
}
class DijkshtrasAlgo
{
	public static void main(String str[])
	{
		Graph g=new Graph();
		g.creategraph();
		while(true)
			g.dijkshtra();
	}
}
/*
OUTPUT
Enter no of vertices
6
Enter no of egdes
8
Enter starting and ending node of edge 1
1
2
Enter weight of the edge
1
Enter starting and ending node of edge 2
1
3
Enter weight of the edge
2
Enter starting and ending node of edge 3
1
4
Enter weight of the edge
3
Enter starting and ending node of edge 4
2
5
Enter weight of the edge
5
Enter starting and ending node of edge 5
3
5
Enter weight of the edge
4
Enter starting and ending node of edge 6
3
6
Enter weight of the edge
6
Enter starting and ending node of edge 7
4
6
Enter weight of the edge
7
Enter starting and ending node of edge 8
5
6
Enter weight of the edge
8
Enter source and destination nodes
5
4
The min_cost of the span tree is 9
Node 4 is connected to node 1
Node 1 is connected to node 3
Node 3 is connected to node 5
*/