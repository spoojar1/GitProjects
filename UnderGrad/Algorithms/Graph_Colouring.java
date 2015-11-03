/*
  Suraj Poojary
  C22_109
  GRAPH COLORING
  */
import java.io.*;
import java.util.*;

class Gr_color
{
	public int G[][],n,m,edges;
	int color_tab[];
	public Gr_color(int MAX)
	{
		System.out.println("\n\n\tGraph Coloring ");
		G=new int[MAX][MAX];
		color_tab=new int[MAX];
	}
	public void Gen_Col_Value(int k)
	{
		int j,a,b;
		while(true)
		{
			a=color_tab[k]+1;
			b=m+1;
			color_tab[k]=a%b;
			if(color_tab[k]==0) return;
			for(j=1;j<=n;j++)
			{
				if(G[k][j]!=0 && color_tab[k]==color_tab[j])
				break;
			}
			if(j==n+1) return;
		}
	}
	public void Gr_coloring(int k)
	{
		Gen_Col_Value(k);
		if(color_tab[k]==0) return;
		if(k==n) return;
		else Gr_coloring(k+1);
	}
	public void display()
	{
		System.out.println("\nThe vertices to be colored as: \n");
		for(int i=1;i<=n;i++)
			System.out.println("\n V" +i+ ": = " +color_tab[i]);
	}
}

class Graph_Colouring
{
	public static void main(String[] args) throws IOException,NullPointerException
	{
		Gr_color obj = new Gr_color(10);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int v1,v2;
		System.out.println("\nEnter the no. of nodes & edges: ");
		obj.n=Integer.parseInt(br.readLine());;
		obj.edges=Integer.parseInt(br.readLine());;
		obj.m=obj.n-1;
		System.out.println("\nCreate a Graph");
		System.out.println("\nEnter the egdes of the Graph: ");
		for(int i=1;i<=obj.edges;i++)
		{
			System.out.println("\nEnter the value of v1 & v2 :");
			v1=Integer.parseInt(br.readLine());;
			v2=Integer.parseInt(br.readLine());;
			obj.G[v1][v2]=obj.G[v2][v1]=1;
		}
		obj.Gr_coloring(1);
		obj.display();
	}

}
/*OUTPUT:


        Graph Coloring

Enter the no. of nodes & edges:
5
8

Create a Graph

Enter the egdes of the Graph:

Enter the value of v1 & v2 :
1
2

Enter the value of v1 & v2 :
1
3

Enter the value of v1 & v2 :
1
4

Enter the value of v1 & v2 :
2
3

Enter the value of v1 & v2 :
2
4

Enter the value of v1 & v2 :
2
5

Enter the value of v1 & v2 :
3
4

Enter the value of v1 & v2 :
4
5

The vertices to be colored as:


 V1: = 1

 V2: = 2

 V3: = 3

 V4: = 4

 V5: = 1
Press any key to continue . . .*/