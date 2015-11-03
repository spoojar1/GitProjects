import java.util.*;
class Nqueen
{
	public static void main(String str[])
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter no of queens");
		int n=s.nextInt();
		int board[]=new int[n+1];
		Queen q=new Queen();
		q.queen(1,n,board);
	}
}
class Queen
{
	int count=0;
	void queen(int r,int n,int board[])
	{
		for(int c=1;c<=n;c++)
		{
			if(place(r,c,board))
			{
				board[r]=c;
				if(r==n)
				{
					count++;
					printboard(board,n);
				}
				else
					queen(r+1,n,board);
			}
		}
	}
	boolean place(int r,int c,int board[])
	{
		for(int i=1;i<r;i++)
		{
			if(board[i]==c)
				return false;
			else
				if(Math.abs(board[i]-c)==Math.abs(i-r))
					return false;
		}
		return true;
	}
	void printboard(int board[],int n)
	{
		int i,j;
		System.out.println("Solution "+count+"\n");
		for(i=1;i<=n;i++)
		{
			System.out.print("\t"+i);
		}
		System.out.println("\n");
		for(i=1;i<=n;i++)
		{
			System.out.print(i);
			for(j=1;j<=n;j++)
			{
				if(board[i]==j)
				System.out.print("\tQ");
				else
				System.out.print("\t*");
			}
			System.out.println();
		}
		System.out.println("\n\n");
	}
}
/*
OUTPUT
Enter no of queens
4
Solution 1

        1       2       3       4

1       *       Q       *       *
2       *       *       *       Q
3       Q       *       *       *
4       *       *       Q       *



Solution 2

        1       2       3       4

1       *       *       Q       *
2       Q       *       *       *
3       *       *       *       Q
4       *       Q       *       *



*/