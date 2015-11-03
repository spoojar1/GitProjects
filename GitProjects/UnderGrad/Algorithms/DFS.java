import java.util.*;
class Qgraph
{
	int v,e,g[][],visited[];
	Scanner s=new Scanner(System.in);
	int sum=0,dest;
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
	void dsf(int k,String s,int cost)
	{
		visited[k]=1;
		if(k==dest)
		{
			System.out.print(k);
			s=s+Integer.toString(k);
			System.out.println("\nThe actual path :"+s);
			System.out.println("\nThe cost of reaching the destination :"+cost);
			System.exit(1);
		}
		System.out.print(k+"->");
		s=s+Integer.toString(k)+"->";
		for(int w=1;w<=v;w++)
			if(g[k][w]!=0)
				if(visited[w]!=1)
					dsf(w,s,cost+g[k][w]);
	}
}
class DFS
{
	public static void main(String str[])
	{
		Qgraph g=new Qgraph();
		g.creategraph();
		System.out.print("The DFS sequence : ");
		g.dsf(1,"",0);
	}
}
/*
OUTPUT
Enter no of vertices
6
Enter no of edges
6
Enter starting and ending node and weight of edge 1
1
2
1
Enter starting and ending node and weight of edge 2
1
3
1
Enter starting and ending node and weight of edge 3
2
4
1
Enter starting and ending node and weight of edge 4
2
5
1
Enter starting and ending node and weight of edge 5
2
6
2
Enter starting and ending node and weight of edge 6
3
6
2
Enter destination node
6
The DFS sequence : 1->2->4->5->6
The actual path : 1->2->6

The cost of reaching the destination :3

*/