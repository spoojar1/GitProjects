/*Suraj Poojary
  C22_109
  AIM4- TO IMPLEMENT GREEDY APPROACH ( USING KRUSKAL'S ALGO.)
*/

import java.io.*;
import java.util.*;

class edge
{
	int v1,v2,wt;
	edge()
	{
	}
	public String toString()
	{
		return(v1+"-->"+v2+" ("+wt+")");
	}
}


class kruskal
{
	int n,v;
	edge e[];

	kruskal()throws IOException
	{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the number of edges & vertices: ");
		n=Integer.parseInt(in.readLine());
		v=Integer.parseInt(in.readLine());
		e=new edge[n];

		System.out.println("Enter the two verices & its weight: ");

		for(int i=0;i<n;i++)
		e[i]=new edge();

		for(int i=0;i<n;i++)
		{
			System.out.println("For edge: "+(i+1));
			e[i].v1=Integer.parseInt(in.readLine());
			e[i].v2=Integer.parseInt(in.readLine());
			e[i].wt=Integer.parseInt(in.readLine());
		}
	}//Kruskal() ends here

     	void sort()
		{
			for(int i=0;i<n-1;i++)
			{
				for(int j=0;j<n-1;j++)
				{
					if(e[j].wt>e[j+1].wt)
					{
						edge t;
						t=e[j];
						e[j]=e[j+1];
						e[j+1]=t;
					}
				}
			}
			System.out.println("Edges in Sorted Order: ");
			System.out.println("From Vertex-->To Vertex (Weight)");
			for(int i=0;i<n;i++)
			System.out.println(e[i]);
		}// Sort() ends here

		void findMST()
		{
			int parent[]=new int[n];
			int i=0,j=0;
			int fwt=0;

			for(int k=0;k<n;k++)
			parent[k]=-1;

			int nc=1;

			System.out.println("Minimum Spanning tree is :");
			System.out.println("From Vertex-->To Vertex (Weight)");
			for(int k=0;k<n;k++)
			{
				i=e[k].v1;
				j=e[k].v2;

				while(parent[i]>-1)
				i=parent[i];

				while(parent[j]>-1)
				j=parent[j];

				if(i!=j)
				{
					System.out.println(e[k].v1+"-->"+e[k].v2+" ("+e[k].wt+")");
					fwt+=e[k].wt;
					nc++;
					parent[j]=i;
					if(nc==v)
					break;
				}
			 }
			System.out.println("Minimum Spanning cost is :"+fwt);
		}// findMST() ends here
}//class Kruskal ends here

class KruskalDemo1
{
	public static void main(String args[])throws IOException
	{
		kruskal ob=new kruskal();
		ob.sort();
		ob.findMST();
	}
}
/*OUTPUT:
Enter the number of edges & vertices:
8
5
Enter the two verices & its weight:
For edge: 1
1
3
5
For edge: 2
1
3
6
For edge: 3
2
3
8
For edge: 4
2
4
7
For edge: 5
2
5
1
For edge: 6
3
4
9
For edge: 7
3
5
2
For edge: 8
4
5
9
Edges in Sorted Order:
From Vertex-->To Vertex (Weight)
2-->5 (1)
3-->5 (2)
1-->3 (5)
1-->3 (6)
2-->4 (7)
2-->3 (8)
3-->4 (9)
4-->5 (9)
Minimum Spanning tree is :
From Vertex-->To Vertex (Weight)
2-->5 (1)
3-->5 (2)
1-->3 (5)
2-->4 (7)
Minimum Spanning cost is :15
Press any key to continue . . .
*/
