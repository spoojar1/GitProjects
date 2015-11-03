import java.util.*;
class Qgraph
{
	int v,e,g[][],visited[],sum=0,dest;
	Scanner s=new Scanner(System.in);
	Queue q=new LinkedList();

	void creategraph()
	{
		System.out.println("Enter no of vertices");
		v=s.nextInt();
		System.out.println("Enter no of edges");
		e=s.nextInt();
		g=new int[v+1][v+1];

		for(int i=1;i<=e;i++)
		{
			System.out.println("Enter starting and ending node and weight of edge "+i);
			int a=s.nextInt();
			int b=s.nextInt();
			int w=s.nextInt();
			g[a][b]=g[b][a]=w;
		}
		visited=new int[v+1];
		for(int i=1;i<=v;i++)
			visited[i]=0;

		System.out.println("Enter destination node");
		dest=s.nextInt();
	}
	void bfs(int k)
	{
		System.out.println("The path to the destination is");
		q.add(k);
		while(q.peek()!=null)
		{
			k=Integer.parseInt(q.remove().toString());
			if(k==dest)
			{
				System.out.print(k);
				break;
			}
			System.out.print(k+"->");
			visited[k]=1;
			for(int w=1;w<=v;w++)
				if(g[k][w]==1)
					if(visited[w]!=1 && !q.contains(w))
					{
						q.add(w);
					}
		}
	}
}
class BFS
{
	public static void main(String str[])
	{
		Qgraph g=new Qgraph();
		g.creategraph();
		g.bfs(1);
	}
}
/*
OUTPUT
Enter no of vertices
8
Enter no of edges
8
Enter starting and ending node and weight of edge 1
1
2
1
Enter starting and ending node and weight of edge 2
1
3
1
Enter starting and ending node and weight of edge 3
1
4
1
Enter starting and ending node and weight of edge 4
2
5
1
Enter starting and ending node and weight of edge 5
5
6
1
Enter starting and ending node and weight of edge 6
6
7
1
Enter starting and ending node and weight of edge 7
4
7
1
Enter starting and ending node and weight of edge 8
4
8
1
Enter destination node
7

The path to the destination is
1->2->3->4->5->7
*/