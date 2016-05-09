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
	void prim()
	{
		int d[],p[],visited[],current,c=1;
		d=new int[v+1];
		p=new int[v+1];
		visited=new int[v+1];
		for(int i=1;i<=v;i++)
			d[i]=32767;

		current=1;
		d[current]=0;
		visited[current]=1;
		while(c!=v)
		{
			for(int i=1;i<=v;i++)
				if(g[current][i]!=0&&visited[i]!=1)
					if(g[current][i]<d[i])
					{
						d[i]=g[current][i];
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
			c++;
		}
		int min_cost=0;
		for(int i=1;i<=v;i++)
			min_cost+=d[i];
		System.out.println("The min_cost of the span tree is "+min_cost);
		for(int i=2;i<=v;i++)
			System.out.println("The node "+i+" is connected to node "+p[i]);
	}
}
class PrimsAlgo
{
	public static void main(String str[])
	{
		Graph g=new Graph();
		g.creategraph();
		g.prim();
	}
}
/*
OUTPUT
Enter no of vertices
5
Enter no of egdes
9
Enter starting and ending node of edge 1
1
2
Enter weight of the edge
2
Enter starting and ending node of edge 2
1
4
Enter weight of the edge
2
Enter starting and ending node of edge 3
1
3
Enter weight of the edge
8
Enter starting and ending node of edge 4
1
5
Enter weight of the edge
6
Enter starting and ending node of edge 5
2
3
Enter weight of the edge
3
Enter starting and ending node of edge 6
2
5
Enter weight of the edge
18
Enter starting and ending node of edge 7
3
4
Enter weight of the edge
9
Enter starting and ending node of edge 8
3
5
Enter weight of the edge
6
Enter starting and ending node of edge 9
4
5
Enter weight of the edge
5
The min_cost of the span tree is 12
The node 2 is connected to node 1
The node 3 is connected to node 2
The node 4 is connected to node 1
The node 5 is connected to node 4
*/